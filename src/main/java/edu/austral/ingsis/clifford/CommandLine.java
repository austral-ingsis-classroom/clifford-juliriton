package edu.austral.ingsis.clifford;

import java.util.List;

public class CommandLine {
  private final String command;
  private final List<String> flags;
  private final List<String> arguments;

  public CommandLine(String command, List<String> flags, List<String> arguments) {
    this.command = command;
    this.flags = flags;
    this.arguments = arguments;
  }

  public String getCommand() {
    return command;
  }

  public List<String> getFlags() {
    return flags;
  }

  public List<String> getArguments() {
    return arguments;
  }
}
