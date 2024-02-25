package fluff.files.attributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * A file attribute implementation.
 *
 * @param <V> the type of the attribute value
 */
public class ViewNameFileAttribute<V> implements IFileAttribute<V> {
    
    private final String attribute;
    
    /**
     * Constructs a ViewNameFileAttribute with the specified view and name.
     *
     * @param view the view associated with the attribute
     * @param name the name of the attribute
     */
    public ViewNameFileAttribute(String view, String name) {
        this.attribute = view + ":" + name;
    }
    
    @Override
    public V get(File file) {
        try {
            return (V) Files.getAttribute(file.toPath(), attribute);
        } catch (IOException e) {}
        return null;
    }
    
    @Override
    public void set(File file, V value) {
        try {
            Files.setAttribute(file.toPath(), attribute, value);
        } catch (IOException e) {}
    }
}
