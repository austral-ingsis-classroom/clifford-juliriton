package edu.austral.ingsis.clifford;

import edu.austral.ingsis.clifford.command.util.CommandExecutor;
import edu.austral.ingsis.clifford.command.util.CommandLineValidator;
import edu.austral.ingsis.clifford.file.util.FileSystem;

public class CommandLineInterface {
  private final FileSystem fs;

  public CommandLineInterface(FileSystem fs) {
    this.fs = fs;
  }

  public String input(String input) {
    ParsingResult parsed = CommandLineParser.parse(input);

    String output = switch (parsed) {
      case ParsingResult.Failure failure -> failure.message();
      case ParsingResult.Success success -> {
        CommandLine commandLine = success.commandLine();
        ValidationResult validation = CommandLineValidator.validate(commandLine);
        yield switch (validation) {
          case ValidationResult.Invalid invalid ->
              invalid.message();
          case ValidationResult.Valid valid ->
              CommandExecutor.execute(fs, valid.command()).getMessage();
        };
      }
    };
    return output;
  }

}
