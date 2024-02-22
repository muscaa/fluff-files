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

public class FileHelper {
	
	public static IFileBuilder<File> file() {
		return new FileBuilder();
	}
	
	public static IFileBuilder<Folder> folder() {
		return new FolderBuilder();
	}
	
	public static IFileRead read(File file) {
		return new FileRead(file);
	}
	
	public static IFileWrite write(File file) {
		return new FileWrite(file);
	}
	
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
    
	public static boolean copy(File from, File to) {
		try (FileInputStream in = new FileInputStream(from); FileOutputStream out = new FileOutputStream(to)) {
			in.transferTo(out);
		} catch (IOException e) {}
		return false;
	}
	
	public static boolean create(File file) {
		if (file.exists()) return true;
		if (file.getParentFile() != null) file.getParentFile().mkdirs();
		try {
			file.createNewFile();
			return true;
		} catch (IOException e) {}
		return false;
	}
	
	public static String getExtension(File file) {
		int last = file.getName().lastIndexOf('.');
		if (last == -1) return null;
		return file.getName().substring(last);
	}
	
	public static String getNameNoExtension(File file) {
		int last = file.getName().lastIndexOf('.');
		if (last == -1) return file.getName();
		return file.getName().substring(0, last);
	}
	
	public static URL getURL(File file) {
		try {
			return file.toURI().toURL();
		} catch (MalformedURLException e) {}
		return null;
	}
	
	public static String getURLString(File file) {
		return getURL(file).toString();
	}
}
