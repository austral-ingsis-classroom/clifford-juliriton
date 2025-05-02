package edu.austral.ingsis.clifford.command.validator;

import edu.austral.ingsis.clifford.command.result.ValidationResult;
import java.util.Collection;

public interface CommandValidator {
  ValidationResult validate(Collection<String> args, Collection<String> flags);

}
