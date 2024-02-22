package fluff.files;

import java.io.File;

import fluff.files.attributes.IFileAttribute;
import fluff.functions.gen.VoidFunc;
import fluff.functions.gen.obj.VoidFunc1;

public interface IFileBuilder<V extends File> {
	
	IFileBuilder<V> name(String name);
	
	IFileBuilder<V> parent(File parent);
	
	<T> IFileBuilder<V> attribute(IFileAttribute<T> attribute, T value);
	
	IFileBuilder<V> onPre(VoidFunc preFunc);
	
	IFileBuilder<V> onPost(VoidFunc1<V> postFunc);
	
	V build();
}
