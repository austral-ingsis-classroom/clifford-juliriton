package edu.austral.ingsis.clifford;

public class CommandLineInterface {

  private String lastInput;
  private String lastOutput;

  public CommandLineInterface() {}

  public String input(String input) {
    this.lastInput = input;
    ParsingResult output = CommandValidator.validate(input);

    switch (output) {
      case ParsingResult.ValidCommandLine valid -> {
        this.lastOutput = executeCommandLine(valid.command());
      }
      case ParsingResult.InvalidCommandLine invalid -> {
        this.lastOutput = invalid.message(); // Caching
      }
    }
    return lastOutput;
  }

  private String executeCommandLine(CommandLine commandLine) {
    return null;
  }

  public String getLastInput() {
    return lastInput;
  }

  public String getLastOutput() {
    return lastOutput;
  }
}
