package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.file.FileSystem;
import edu.austral.ingsis.clifford.result.ExecutionResult;
import edu.austral.ingsis.clifford.result.FileModificationResult;

public class CdCommand implements Command {
  private final String path;
  private final FileSystem fs;

  public CdCommand(String path, FileSystem fs) {
    this.path = path;
    this.fs = fs;
  }

  @Override
  public ExecutionResult execute() {
    FileModificationResult result = fs.changeDirectory(path);

    return switch (result) {
      case FileModificationResult.Success success ->
          new ExecutionResult.Success(success.fs(), success.output());
      case FileModificationResult.Failure failure ->
          new ExecutionResult.Failure(failure.message());
    };
  }
}