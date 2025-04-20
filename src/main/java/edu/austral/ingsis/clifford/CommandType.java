package edu.austral.ingsis.clifford;

import java.util.Map;
import java.util.Optional;

public enum CommandType {
  LS("ls"),
  CD("cd"),
  TOUCH("touch"),
  MKDIR("mkdir"),
  RM("rm"),
  PWD("pwd");

  private final String keyword;

  CommandType(String keyword) {
    this.keyword = keyword;
  }

  private static final Map<String, CommandType> COMMANDS = Map.ofEntries(
      Map.entry("ls", LS),
      Map.entry("cd", CD),
      Map.entry("touch", TOUCH),
      Map.entry("mkdir", MKDIR),
      Map.entry("rm", RM),
      Map.entry("pwd", PWD)
  );

  public static Optional<CommandType> fromString(String s) {
    return Optional.ofNullable(COMMANDS.get(s));
  }
}
