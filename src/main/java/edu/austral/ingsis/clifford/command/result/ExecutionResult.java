package edu.austral.ingsis.clifford.command.result;

import edu.austral.ingsis.clifford.file.util.FileSystem;

public sealed interface ExecutionResult permits ExecutionResult.Success, ExecutionResult.Failure {

  public String getMessage();

  record Success(FileSystem fs, String message) implements ExecutionResult {
    @Override
    public String getMessage() {
      return message;
    }
  }

  record Failure(String message) implements ExecutionResult {
    @Override
    public String getMessage() {
      return message;
    }
  }

}