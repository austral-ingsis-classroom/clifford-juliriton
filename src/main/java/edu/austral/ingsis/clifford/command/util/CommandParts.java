package edu.austral.ingsis.clifford.command.util;

import java.util.Collection;

public class CommandParts {
  private final String commandName;
  private final Collection<String> args;
  private final Collection<String> flags;

  public CommandParts(String commandName,
                      Collection<String> args,
                      Collection<String> flags) {
    this.commandName = commandName;
    this.args = args;
    this.flags = flags;
  }

  public String getCommandName() {
    return commandName;
  }

  public Collection<String> getArgs() {
    return args;
  }

  public Collection<String> getFlags() {
    return flags;
  }

}
