package fluff.files.attributes;

import java.io.File;

public interface IFileAttribute<V> {
	
	V get(File file);
	
	void set(File file, V value);
}
