package fluff.files.builders;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fluff.files.FileHelper;
import fluff.files.IFileBuilder;
import fluff.files.attributes.IFileAttribute;
import fluff.functions.gen.VoidFunc;
import fluff.functions.gen.obj.VoidFunc1;

public class FileBuilder implements IFileBuilder<File> {
	
	private final List<VoidFunc> preFuncs = new ArrayList<>();
	private final List<VoidFunc1<File>> postFuncs = new ArrayList<>();
	private final Map<IFileAttribute<?>, Object> attributes = new HashMap<>();
	
	private String name;
	private File parent;
	
	@Override
	public IFileBuilder<File> name(String name) {
		this.name = name;
		return this;
	}
	
	@Override
	public IFileBuilder<File> parent(File parent) {
		this.parent = parent;
		return this;
	}
	
	@Override
	public <T> IFileBuilder<File> attribute(IFileAttribute<T> attribute, T value) {
		attributes.put(attribute, value);
		return this;
	}
	
	@Override
	public IFileBuilder<File> onPre(VoidFunc preFunc) {
		preFuncs.add(preFunc);
		return this;
	}
	
	@Override
	public IFileBuilder<File> onPost(VoidFunc1<File> postFunc) {
		postFuncs.add(postFunc);
		return this;
	}
	
	@Override
	public File build() {
		for (VoidFunc f : preFuncs) {
			f.invoke();
		}
		
		File file = new File(parent, name);
		FileHelper.create(file);
		
		for (Map.Entry<IFileAttribute<?>, Object> e : attributes.entrySet()) {
			((IFileAttribute) e.getKey()).set(file, e.getValue());
		}
		
		for (VoidFunc1<File> f : postFuncs) {
			f.invoke(file);
		}
		
		return file;
	}
}
