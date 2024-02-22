package fluff.files;

import java.io.File;

public class Folder extends File {
	
	private static final long serialVersionUID = -2682058718826851629L;
	
	public Folder(String path) {
		super(path);
		
		if (!this.exists())
			this.mkdirs();
	}
	
	public Folder(File parent, String path) {
		super(parent, path);
		
		if (!this.exists())
			this.mkdirs();
	}
}
