package edu.austral.ingsis.clifford.result;

public sealed interface ValidationResult
    permits ValidationResult.Success, ValidationResult.Failure {

  record Success() implements ValidationResult {}

  record Failure(String message) implements ValidationResult {}
}
