package edu.austral.ingsis.clifford;

import edu.austral.ingsis.clifford.command.Command;

public sealed interface ValidationResult permits
    ValidationResult.Valid,
    ValidationResult.Invalid {

  record Valid(CommandLine commandLine, Command command) implements ValidationResult {
  }

  record Invalid(String message) implements ValidationResult {
  }

}
