package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.command.result.ExecutionResult;
import edu.austral.ingsis.clifford.file.util.FileSystem;

public class PwdCommand implements Command {
  private final FileSystem fs;

  public PwdCommand(FileSystem fs) {
    this.fs = fs;
  }

  @Override
  public ExecutionResult execute() {
    String currentPath = fs.getCurrentPath();
    return new ExecutionResult.Success(fs, currentPath);
  }
}
