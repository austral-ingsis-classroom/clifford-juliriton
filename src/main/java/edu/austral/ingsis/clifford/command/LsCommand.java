package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.file.Directory;
import edu.austral.ingsis.clifford.file.FileSystem;
import edu.austral.ingsis.clifford.result.ExecutionResult;
import java.util.Collection;

public class LsCommand implements Command {
  private final String sortOrder;
  private final FileSystem fs;

  public LsCommand(String sortOrder, FileSystem fs) {
    this.sortOrder = sortOrder;
    this.fs = fs;
  }

  @Override
  public ExecutionResult execute() {
    Directory currentDir = fs.getCurrentDirectory();
    Collection<String> items;

    if (sortOrder != null) {
      if (sortOrder.equals("asc")) {
        items = currentDir.listItemsAscending();
      } else if (sortOrder.equals("desc")) {
        items = currentDir.listItemsDescending();
      } else {
        items = currentDir.listItems();
      }
    } else {
      items = currentDir.listItems();
    }

    // Critical fix: Return an empty string for empty directories
    if (items.isEmpty()) {
      return new ExecutionResult.Success(fs, "");
    }

    // Join items with space separator
    String output = String.join(" ", items);
    return new ExecutionResult.Success(fs, output);
  }
}