package edu.austral.ingsis.clifford;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.command.util.CommandExecutor;
import edu.austral.ingsis.clifford.command.util.CommandLineValidator;
import edu.austral.ingsis.clifford.file.util.FileSystem;

public class CommandLineInterface {
  private final FileSystem fs;
  private String lastInput;
  private String lastOutput;

  public CommandLineInterface(FileSystem fs) {
    this.fs = fs;
  }

  public String input(String input) {
    this.lastInput = input;
    ParsingResult parsed = CommandLineParser.parse(input);

    switch (parsed) {
      case ParsingResult.Failure failure -> {
        lastOutput = failure.message();
        return lastOutput;
      }
      case ParsingResult.Success success -> {
        CommandLine commandLine = success.commandLine();
        ValidationResult validation = CommandLineValidator.validate(commandLine);

        switch (validation) {
          case ValidationResult.Invalid invalid -> {
            lastOutput = invalid.message();
            return lastOutput;
          }
          case ValidationResult.Valid valid -> {
            Command command = valid.command();
            ExecutionResult result = CommandExecutor.execute(fs, command);
            lastOutput = result.getMessage();
            return lastOutput;
          }
        }
      }
    }
  }

  //Caching

  public String getLastInput() {
    return lastInput;
  }

  public String getLastOutput() {
    return lastOutput;
  }
}
