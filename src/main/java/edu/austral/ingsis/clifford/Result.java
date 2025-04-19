package edu.austral.ingsis.clifford;

public sealed interface Result permits Result.ValidCommand, Result.InvalidCommand {

  record ValidCommand(String[] command) implements Result {}
  record InvalidCommand(String message) implements Result {}
}
