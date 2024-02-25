package fluff.files.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fluff.files.IFileRead;
import fluff.functions.gen._byte.VoidFunc1Byte;
import fluff.functions.gen.obj.VoidFunc1;

/**
 * A utility class for reading data from files.
 */
public class FileRead implements IFileRead {
    
    private final File file;
    
    /**
     * Constructs a FileRead object with the specified file.
     *
     * @param file the file to read data from
     */
    public FileRead(File file) {
        this.file = file;
    }
    
    @Override
    public byte[] Bytes() {
        try (FileInputStream fis = new FileInputStream(file)) {
            return fis.readAllBytes();
        } catch (IOException e) {}
        return new byte[0];
    }
    
    @Override
    public String String() {
        return new String(Bytes());
    }
    
    @Override
    public List<String> lines() {
        List<String> list = new ArrayList<>();
        forEachLine(list::add);
        return list;
    }
    
    @Override
    public void forEachByte(VoidFunc1Byte func) {
        try (FileInputStream fis = new FileInputStream(file)) {
            while (fis.available() > 0) {
                func.invoke((byte) fis.read());
            }
        } catch (IOException e) {}
    }
    
    @Override
    public void forEachLine(VoidFunc1<String> func) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                func.invoke(line);
            }
        } catch (IOException e) {}
    }
}
