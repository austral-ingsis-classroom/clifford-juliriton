package edu.austral.ingsis.clifford.file.util;

import edu.austral.ingsis.clifford.file.Directory;
import edu.austral.ingsis.clifford.file.File;

import java.util.List;

public class VirtualFileSystem implements FileSystem {
  @Override
  public Directory getCurrentDirectory() {
    return null;
  }

  @Override
  public FileModificationResult changeDirectory(String path) {
    return null;
  }

  @Override
  public FileModificationResult createFile(String name) {
    return null;
  }

  @Override
  public FileModificationResult createDirectory(String name) {
    return null;
  }

  @Override
  public FileModificationResult remove(String name, boolean recursive) {
    return null;
  }

  @Override
  public List<File> listFiles() {
    return List.of();
  }
}
