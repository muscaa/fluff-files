package fluff.files.attributes;

import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.GroupPrincipal;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.UserPrincipal;
import java.util.Set;

public class FileAttributes {
	
	// BASIC
	public static final IFileAttribute<Long> BASIC_SIZE = new ViewNameFileAttribute<>("basic", "size");
	public static final IFileAttribute<FileTime> BASIC_CREATION_TIME = new ViewNameFileAttribute<>("basic", "creationTime");
	public static final IFileAttribute<FileTime> BASIC_LAST_ACCESS_TIME = new ViewNameFileAttribute<>("basic", "lastAccessTime");
	public static final IFileAttribute<FileTime> BASIC_LAST_MODIFIED_TIME = new ViewNameFileAttribute<>("basic", "lastModifiedTime");
	public static final IFileAttribute<Object> BASIC_FILE_KEY = new ViewNameFileAttribute<>("basic", "fileKey");
	public static final IFileAttribute<Boolean> BASIC_IS_DIRECTORY = new ViewNameFileAttribute<>("basic", "isDirectory");
	public static final IFileAttribute<Boolean> BASIC_IS_REGULAR_FILE = new ViewNameFileAttribute<>("basic", "isRegularFile");
	public static final IFileAttribute<Boolean> BASIC_IS_SYMBOLIC_LINK = new ViewNameFileAttribute<>("basic", "isSymbolicLink");
	public static final IFileAttribute<Boolean> BASIC_IS_OTHER = new ViewNameFileAttribute<>("basic", "isOther");
	
	// DOS
	public static final IFileAttribute<Boolean> DOS_READONLY = new ViewNameFileAttribute<>("dos", "readonly");
	public static final IFileAttribute<Boolean> DOS_HIDDEN = new ViewNameFileAttribute<>("dos", "hidden");
	public static final IFileAttribute<Boolean> DOS_ARCHIVE = new ViewNameFileAttribute<>("dos", "archive");
	public static final IFileAttribute<Boolean> DOS_SYSTEM = new ViewNameFileAttribute<>("dos", "system");
	
	// POSIX
	public static final IFileAttribute<Set<PosixFilePermission>> POSIX_PERMISSIONS = new ViewNameFileAttribute<>("posix", "permissions");
	public static final IFileAttribute<UserPrincipal> POSIX_OWNER = new ViewNameFileAttribute<>("posix", "owner");
	public static final IFileAttribute<GroupPrincipal> POSIX_GROUP = new ViewNameFileAttribute<>("posix", "group");
}
