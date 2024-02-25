package fluff.files;

import java.io.File;

/**
 * Represents a folder in the file system.
 */
public class Folder extends File {
    
    private static final long serialVersionUID = -2682058718826851629L;
    
    /**
     * Constructs a new Folder instance representing the folder at the specified path.
     * If the folder does not exist, it will be created.
     *
     * @param path the path to the folder
     */
    public Folder(String path) {
        super(path);
        
        if (!this.exists())
            this.mkdirs();
    }
    
    /**
     * Constructs a new Folder instance representing the folder with the specified parent and path.
     * If the folder does not exist, it will be created.
     *
     * @param parent the parent directory
     * @param path the path to the folder
     */
    public Folder(File parent, String path) {
        super(parent, path);
        
        if (!this.exists())
            this.mkdirs();
    }
}
