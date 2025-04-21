package edu.austral.ingsis.clifford.file.util;

import edu.austral.ingsis.clifford.file.Directory;
import edu.austral.ingsis.clifford.file.File;

import java.util.List;

public interface FileSystem {
  Directory getCurrentDirectory();
  FileModificationResult changeDirectory(String path);
  FileModificationResult createFile(String name);
  FileModificationResult createDirectory(String name);
  FileModificationResult remove(String name, boolean recursive);
  List<File> listFiles();
}
