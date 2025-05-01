package edu.austral.ingsis.clifford.file;

import edu.austral.ingsis.clifford.Result;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Directory implements FileSystemItem {
  private final String name;
  private final Map<String, FileSystemItem> children;
  private final List<FileSystemItem> childrenByCreationOrder;
  private final Directory parent;

  public Directory(String name, Directory parent) {
    this.name = name;
    this.parent = parent;
    this.children = new HashMap<>();
    this.childrenByCreationOrder = new ArrayList<>();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public boolean isDirectory() {
    return true;
  }

  @Override
  public String getPath() {
    if (parent == null || parent.getName().isEmpty()) {
      return "/" + name;
    }
    return parent.getPath() + "/" + name;
  }

  @Override
  public Directory getParent() {
    return parent;
  }

  public Result addItem(FileSystemItem item) {
    if (item == null) {
      return new Result.Failure("Cannot add null item.");
    }
    if (children.containsKey(item.getName())) {
      return new Result.Failure("Item with the same name already exists.");
    }
    children.put(item.getName(), item);
    childrenByCreationOrder.add(item);
    return new Result.Success(item.getName() + "was added.");
  }

  public Result removeItem(String name) {
    if (name == null || !children.containsKey(name)) {
      return new Result.Failure("Item not found: " + name);
    }
    FileSystemItem item = children.remove(name);
    childrenByCreationOrder.remove(item);
    return new Result.Success("Item removed: " + name);
  }

  public Result getItem(String name) {
    if (name == null || !children.containsKey(name)) {
      return new Result.Failure("Item not found: " + name);
    }
    return new Result.Value<>(children.get(name));
  }

  public Collection<String> listItems() {
    Collection<String> result = new ArrayList<>();
    for (FileSystemItem item : childrenByCreationOrder) {
      result.add(item.getName());
    }
    return result;
  }

  public Collection<String> listItemsAscending() {
    List<String> result = new ArrayList<>();
    for (FileSystemItem item : childrenByCreationOrder) {
      result.add(item.getName());
    }
    result.sort(Comparator.naturalOrder());
    return result;
  }

  public Collection<String> listItemsDescending() {
    List<String> result = new ArrayList<>();
    for (FileSystemItem item : childrenByCreationOrder) {
      result.add(item.getName());
    }
    result.sort(Comparator.reverseOrder());
    return result;
  }

  public Collection<FileSystemItem> getItems() {
    return List.copyOf(childrenByCreationOrder);
  }
}
