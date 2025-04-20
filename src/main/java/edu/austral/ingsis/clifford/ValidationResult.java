package edu.austral.ingsis.clifford;

public sealed interface ValidationResult permits
    ValidationResult.ValidCommand,
    ValidationResult.InvalidCommand,
    ValidationResult.ValidFlag,
    ValidationResult.InvalidFlag,
    ValidationResult.ValidArgument,
    ValidationResult.InvalidArgument{

  record ValidCommand(Command command, String message) implements ValidationResult {}
  record InvalidCommand(String message) implements ValidationResult {}
  record ValidFlag(Flag flag, String message) implements ValidationResult {}
  record InvalidFlag(String message) implements ValidationResult {}
  record ValidArgument(Argument argument, String message) implements ValidationResult {}
  record InvalidArgument(String message) implements ValidationResult {}
}
