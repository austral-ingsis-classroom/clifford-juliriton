package edu.austral.ingsis.clifford.command.validator;

import edu.austral.ingsis.clifford.result.ValidationResult;
import java.util.Collection;

public class MkdirCommandValidator implements CommandValidator {

  public MkdirCommandValidator() {}

  @Override
  public ValidationResult validate(Collection<String> args, Collection<String> flags) {
    if (!flags.isEmpty()) {
      return new ValidationResult.Failure("mkdir command take no flags");
    }

    if (args.size() != 1) {
      return new ValidationResult.Failure("mkdir command requires exactly one argument");
    }

    String dirName = args.iterator().next();
    if (dirName.contains("/") || dirName.contains(" ")) {
      return new ValidationResult.Failure("directory names can't contain '/' or white spaces");
    }

    return new ValidationResult.Success();
  }
}
