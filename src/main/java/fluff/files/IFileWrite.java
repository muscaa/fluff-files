package fluff.files;

import java.util.List;

/**
 * Interface for writing to a file.
 */
public interface IFileWrite {
    
    /**
     * Appends the specified byte array to the file.
     *
     * @param bytes the byte array to append
     * @return the file writer instance
     */
    IFileWrite append(byte[] bytes);
    
    /**
     * Appends the specified text to the file.
     *
     * @param text the text to append
     * @return the file writer instance
     */
    IFileWrite append(String text);
    
    /**
     * Appends the specified list of lines to the file.
     *
     * @param lines the list of lines to append
     * @return the file writer instance
     */
    IFileWrite append(List<String> lines);
    
    /**
     * Appends a newline character to the file.
     *
     * @return the file writer instance
     */
    IFileWrite newLine();
    
    /**
     * Closes the file writer, releasing any resources associated with it.
     */
    void close();
}
