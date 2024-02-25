package fluff.files;

import java.util.List;

import fluff.functions.gen._byte.VoidFunc1Byte;
import fluff.functions.gen.obj.VoidFunc1;

/**
 * Interface for reading file contents.
 */
public interface IFileRead {
    
    /**
     * Reads the entire file and returns its contents as a byte array.
     *
     * @return the file contents as a byte array
     */
    byte[] Bytes();
    
    /**
     * Reads the entire file and returns its contents as a string.
     *
     * @return the file contents as a string
     */
    String String();
    
    /**
     * Reads the lines of the file and returns them as a list of strings.
     *
     * @return the lines of the file as a list of strings
     */
    List<String> lines();
    
    /**
     * Executes the specified function for each byte in the file.
     *
     * @param func the function to execute for each byte
     */
    void forEachByte(VoidFunc1Byte func);
    
    /**
     * Executes the specified function for each line in the file.
     *
     * @param func the function to execute for each line
     */
    void forEachLine(VoidFunc1<String> func);
}
