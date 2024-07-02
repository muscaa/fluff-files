package fluff.files;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

import fluff.files.builders.FileBuilder;
import fluff.files.builders.FolderBuilder;
import fluff.files.io.FileRead;
import fluff.files.io.FileWrite;

/**
 * A utility class for common file operations.
 */
public class FileHelper {
    
	private static final FileVisitor<Path> DELETE_VISITOR = new SimpleFileVisitor<>() {
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
    };
	
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
            Files.walkFileTree(file.toPath(), DELETE_VISITOR);
            return true;
        } catch (IOException e) {}
        return false;
    }
    
    /**
     * Deletes the contents of the specified directory.
     *
     * @param dir the directory whose contents are to be deleted
     * @return true if the directory's contents are successfully deleted, false otherwise
     */
    public static boolean deleteContents(Folder dir) {
        String[] list = dir.list();
        if (list == null) return false;
        boolean result = true;
        for (String path : list) {
        	File file = new File(dir, path);
            result &= delete(file);
        }
        return result;
    }

    
    /**
     * Copies a file or directory from one location to another.
     *
     * @param from the source file or directory
     * @param to the destination file or directory
     * @return true if the file or directory is successfully copied, false otherwise
     */
    public static boolean copy(File from, File to) {
    	try {
    		Path source = from.toPath();
    		Path target = to.toPath();
    		
			Files.walkFileTree(source, new SimpleFileVisitor<>() {
				
				@Override
				public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
			        Path resolve = target.resolve(source.relativize(dir));
			        if (Files.notExists(resolve))
			        	Files.createDirectories(resolve);
			        return FileVisitResult.CONTINUE;
				}
				
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			        Path resolve = target.resolve(source.relativize(file));
			        Files.copy(file, resolve,
			        		StandardCopyOption.REPLACE_EXISTING,
			        		StandardCopyOption.COPY_ATTRIBUTES
			        		);
			        return FileVisitResult.CONTINUE;
				}
			});
			return true;
		} catch (IOException e) {}
        return false;
    }
    
    /**
     * Copies the contents from one location to another.
     *
     * @param fromDir the source directory
     * @param toDir the destination directory
     * @return true if the directory's contents are successfully copied, false otherwise
     */
    public static boolean copyContents(Folder fromDir, Folder toDir) {
        String[] list = fromDir.list();
        if (list == null) return false;
        boolean result = true;
        for (String path : list) {
        	File from = new File(fromDir, path);
        	File to = new File(toDir, path);
            result &= copy(from, to);
        }
        return result;
    }
    
    /**
     * Moves a file or directory from one location to another.
     *
     * @param from the source file or directory
     * @param to the destination file or directory
     * @return true if the file or directory is successfully moved, false otherwise
     */
    public static boolean move(File from, File to) {
        try {
    		Path source = from.toPath();
    		Path target = to.toPath();
    		
			Files.walkFileTree(source, new SimpleFileVisitor<>() {
				
				@Override
				public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
			        Path resolve = target.resolve(source.relativize(dir));
			        if (Files.notExists(resolve))
			        	Files.createDirectories(resolve);
			        return FileVisitResult.CONTINUE;
				}
				
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			        Path resolve = target.resolve(source.relativize(file));
			        Files.move(file, resolve,
			        		StandardCopyOption.REPLACE_EXISTING
			        		);
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
     * Moves the contents from one location to another.
     *
     * @param fromDir the source directory
     * @param toDir the destination directory
     * @return true if the directory's contents are successfully moved, false otherwise
     */
    public static boolean moveContents(Folder fromDir, Folder toDir) {
        String[] list = fromDir.list();
        if (list == null) return false;
        boolean result = true;
        for (String path : list) {
        	File from = new File(fromDir, path);
        	File to = new File(toDir, path);
            result &= move(from, to);
        }
        return result;
    }

    
    /**
     * Creates a new file. If the file already exists, it returns true.
     *
     * @param file the file to create
     * @return true if the file is successfully created or already exists, false otherwise
     */
    public static boolean create(File file) {
        if (file.exists()) return true;
        File parent = file.getParentFile();
        if (parent != null) parent.mkdirs();
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
    	String name = file.getName();
        int last = name.lastIndexOf('.');
        if (last == -1) return null;
        return name.substring(last);
    }
    
    /**
     * Retrieves the name of the specified file without its extension.
     *
     * @param file the file
     * @return the name of the file without extension
     */
    public static String getNameNoExtension(File file) {
    	String name = file.getName();
        int last = name.lastIndexOf('.');
        if (last == -1) return name;
        return name.substring(0, last);
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
        } catch (MalformedURLException e) {}
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
