package edu.austral.ingsis.clifford.file.util;

import edu.austral.ingsis.clifford.file.Directory;
import edu.austral.ingsis.clifford.file.File;
import edu.austral.ingsis.clifford.file.SimpleFile;
import java.util.ArrayList;
import java.util.List;

public class VirtualFileSystem implements FileSystem {

  private final Directory root;
  private Directory currentDirectory;

  public VirtualFileSystem() {
    this.root = new Directory("/", null);
    this.currentDirectory = root;
  }

  @Override
  public Directory getCurrentDirectory() {
    return currentDirectory;
  }

  @Override
  public FileModificationResult changeDirectory(String name) {
    File child = currentDirectory.getChild(name);
    if (child != null && child.isDirectory()) {
      currentDirectory = (Directory) child;
      return new FileModificationResult.Success("Moved to " + name);
    }
    return new FileModificationResult.Error("'" + name + "'" + " does not exist");
  }

  @Override
  public FileModificationResult createFile(String name) {
    if (currentDirectory.getChild(name) != null) {
      return new FileModificationResult.Error("File already exists: " + name);
    }
    return currentDirectory.addChild(new SimpleFile(name, currentDirectory));
  }

  @Override
  public FileModificationResult createDirectory(String name) {
    if (currentDirectory.getChild(name) != null) {
      return new FileModificationResult.Error("Directory already exists: " + name);
    }
    return currentDirectory.addChild(new Directory(name, currentDirectory));
  }

  @Override
  public FileModificationResult remove(String name, boolean recursive) {
    return currentDirectory.removeChild(name, recursive);
  }

  @Override
  public List<File> listFiles() {
    return new ArrayList<>(currentDirectory.getChildren());
  }
}
