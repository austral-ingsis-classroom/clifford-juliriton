package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.command.result.ExecutionResult;
import edu.austral.ingsis.clifford.file.Directory;
import edu.austral.ingsis.clifford.file.util.FileSystem;
import java.util.Collection;

public class LsCommand implements Command {
  private final FileSystem fs;
  private final String sortOrder;

  public LsCommand(String sortOrder, FileSystem fs) {
    this.sortOrder = sortOrder;
    this.fs = fs;
  }

  @Override
  public ExecutionResult execute() {
    Directory currentDir = fs.getCurrentDirectory();
    Collection<String> items;

    if ("asc".equals(sortOrder)) {
      items = currentDir.listItemsAscending();
    } else if ("desc".equals(sortOrder)) {
      items = currentDir.listItemsDescending();
    } else {
      items = currentDir.listItems();
    }

    return new ExecutionResult.Success(this.fs, String.join(" ", items));
  }
}