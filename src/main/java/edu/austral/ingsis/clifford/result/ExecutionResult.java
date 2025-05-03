package edu.austral.ingsis.clifford.result;

import edu.austral.ingsis.clifford.file.FileSystem;

public sealed interface ExecutionResult permits ExecutionResult.Success, ExecutionResult.Failure {

  record Success(FileSystem fs, String output) implements ExecutionResult {}

  record Failure(String message) implements ExecutionResult {}
}
