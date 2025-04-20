package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.List;


public class CommandLineParser {

  public static ParsingResult parse(String input) {
    if (isNullOrEmpty(input)) {
      return new ParsingResult.InvalidCommandLine("Input is empty");
    }

    String[] commandParts = getParts(input);
    CommandLine commandLine = buildCommandLine(commandParts);
    return new ParsingResult.ValidCommandLine(commandLine, "Command Line has correct syntax");
  }

  private static boolean isNullOrEmpty(String input) {
    return input == null || input.trim().isEmpty();
  }

  private static String[] getParts(String input) {
    return input.trim().split("\\s+");
  }

  private static CommandLine buildCommandLine(String[] commandLineParts) {
    String command = commandLineParts[0];
    List<String> flags = new ArrayList<>();
    List<String> arguments = new ArrayList<>();

    for (int i = 1; i < commandLineParts.length; i++) {
      String part = commandLineParts[i];
      if (part.startsWith("--")) {
        flags.add(part);
      } else {
        arguments.add(part);
      }
    }
    return new CommandLine(command, flags, arguments);
  }

}
