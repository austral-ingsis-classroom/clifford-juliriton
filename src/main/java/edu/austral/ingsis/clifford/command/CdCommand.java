package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.command.result.ExecutionResult;
import edu.austral.ingsis.clifford.file.util.FileSystem;

public class CdCommand implements Command {
  private final FileSystem fs;
  private final String targetPath;

  public CdCommand(String targetPath, FileSystem fs) {
    this.targetPath = targetPath;
    this.fs = fs;
  }

  @Override
  public ExecutionResult execute() {
    String newPath = fs.changeDirectory(targetPath);
    return new ExecutionResult.Success(fs, String.format("moved to directory: '%s'", newPath));
  }
}