package edu.austral.ingsis.clifford.command.validator;

import edu.austral.ingsis.clifford.command.result.ValidationResult;

import java.util.Collection;

public class TouchCommandValidator implements CommandValidator {

  @Override
  public ValidationResult validate(Collection<String> args, Collection<String> flags) {
    if (args == null || args.isEmpty()) {
      return new ValidationResult.Failure("touch command requires a file name");
    }

    if (args.size() > 1) {
      return new ValidationResult.Failure("touch command takes only one argument");
    }

    String fileName = args.iterator().next();

    if (fileName.contains("/") || fileName.contains(" ")) {
      return new ValidationResult.Failure("File name cannot contain '/' or spaces");
    }

    if (flags != null && !flags.isEmpty()) {
      return new ValidationResult.Failure("touch command doesn't support flags");
    }

    return new ValidationResult.Success();
  }

}