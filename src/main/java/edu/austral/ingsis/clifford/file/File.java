package edu.austral.ingsis.clifford.file;

public class File implements FileSystemItem {
  private final String name;
  private final Directory parent;

  public File(String name, Directory parent) {
    this.name = name;
    this.parent = parent;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public boolean isDirectory() {
    return false;
  }

  @Override
  public String getPath() {
    String parentPath = parent.getPath();
    if (parentPath.equals("/")) {
      return parentPath + name;
    }
    return parentPath + "/" + name;
  }

  @Override
  public Directory getParent() {
    return parent;
  }
}
