package edu.austral.ingsis.clifford.command.validator;

import edu.austral.ingsis.clifford.command.result.ValidationResult;

import java.util.Collection;

public class PwdCommandValidator implements CommandValidator {

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
