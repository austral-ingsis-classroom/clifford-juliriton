package edu.austral.ingsis.clifford.file.util;

import edu.austral.ingsis.clifford.file.Directory;

public class ImmutableFileSystem implements FileSystem {

  @Override
  public String listItems(String orderOption) {
    return "";
  }

  @Override
  public String changeDirectory(String path) {
    return "";
  }

  @Override
  public FileSystem createFile(String fileName) {
    return null;
  }

  @Override
  public FileSystem createDirectory(String dirName) {
    return null;
  }

  @Override
  public FileSystem removeItem(String path, boolean recursive) {
    return null;
  }

  @Override
  public String getCurrentPath() {
    return "";
  }

  @Override
  public Directory getCurrentDirectory() {
    return null;
  }
}