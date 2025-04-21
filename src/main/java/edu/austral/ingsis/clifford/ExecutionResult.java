package edu.austral.ingsis.clifford;

public sealed interface ExecutionResult permits ExecutionResult.Success, ExecutionResult.Error {

  record Success(String message) implements ExecutionResult {}

  record Error(String message) implements ExecutionResult {}

  default String getMessage() {
    return switch (this) {
      case Success success -> success.message();
      case Error error -> error.message();
    };
  }
}
