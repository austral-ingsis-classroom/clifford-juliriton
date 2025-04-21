package edu.austral.ingsis.clifford.file;

public class SimpleFile implements File {
  private final String name;
  private final Directory parent;

  public SimpleFile(String name, Directory parent) {
    this.name = name;
    this.parent = parent;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Directory getParent() {
    return parent;
  }

  @Override
  public boolean isDirectory() {
    return false;
  }

  @Override
  public String getPath() {
    String parentPath = parent.getPath();
    return (parentPath.endsWith("/") ? parentPath : parentPath + "/") + name;
  }
}
