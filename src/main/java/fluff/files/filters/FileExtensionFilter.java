package fluff.files.filters;

import java.io.File;
import java.io.FilenameFilter;

import fluff.files.FileHelper;

/**
 * A FilenameFilter implementation that filters files by their extensions.
 */
public class FileExtensionFilter implements FilenameFilter {
    
    private final String[] extensions;
    
    /**
     * Constructs a FileExtensionFilter with the specified extensions.
     *
     * @param extensions the file extensions to filter
     */
    public FileExtensionFilter(String... extensions) {
        this.extensions = new String[extensions.length];
        
        for (int i = 0; i < extensions.length; i++) {
            String prefix = "";
            if (!extensions[i].startsWith(".")) {
                prefix = ".";
            }
            
            this.extensions[i] = prefix + extensions[i];
        }
    }
    
    @Override
    public boolean accept(File dir, String name) {
        String extension = FileHelper.getExtension(new File(dir, name));
        if (extension == null) return false;
        
        for (String s : extensions) {
            if (extension.equals(s)) {
                return true;
            }
        }
        return false;
    }
}
