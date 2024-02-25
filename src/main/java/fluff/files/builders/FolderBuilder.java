package fluff.files.builders;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fluff.files.Folder;
import fluff.files.IFileBuilder;
import fluff.files.attributes.IFileAttribute;
import fluff.functions.gen.VoidFunc;
import fluff.functions.gen.obj.VoidFunc1;

/**
 * A builder class for creating folders with various attributes.
 */
public class FolderBuilder implements IFileBuilder<Folder> {
    
    private final List<VoidFunc> preFuncs = new ArrayList<>();
    private final List<VoidFunc1<Folder>> postFuncs = new ArrayList<>();
    private final Map<IFileAttribute<?>, Object> attributes = new HashMap<>();
    
    private String name;
    private File parent;
    
    @Override
    public IFileBuilder<Folder> name(String name) {
        this.name = name;
        return this;
    }
    
    @Override
    public IFileBuilder<Folder> parent(File parent) {
        this.parent = parent;
        return this;
    }
    
    @Override
    public <T> IFileBuilder<Folder> attribute(IFileAttribute<T> attribute, T value) {
        attributes.put(attribute, value);
        return this;
    }
    
    @Override
    public IFileBuilder<Folder> onPre(VoidFunc preFunc) {
        preFuncs.add(preFunc);
        return this;
    }
    
    @Override
    public IFileBuilder<Folder> onPost(VoidFunc1<Folder> postFunc) {
        postFuncs.add(postFunc);
        return this;
    }
    
    @Override
    public Folder build() {
        for (VoidFunc f : preFuncs) {
            f.invoke();
        }
        
        Folder folder = new Folder(parent, name);
        
        for (Map.Entry<IFileAttribute<?>, Object> e : attributes.entrySet()) {
            ((IFileAttribute) e.getKey()).set(folder, e.getValue());
        }
        
        for (VoidFunc1<Folder> f : postFuncs) {
            f.invoke(folder);
        }
        
        return folder;
    }
}
