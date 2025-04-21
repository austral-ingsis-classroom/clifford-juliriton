package edu.austral.ingsis.clifford.file;

public class Directory implements File {
  private final String name;
  private final Directory parent;

  public Directory(String name, Directory parent) {
    this.name = name;
    this.parent = parent;
  }

  @Override
  public String getName() { return name; }

  @Override
  public Directory getParent() { return parent; }

  @Override
  public boolean isDirectory() { return true; }

  @Override
  public String getPath() {
    if (parent == null) return "/";
    String parentPath = parent.getPath();
    return (parentPath.endsWith("/") ? parentPath : parentPath + "/") + name;
  }

}
