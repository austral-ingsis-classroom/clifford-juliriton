package edu.austral.ingsis.clifford;

public sealed interface ParsingResult permits
    ParsingResult.ValidCommandLine,
    ParsingResult.InvalidCommandLine {

  record ValidCommandLine(CommandLine command, String message) implements ParsingResult {}
  record InvalidCommandLine(String message) implements ParsingResult {}


}
