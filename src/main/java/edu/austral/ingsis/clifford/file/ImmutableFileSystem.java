package edu.austral.ingsis.clifford.file;

import edu.austral.ingsis.clifford.result.FileModificationResult;
import java.util.ArrayList;
import java.util.HashMap;

public class ImmutableFileSystem implements FileSystem {
  private final Directory root;
  private final Directory current;

  public ImmutableFileSystem() {
    this.root = new Directory("", null, new HashMap<>(), new ArrayList<>());
    this.current = root;
  }

  private ImmutableFileSystem(Directory root, Directory current) {
    this.root = root;
    this.current = current;
  }

  @Override
  public FileModificationResult changeDirectory(String path) {
    if (path.equals("/")) {
      return stayAtRoot();
    }

    if (path.equals("..")) {
      return changeToParent();
    }

    // path con muchos directorios
    Directory newDir;
    if (path.contains("/")) {
      newDir = resolveAbsolutePath(path);
      if (newDir == null) {
        return new FileModificationResult.Failure("'" + path + "' directory does not exist");
      }
    } else {
      // path simple
      FileSystemItem item = current.getItem(path);
      if (item == null || !item.isDirectory()) {
        return new FileModificationResult.Failure("'" + path + "' directory does not exist");
      } else {
        newDir = (Directory) item;
      }
    }

    String outputName = path;
    if (path.contains("/")) {
      String[] parts = path.split("/");
      outputName = parts[parts.length - 1];
    }

    ImmutableFileSystem newFs = new ImmutableFileSystem(root, newDir);
    return new FileModificationResult.Success(newFs, "moved to directory '" + outputName + "'");
  }

  private FileModificationResult.Success stayAtRoot() {
    ImmutableFileSystem newFs = new ImmutableFileSystem(root, root);
    return new FileModificationResult.Success(newFs, "moved to directory '/'");
  }

  private FileModificationResult.Success changeToParent() {
    if (current.getParent() == null) {
      return stayAtRoot();
    } else {
      Directory parent = current.getParent();
      ImmutableFileSystem newFs = new ImmutableFileSystem(root, parent);
      return new FileModificationResult.Success(newFs, "moved to directory '/'");
    }
  }

  private Directory resolveAbsolutePath(String path) {

    if (!path.startsWith("/")) {
      path = "/" + path;
    }

    if (path.equals("/")) {
      return root;
    }

    String[] parts = splitDirectories(path);
    Directory pointer = root;

    pointer = getTargetDirectory(parts, pointer);

    return pointer;
  }

  private static String[] splitDirectories(String path) {
    String[] parts = path.split("/");
    return parts;
  }

  private static Directory getTargetDirectory(String[] parts, Directory pointer) {
    for (String part : parts) {
      if (part.isEmpty() || part.equals(".")) {
        continue;
      }

      if (part.equals("..")) {
        pointer = pointer.getParent();
        continue;
      }

      FileSystemItem item = pointer.getItem(part);
      if (item == null || !item.isDirectory()) {
        return null;
      }

      pointer = (Directory) item;
    }
    return pointer;
  }

  @Override
  public FileSystem createFile(String fileName) {
    if (current.containsItem(fileName)) {
      return this;
    }

    File newFile = new File(fileName, current);
    Directory updatedCurrent = current.addItem(newFile);

    if (current == root) {
      return new ImmutableFileSystem(updatedCurrent, updatedCurrent);
    }

    Directory updatedRoot = rebuildPath(current, updatedCurrent);
    Directory newCurrent = findDirectoryInTree(updatedRoot, current.getPath());

    return new ImmutableFileSystem(updatedRoot, newCurrent);
  }

  @Override
  public FileSystem createDirectory(String dirName) {
    if (current.containsItem(dirName)) {
      return this;
    }

    Directory newDir = new Directory(dirName, current, new HashMap<>(), new ArrayList<>());
    Directory updatedCurrent = current.addItem(newDir);

    if (current == root) {
      return new ImmutableFileSystem(updatedCurrent, updatedCurrent);
    }

    Directory updatedRoot = rebuildPath(current, updatedCurrent);
    Directory newCurrent = findDirectoryInTree(updatedRoot, current.getPath());

    return new ImmutableFileSystem(updatedRoot, newCurrent);
  }

  @Override
  public FileSystem removeItem(String name, boolean recursive) {
    Directory currentDir = getCurrentDirectory();
    FileSystemItem item = currentDir.getItem(name);

    if (item == null) {
      return this;
    }

    if (item.isDirectory() && !recursive) {
      return this;
    }

    Directory updatedCurrent = currentDir.removeItem(name);

    if (currentDir == root) {
      return new ImmutableFileSystem(updatedCurrent, updatedCurrent);
    }

    Directory updatedRoot = rebuildPath(currentDir, updatedCurrent);
    Directory newCurrent = findDirectoryInTree(updatedRoot, currentDir.getPath());

    return new ImmutableFileSystem(updatedRoot, newCurrent);
  }

  @Override
  public String getCurrentPath() {
    return current.getPath();
  }

  @Override
  public Directory getCurrentDirectory() {
    return current;
  }

  private Directory findDirectoryInTree(Directory root, String path) {
    if (path.equals("/")) {
      return root;
    }

    String[] parts = path.startsWith("/") ? path.substring(1).split("/") : path.split("/");
    Directory pointer = root;

    for (String part : parts) {
      if (part.isEmpty() || part.equals(".")) {
        continue;
      }

      if (part.equals("..")) {
        if (pointer.getParent() != null) {
          pointer = pointer.getParent();
        }
        continue;
      }

      FileSystemItem item = pointer.getItem(part);
      if (item == null || !item.isDirectory()) {
        return null;
      }

      pointer = (Directory) item;
    }

    return pointer;
  }

  private Directory rebuildPath(Directory original, Directory updated) {
    Directory parent = original.getParent();

    if (parent == null) {
      return updated;
    }

    Directory updatedParent = parent.removeItem(original.getName()).addItem(updated);

    if (parent == root) {
      return updatedParent;
    }

    return rebuildPath(parent, updatedParent);
  }
}
