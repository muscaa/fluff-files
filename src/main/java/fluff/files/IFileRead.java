package fluff.files;

import java.util.List;

import fluff.functions.gen._byte.VoidFunc1Byte;
import fluff.functions.gen.obj.VoidFunc1;

public interface IFileRead {
	
	byte[] Bytes();
	
	String String();
	
	List<String> lines();
	
	void forEachByte(VoidFunc1Byte func);
	
	void forEachLine(VoidFunc1<String> func);
}
