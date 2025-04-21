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

    String output = switch (parsed) {
      case ParsingResult.Failure failure -> failure.message();
      case ParsingResult.Success success -> {
        CommandLine commandLine = success.commandLine();
        ValidationResult validation = CommandLineValidator.validate(commandLine);
        yield switch (validation) {
          case ValidationResult.Invalid invalid -> invalid.message();
          case ValidationResult.Valid valid -> CommandExecutor.execute(fs, valid.command()).getMessage();
        };
      }
    };

    this.lastOutput = output;
    return output;
  }

  public String getLastInput() {
    return lastInput;
  }

  public String getLastOutput() {
    return lastOutput;
  }
}
