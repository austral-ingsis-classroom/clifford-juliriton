package edu.austral.ingsis.clifford.command.result;

public sealed interface Result<T> permits Result.Value, Result.Success, Result.Failure {

  record Value<T>(T value) implements Result<T> {}

  record Success(String message) implements Result {}

  record Failure(String message) implements Result {}

}
