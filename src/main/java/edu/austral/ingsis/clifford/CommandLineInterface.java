package edu.austral.ingsis.clifford;

public class CommandLineInterface {

  private String lastInput;
  private String lastOutput;

  public CommandLineInterface() {}

  public Result input(String input) {
    this.lastInput = input;
    Result output = CommandValidator.validate(input);

    switch (output) {
      case Result.ValidCommand valid -> {
        this.lastOutput = executeCommand(valid.command());
      }
      case Result.InvalidCommand invalid -> {
        this.lastOutput = invalid.message();
      }
    }

    return output;
  }

  private String executeCommand(ParsedCommand parsedCommand) {
    return null;
  }

  public String getLastInput() {
    return lastInput;
  }

  public String getLastOutput() {
    return lastOutput;
  }
}
