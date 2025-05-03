package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.file.Directory;
import edu.austral.ingsis.clifford.file.FileSystem;
import edu.austral.ingsis.clifford.file.FileSystemItem;
import edu.austral.ingsis.clifford.result.ExecutionResult;

public class RmCommand implements Command {
  private final String name;
  private final boolean recursive;
  private final FileSystem fs;

  public RmCommand(String name, boolean recursive, FileSystem fs) {
    this.name = name;
    this.recursive = recursive;
    this.fs = fs;
  }

  @Override
  public ExecutionResult execute() {
    Directory current = fs.getCurrentDirectory();
    FileSystemItem item = current.getItem(name);

    if (item == null) {
      return new ExecutionResult.Failure("'" + name + "' does not exist");
    }

    if (item.isDirectory() && !recursive) {
      return new ExecutionResult.Failure("cannot remove '" + name + "', is a directory");
    }

    FileSystem newFs = fs.removeItem(name, recursive);
    return new ExecutionResult.Success(newFs, "'" + name + "' removed");
  }
}