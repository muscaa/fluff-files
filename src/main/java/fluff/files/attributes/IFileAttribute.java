package fluff.files.attributes;

import java.io.File;

/**
 * Interface for file attributes.
 *
 * @param <V> the type of the attribute value
 */
public interface IFileAttribute<V> {
    
    /**
     * Gets the value of the attribute for the specified file.
     *
     * @param file the file to get the attribute from
     * @return the value of the attribute
     */
    V get(File file);
    
    /**
     * Sets the value of the attribute for the specified file.
     *
     * @param file the file to set the attribute for
     * @param value the value of the attribute
     */
    void set(File file, V value);
}
