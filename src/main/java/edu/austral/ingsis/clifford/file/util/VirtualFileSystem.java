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
  public FileModificationResult changeDirectory(String path) {
    Directory target = path.startsWith("/") ? root : currentDirectory;

    String[] parts = path.split("/");
    for (String part : parts) {
      if (part.isEmpty() || ".".equals(part)) continue;
      if ("..".equals(part)) {
        if (target.getParent() != null) {
          target = target.getParent();
        } else {
          return new FileModificationResult.Error("No parent directory available");
        }
      } else {
        File child = target.getChild(part);
        if (child != null && child.isDirectory()) {
          target = (Directory) child;
        } else {
          return new FileModificationResult.Error("'" + path + "' directory does not exist");
        }
      }
    }
    currentDirectory = target;
    return new FileModificationResult.Success("moved to directory '" + target.getName() + "'");
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
