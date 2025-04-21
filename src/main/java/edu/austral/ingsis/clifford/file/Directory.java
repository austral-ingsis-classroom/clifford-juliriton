package edu.austral.ingsis.clifford.file;

import edu.austral.ingsis.clifford.file.util.FileModificationResult;
import java.util.ArrayList;
import java.util.List;

public class Directory implements File {
  private final String name;
  private final Directory parent;
  private final List<File> children;

  public Directory(String name, Directory parent) {
    this.name = name;
    this.parent = parent;
    this.children = new ArrayList<>();
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
    return true;
  }

  @Override
  public String getPath() {
    if (parent == null) return "/";
    String parentPath = parent.getPath();
    return (parentPath.endsWith("/") ? parentPath : parentPath + "/") + name;
  }

  public File getChild(String name) {
    for (File child : children) {
      if (child.getName().equals(name)) {
        return child;
      }
    }
    return null;
  }

  public List<File> getChildren() {
    return children;
  }

  public FileModificationResult addChild(File file) {
    if (getChild(file.getName()) != null) {
      return new FileModificationResult.Error("File already exists: " + file.getName());
    }
    children.add(file);
    return new FileModificationResult.Success("File created: " + file.getName());
  }

  public FileModificationResult removeChild(String name, boolean recursive) {
    File target = getChild(name);
    if (target == null) {
      return new FileModificationResult.Error("File not found: " + name);
    }

    if (target.isDirectory()) {
      Directory dir = (Directory) target;
      if (!recursive && !dir.getChildren().isEmpty()) {
        return new FileModificationResult.Error("Directory not empty: " + name);
      }
    }

    children.remove(target);
    return new FileModificationResult.Success("Removed: " + name);
  }
}
