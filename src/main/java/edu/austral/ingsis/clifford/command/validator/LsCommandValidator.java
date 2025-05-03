package edu.austral.ingsis.clifford.command.validator;

import edu.austral.ingsis.clifford.result.ValidationResult;
import java.util.Collection;

public class LsCommandValidator implements CommandValidator {

  public LsCommandValidator() {}

  @Override
  public ValidationResult validate(Collection<String> args, Collection<String> flags) {
    if (!args.isEmpty()) {
      return new ValidationResult.Failure("ls command 'ls' takes no arguments");
    }

    for (String flag : flags) {
      if (flag.startsWith("--ord=")) {
        String value = flag.substring("--ord=".length()).toLowerCase();
        if (!value.equals("asc") && !value.equals("desc")) {
          return new ValidationResult.Failure("wrong use of ls command flag --ord: "
              + "'--ord', '--ord=asc' o '--ord=desc'.");
        }
      } else {
        return new ValidationResult.Failure("unknown flag: " + flag + " for ls command");
      }
    }

    return new ValidationResult.Success();
  }

}
