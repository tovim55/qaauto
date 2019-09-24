package com.verifone.tests.mpTests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.Iterator;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.IOUtils;

public class ZipArchiver {
	static List<Path> filesListInDir = new ArrayList();

	public ZipArchiver() {
	}

	public static String normalizePath(String path) {
		return path.replaceAll("\\\\", "/");
	}

	private static void populateFilesList(File dir) throws IOException {
		File[] files = dir.listFiles();
		File[] var5 = files;
		int var4 = files.length;

		for (int var3 = 0; var3 < var4; ++var3) {
			File file = var5[var3];
			if (file.isFile()) {
				filesListInDir.add(Paths.get(normalizePath(file.getAbsolutePath()), new String[0]));
			} else {
				populateFilesList(file);
			}
		}

	}

	public static Path createNewFile(Path newFilePath) {
		try {
			if (newFilePath.getParent() != null && !newFilePath.getParent().toFile().exists()) {
				Files.createDirectories(newFilePath.getParent(), new FileAttribute[0]);
			}

			Files.deleteIfExists(newFilePath);
			return Files.createFile(newFilePath, new FileAttribute[0]);
		} catch (IOException var2) {
			throw new RuntimeException("Cannot create new file [" + newFilePath.toString() + "].", var2);
		}
	}

	public static Path zip(String source, String destination, String archiveName, String rootPath) {
		Path zip = Paths.get(destination.toString(), new String[] { archiveName });
		filesListInDir = new ArrayList();
		zip = createNewFile(zip);
		if (rootPath != null && !rootPath.isEmpty()) {
			rootPath = rootPath + File.separator;
		}

		try {
			Throwable var5 = null;
			Object var6 = null;

			try {
				ZipArchiveOutputStream zOus = new ZipArchiveOutputStream(zip.toFile());

				try {
					populateFilesList(Paths.get(source, new String[0]).toFile());
					Iterator var9 = filesListInDir.iterator();

					while (var9.hasNext()) {
						Path fileToZip = (Path) var9.next();
						addFileToZipArchive(zOus, fileToZip,
								rootPath + Paths.get(source, new String[0]).relativize(fileToZip).toString());
					}
				} finally {
					if (zOus != null) {
						zOus.close();
					}

				}
			} catch (Throwable var17) {
				try {
					if (var5 == null) {
						var5 = var17;
					} else if (var5 != var17) {
						var5.addSuppressed(var17);
					}
					throw var5;
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		} catch (Exception var18) {

		}

		return zip;
	}

	public static Path zip(List<String> lists, String destination, String archiveName, String rootPath) {
		Path zip = Paths.get(destination.toString(), new String[] { archiveName });
		filesListInDir = new ArrayList();
		zip = createNewFile(zip);
		if (rootPath != null && !rootPath.isEmpty()) {
			rootPath = rootPath + File.separator;
		}

		try {
			Throwable e = null;
			Object var6 = null;

			try {
				ZipArchiveOutputStream zOus = new ZipArchiveOutputStream(zip.toFile());

				try {
					Iterator var9 = lists.iterator();

					while (var9.hasNext()) {
						String fileToZip = (String) var9.next();
						filesListInDir.add(Paths.get(normalizePath(fileToZip), new String[0]));
					}

					var9 = filesListInDir.iterator();

					while (var9.hasNext()) {
						Path fileToZip1 = (Path) var9.next();
						addFileToZipArchive(zOus, fileToZip1, rootPath + fileToZip1.getFileName().toFile().getName());
					}
				} finally {
					if (zOus != null) {
						zOus.close();
					}

				}
			} catch (Throwable var17) {
				try {
					if (e == null) {
						e = var17;
					} else if (e != var17) {
						e.addSuppressed(var17);
					}

					throw e;
				} catch (Throwable e1) {
					e1.printStackTrace();
				}
			}
		} catch (Exception var18) {
			var18.printStackTrace();
		}

		return zip;
	}

	public static Path zip(List<String> lists, String destination, String archiveName) {
		Path zip = Paths.get(destination.toString(), new String[] { archiveName });
		filesListInDir = new ArrayList();
		zip = createNewFile(zip);

		try {
			Throwable e = null;
			Object var5 = null;

			try {
				ZipArchiveOutputStream zOus = new ZipArchiveOutputStream(zip.toFile());

				try {
					Iterator var8 = lists.iterator();

					while (var8.hasNext()) {
						String fileToZip = (String) var8.next();
						filesListInDir.add(Paths.get(normalizePath(fileToZip), new String[0]));
					}

					var8 = filesListInDir.iterator();

					while (var8.hasNext()) {
						Path fileToZip1 = (Path) var8.next();
						addFileToZipArchive(zOus, fileToZip1,
								Paths.get(destination, new String[0]).relativize(fileToZip1).toString());
					}
				} finally {
					if (zOus != null) {
						zOus.close();
					}

				}
			} catch (Throwable var16) {
				try {
					if (e == null) {
						e = var16;
					} else if (e != var16) {
						e.addSuppressed(var16);
					}

					throw e;
				} catch (Throwable e1) {
					e1.printStackTrace();
				}
			}
		} catch (Exception var17) {
			var17.printStackTrace();
		}

		return zip;
	}

	protected static void addFileToZipArchive(ZipArchiveOutputStream zOus, Path fileToZip, String entryName)
			throws IOException {
		ZipArchiveEntry entry = new ZipArchiveEntry(fileToZip.toFile(), entryName);
		entry.setSize(fileToZip.toFile().length());
		zOus.putArchiveEntry(entry);
		if (entry.isDirectory()) {
			zOus.closeArchiveEntry();
		} else {
			Throwable var4 = null;
			Object var5 = null;

			try {
				InputStream in = Files.newInputStream(fileToZip, new OpenOption[0]);

				try {
					IOUtils.copy(in, zOus);
					zOus.closeArchiveEntry();
				} finally {
					if (in != null) {
						in.close();
					}

				}

			} catch (Throwable var12) {
				try {
					if (var4 == null) {
						var4 = var12;
					} else if (var4 != var12) {
						var4.addSuppressed(var12);
					}
					throw var4;
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		}
	}

//	public static void main(String[] args) {
//		ArrayList lists = new ArrayList();
//		File file = new File("C:\\Users\\PERCITUS 51\\Desktop\\Install\\Android\\asdasd-asdasdasdasda\\9862f6a5e-008380451.zip");
//		lists.add(file);
//		lists.add("C:\\Users\\PERCITUS 51\\Desktop\\uninstall\\Android\\trial-asdasdasdasda.apk");
//		String destination = "C:\\Users\\PERCITUS 51\\Desktop\\Test\\";
//		zip(lists, file.getParentFile().getAbsolutePath(), file.getParentFile().getName()+".zip");
//	}
}
