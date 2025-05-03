package edu.austral.ingsis.clifford.file;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Directory implements FileSystemItem {
  private final String name;
  private final Directory parent;
  private final Map<String, FileSystemItem> items;
  private final List<String> itemsOrder;

  public Directory(String name,
                   Directory parent,
                   Map<String, FileSystemItem> items,
                   List<String> itemsOrder) {
    this.name = name;
    this.parent = parent;
    this.items = items;
    this.itemsOrder = itemsOrder;
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
    if (parent == null) {
      return "/";
    }
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

  public FileSystemItem getItem(String name) {
    return items.get(name);
  }

  public Directory addItem(FileSystemItem item) {
    Map<String, FileSystemItem> newItems = new HashMap<>(items);
    List<String> newOrder = new ArrayList<>(itemsOrder);

    if (newItems.containsKey(item.getName())) {
      return this;
    }

    newItems.put(item.getName(), item);
    newOrder.add(item.getName());
    return new Directory(this.name, this.parent, newItems, newOrder);
  }

  public Directory removeItem(String name) {
    if (!items.containsKey(name)) {
      return this;
    }

    Map<String, FileSystemItem> newItems = new HashMap<>(items);
    List<String> newOrder = new ArrayList<>(itemsOrder);

    newItems.remove(name);
    newOrder.remove(name);

    return new Directory(this.name, parent, newItems, newOrder);
  }

  public boolean containsItem(String name) {
    return items.containsKey(name);
  }

  public Collection<String> listItems() {
    return new ArrayList<>(itemsOrder);
  }

  public Collection<String> listItemsAscending() {
    List<String> sorted = new ArrayList<>(itemsOrder);
    Collections.sort(sorted);
    return sorted;
  }

  public Collection<String> listItemsDescending() {
    List<String> sorted = new ArrayList<>(itemsOrder);
    sorted.sort(Collections.reverseOrder());
    return sorted;
  }

}