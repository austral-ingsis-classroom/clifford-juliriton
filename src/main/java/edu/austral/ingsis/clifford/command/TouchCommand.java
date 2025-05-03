package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.file.FileSystem;
import edu.austral.ingsis.clifford.result.ExecutionResult;

public class TouchCommand implements Command {
  private final FileSystem fs;
  private final String fileName;

  public TouchCommand(String fileName, FileSystem fs) {
    this.fileName = fileName;
    this.fs = fs;
  }

  @Override
  public ExecutionResult execute() {
    FileSystem result = fs.createFile(fileName);
    return new ExecutionResult.Success(result, "'" + fileName + "' file created");
  }
}
