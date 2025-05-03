package edu.austral.ingsis.clifford.command.validator;

import edu.austral.ingsis.clifford.result.ValidationResult;
import java.util.Collection;

public class PwdCommandValidator implements CommandValidator {

  public PwdCommandValidator() {}

  @Override
  public ValidationResult validate(Collection<String> args, Collection<String> flags) {
    if (!args.isEmpty()) {
      return new ValidationResult.Failure("pwd command takes no arguments");
    }

    if (!flags.isEmpty()) {
      return new ValidationResult.Failure("pwd command takes no flags ");
    }

    return new ValidationResult.Success();
  }
}
