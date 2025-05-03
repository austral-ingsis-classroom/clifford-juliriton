package edu.austral.ingsis.clifford;

import edu.austral.ingsis.clifford.command.util.CommandExecutor;
import edu.austral.ingsis.clifford.command.util.CommandLineParser;
import edu.austral.ingsis.clifford.command.util.CommandParts;
import edu.austral.ingsis.clifford.file.FileSystem;
import edu.austral.ingsis.clifford.result.ExecutionResult;
import edu.austral.ingsis.clifford.result.ParsingResult;

public class CliClient {
  private FileSystem fs;

  public CliClient(FileSystem fs) {
    this.fs = fs;
  }

  public String run(String input) {
    ParsingResult parsing = CommandLineParser.parse(input);
    return switch (parsing) {
      case ParsingResult.Failure failure -> failure.message();
      case ParsingResult.Success success -> executeCommand(success.commandParts());
    };
  }

  private String executeCommand(CommandParts parts) {
    ExecutionResult result = CommandExecutor.execute(fs,
                                                     parts.getCommandName(),
                                                     parts.getArgs(),
                                                     parts.getFlags());
    return handleExecutionResult(result);
  }

  private String handleExecutionResult(ExecutionResult result) {
    return switch (result) {
      case ExecutionResult.Success successResult -> {
        fs = successResult.fs();
        yield successResult.output();
      }
      case ExecutionResult.Failure failureResult -> failureResult.message();
    };
  }
}
