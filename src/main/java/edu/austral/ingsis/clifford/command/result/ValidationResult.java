package edu.austral.ingsis.clifford.command.result;

public sealed interface ValidationResult permits
    ValidationResult.Success,
    ValidationResult.Failure {

  record Success() implements ValidationResult {}
  record Failure(String message) implements ValidationResult {}

}
