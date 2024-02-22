package fluff.files.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import fluff.files.IFileWrite;

public class FileWrite implements IFileWrite {
	
	private final FileOutputStream fos;
	
	public FileWrite(File file) {
		try {
			this.fos = new FileOutputStream(file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public IFileWrite append(byte[] bytes) {
		try {
			fos.write(bytes);
		} catch (IOException e) {}
		return this;
	}
	
	@Override
	public IFileWrite append(String text) {
		return append(text.getBytes());
	}
	
	@Override
	public IFileWrite append(List<String> lines) {
		for (String line : lines) {
			append(line).newLine();
		}
		return this;
	}
	
	@Override
	public IFileWrite newLine() {
		return append(System.lineSeparator());
	}
	
	@Override
	public void close() {
		try {
			fos.close();
		} catch (IOException e) {}
	}
}
