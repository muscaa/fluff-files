package fluff.files;

import java.util.List;

public interface IFileWrite {
	
	IFileWrite append(byte[] bytes);
	
	IFileWrite append(String text);
	
	IFileWrite append(List<String> lines);
	
	IFileWrite newLine();
	
	void close();
}
