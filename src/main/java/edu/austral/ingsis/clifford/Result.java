package edu.austral.ingsis.clifford;

public sealed interface Result permits Result.Value, Result.Success, Result.Failure {

  record Value<T>(T value) implements Result {}

  record Success(String message) implements Result {}

  record Failure(String message) implements Result {}

}
