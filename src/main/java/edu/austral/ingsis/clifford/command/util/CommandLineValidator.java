package edu.austral.ingsis.clifford.command.util;

import edu.austral.ingsis.clifford.CommandLine;
import edu.austral.ingsis.clifford.ValidationResult;
import edu.austral.ingsis.clifford.command.Command;

public class CommandLineValidator {
  public static ValidationResult validate(CommandLine commandLine) {
    CommandType type = getCommandType(commandLine);

    if (type == null) {
      return new ValidationResult.Invalid("Unknown command: " + commandLine.getCommand());
    }

    Command command = createCommand(commandLine, type);
    if (!command.isValid()) {
      return new ValidationResult.Invalid(command.validationError());
    }

    return new ValidationResult.Valid(commandLine, command);
  }

  private static CommandType getCommandType(CommandLine commandLine) {
    CommandType type = CommandType.from(commandLine.getCommand());
    return type;
  }

  private static Command createCommand(CommandLine commandLine, CommandType type) {
    Command command = type.create(commandLine);
    return command;
  }

}
