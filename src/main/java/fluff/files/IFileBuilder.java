package fluff.files;

import java.io.File;

import fluff.files.attributes.IFileAttribute;
import fluff.functions.gen.VoidFunc;
import fluff.functions.gen.obj.VoidFunc1;

/**
 * Interface for building files with various attributes and callbacks.
 *
 * @param <V> the type of the file being built
 */
public interface IFileBuilder<V extends File> {
    
    /**
     * Specifies the name of the file.
     *
     * @param name the name of the file
     * @return the file builder instance
     */
    IFileBuilder<V> name(String name);
    
    /**
     * Specifies the parent directory of the file.
     *
     * @param parent the parent directory of the file
     * @return the file builder instance
     */
    IFileBuilder<V> parent(File parent);
    
    /**
     * Adds an attribute to the file.
     *
     * @param <T> the type of the attribute value
     * @param attribute the file attribute
     * @param value the value of the attribute
     * @return the file builder instance
     */
    <T> IFileBuilder<V> attribute(IFileAttribute<T> attribute, T value);
    
    /**
     * Specifies a pre-build callback function.
     *
     * @param preFunc the pre-build callback function
     * @return the file builder instance
     */
    IFileBuilder<V> onPre(VoidFunc preFunc);
    
    /**
     * Specifies a post-build callback function.
     *
     * @param postFunc the post-build callback function
     * @return the file builder instance
     */
    IFileBuilder<V> onPost(VoidFunc1<V> postFunc);
    
    /**
     * Builds the file with the specified attributes and callbacks.
     *
     * @return the built file
     */
    V build();
}
