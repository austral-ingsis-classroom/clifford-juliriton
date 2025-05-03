package edu.austral.ingsis.clifford.command.validator;

import edu.austral.ingsis.clifford.result.ValidationResult;
import java.util.Collection;

public class RmCommandValidator implements CommandValidator {

  public RmCommandValidator() {}

  @Override
  public ValidationResult validate(Collection<String> args, Collection<String> flags) {
    if (args.size() != 1) {
      return new ValidationResult.Failure("rm requires exactly one argument");
    }

    for (String flag : flags) {
      if (!flag.equals("--recursive")) {
        return new ValidationResult.Failure("unknown flag: " + flag + "for rm command");
      }
    }

    return new ValidationResult.Success();
  }
}
