package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.file.util.FileSystem;
import edu.austral.ingsis.clifford.command.result.ExecutionResult;

public class RmCommand implements Command {
  private final FileSystem fs;
  private final String name;
  private final boolean recursive;

  public RmCommand(String name, boolean recursive, FileSystem fs) {
    this.fs = fs;
    this.name = name;
    this.recursive = recursive;
  }

  @Override
  public ExecutionResult execute() {
    FileSystem newFs = fs.removeItem(name, recursive);
    return new ExecutionResult.Success(newFs, name + "item removed");
  }
}
