package edu.austral.ingsis.clifford.command.util;

import edu.austral.ingsis.clifford.result.ParsingResult;
import java.util.ArrayList;
import java.util.Collection;

public class CommandLineParser {

  public static ParsingResult parse(String input) {
    if (isNullOrEmpty(input)) {
      return new ParsingResult.Failure("Invalid input");
    }

    String[] parts = split(input);

    String commandName = parts[0];
    Collection<String> flags = new ArrayList<>();
    Collection<String> args = new ArrayList<>();

    CommandParts commandParts = build(parts, commandName, args, flags);
    return new ParsingResult.Success(commandParts);
  }

  private static boolean isNullOrEmpty(String userInput) {
    return userInput == null || userInput.trim().isEmpty();
  }

  private static String[] split(String input) {
    return input.trim().split("\\s+");
  }

  private static CommandParts build(
      String[] parts, String commandName, Collection<String> args, Collection<String> flags) {

    for (int i = 1; i < parts.length; i++) {
      String p = parts[i];
      if (p.startsWith("--")) {
        flags.add(p);
      } else {
        args.add(p);
      }
    }

    return new CommandParts(commandName, args, flags);
  }
}
