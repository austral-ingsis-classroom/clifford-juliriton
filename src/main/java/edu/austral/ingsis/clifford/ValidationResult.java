package edu.austral.ingsis.clifford;

import edu.austral.ingsis.clifford.command.Command;

public sealed interface ValidationResult permits
    ValidationResult.Valid,
    ValidationResult.Invalid {

  boolean isValid();

  record Valid(CommandLine commandLine, Command command) implements ValidationResult {
    @Override
    public boolean isValid() {
      return true;
    }
  }

  record Invalid(String message) implements ValidationResult {
    @Override
    public boolean isValid() {
      return false;
    }
  }
}
