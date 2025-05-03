package edu.austral.ingsis.clifford.command.validator;

import edu.austral.ingsis.clifford.result.ValidationResult;
import java.util.Collection;

public class CdCommandValidator implements CommandValidator {

  public CdCommandValidator() {}

  @Override
  public ValidationResult validate(Collection<String> args, Collection<String> flags) {
    if (args == null || args.isEmpty()) {
      return new ValidationResult.Failure("cd command requires a target directory");
    }

    if (args.size() > 1) {
      return new ValidationResult.Failure("cd command takes only one argument");
    }

    if (flags != null && !flags.isEmpty()) {
      return new ValidationResult.Failure("cd command doesn't support flags");
    }

    return new ValidationResult.Success();
  }

}
