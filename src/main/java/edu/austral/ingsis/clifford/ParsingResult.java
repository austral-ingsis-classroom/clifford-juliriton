package edu.austral.ingsis.clifford;

public sealed interface ParsingResult permits
    ParsingResult.Success,
    ParsingResult.Failure {

  static ParsingResult valid(CommandLine commandLine) {
    return new Success(commandLine);
  }

  static ParsingResult invalid(String message) {
    return new Failure(message);
  }

  record Success(CommandLine commandLine) implements ParsingResult { }

  record Failure(String message) implements ParsingResult { }

}