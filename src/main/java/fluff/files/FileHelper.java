package fluff.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import fluff.files.builders.FileBuilder;
import fluff.files.builders.FolderBuilder;
import fluff.files.io.FileRead;
import fluff.files.io.FileWrite;

/**
 * A utility class for common file operations.
 */
public class FileHelper {
    
    /**
     * Creates a new file builder instance.
     *
     * @return the file builder instance
     */
    public static IFileBuilder<File> file() {
        return new FileBuilder();
    }
    
    /**
     * Creates a new folder builder instance.
     *
     * @return the folder builder instance
     */
    public static IFileBuilder<Folder> folder() {
        return new FolderBuilder();
    }
    
    /**
     * Creates a new file read instance for the specified file.
     *
     * @param file the file to read
     * @return the file read instance
     */
    public static IFileRead read(File file) {
        return new FileRead(file);
    }
    
    /**
     * Creates a new file write instance for the specified file.
     *
     * @param file the file to write
     * @return the file write instance
     */
    public static IFileWrite write(File file) {
        return new FileWrite(file);
    }
    
    /**
     * Deletes the specified file or directory.
     *
     * @param file the file or directory to delete
     * @return true if the file or directory is successfully deleted, false otherwise
     */
    public static boolean delete(File file) {
        try {
            Files.walkFileTree(file.toPath(), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }
                
                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
            return true;
        } catch (IOException e) {}
        return false;
    }
    
    /**
     * Copies data from one file to another.
     *
     * @param from the source file
     * @param to the destination file
     * @return true if the data is successfully copied, false otherwise
     */
    public static boolean copy(File from, File to) {
        try (FileInputStream in = new FileInputStream(from);
        		FileOutputStream out = new FileOutputStream(to)) {
            in.transferTo(out);
            return true;
        } catch (IOException e) {}
        return false;
    }
    
    /**
     * Creates a new file. If the file already exists, it returns true.
     *
     * @param file the file to create
     * @return true if the file is successfully created or already exists, false otherwise
     */
    public static boolean create(File file) {
        if (file.exists()) return true;
        if (file.getParentFile() != null) file.getParentFile().mkdirs();
        try {
            return file.createNewFile();
        } catch (IOException e) {}
        return false;
    }
    
    /**
     * Retrieves the extension of the specified file.
     *
     * @param file the file
     * @return the extension of the file, or null if no extension is found
     */
    public static String getExtension(File file) {
        int last = file.getName().lastIndexOf('.');
        if (last == -1) return null;
        return file.getName().substring(last);
    }
    
    /**
     * Retrieves the name of the specified file without its extension.
     *
     * @param file the file
     * @return the name of the file without extension
     */
    public static String getNameNoExtension(File file) {
        int last = file.getName().lastIndexOf('.');
        if (last == -1) return file.getName();
        return file.getName().substring(0, last);
    }
    
    /**
     * Retrieves the URL of the specified file.
     *
     * @param file the file
     * @return the URL of the file, or null if the URL cannot be created
     */
    public static URL getURL(File file) {
        try {
            return file.toURI().toURL();
        } catch (MalformedURLException e) {
            // Handle MalformedURLException, return null if URL creation fails
        }
        return null;
    }
    
    /**
     * Retrieves the string representation of the URL of the specified file.
     *
     * @param file the file
     * @return the string representation of the file's URL, or null if the URL cannot be created
     */
    public static String getURLString(File file) {
        URL url = getURL(file);
        return url != null ? url.toString() : null;
    }
}
