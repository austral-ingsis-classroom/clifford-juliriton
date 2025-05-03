package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.file.FileSystem;
import edu.austral.ingsis.clifford.result.ExecutionResult;

public class MkdirCommand implements Command {
  private final FileSystem fs;
  private final String dirName;

  public MkdirCommand(FileSystem fs, String dirName) {
    this.fs = fs;
    this.dirName = dirName;
  }

  @Override
  public ExecutionResult execute() {
    FileSystem newFs = fs.createDirectory(dirName);
    return new ExecutionResult.Success(newFs, "'" + dirName + "' directory created");
  }
}
