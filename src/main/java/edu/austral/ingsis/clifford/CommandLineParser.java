package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.List;

public class CommandLineParser {
  public static ParsingResult parse(String input) {
    if (isNullOrEmpty(input)) {
      return ParsingResult.invalid("Input is empty");
    }
    String[] parts = split(input);

    String command = parts[0];
    List<String> flags = new ArrayList<>();
    List<String> args = new ArrayList<>();

    for (int i = 1; i < parts.length; i++) {
      if (parts[i].startsWith("--")) flags.add(parts[i]);
      else args.add(parts[i]);
    }
    return ParsingResult.valid(new CommandLine(command, flags, args));
  }

  private static String[] split(String input) {
    return input.trim().split("\\s+");
  }

  private static boolean isNullOrEmpty(String input) {
    return input == null || input.isBlank();
  }
}