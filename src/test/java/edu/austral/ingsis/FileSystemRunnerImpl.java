package edu.austral.ingsis;

import edu.austral.ingsis.clifford.command.factory.CdCommandFactory;
import edu.austral.ingsis.clifford.command.factory.LsCommandFactory;
import edu.austral.ingsis.clifford.command.factory.MkdirCommandFactory;
import edu.austral.ingsis.clifford.command.factory.PwdCommandFactory;
import edu.austral.ingsis.clifford.command.factory.RmCommandFactory;
import edu.austral.ingsis.clifford.command.factory.TouchCommandFactory;
import edu.austral.ingsis.clifford.command.util.CommandExecutor;
import edu.austral.ingsis.clifford.command.util.CommandLineParser;
import edu.austral.ingsis.clifford.command.util.CommandParts;
import edu.austral.ingsis.clifford.command.util.CommandRegistry;
import edu.austral.ingsis.clifford.command.validator.CdCommandValidator;
import edu.austral.ingsis.clifford.command.validator.LsCommandValidator;
import edu.austral.ingsis.clifford.command.validator.MkdirCommandValidator;
import edu.austral.ingsis.clifford.command.validator.PwdCommandValidator;
import edu.austral.ingsis.clifford.command.validator.RmCommandValidator;
import edu.austral.ingsis.clifford.command.validator.TouchCommandValidator;
import edu.austral.ingsis.clifford.file.FileSystem;
import edu.austral.ingsis.clifford.file.ImmutableFileSystem;
import edu.austral.ingsis.clifford.result.ExecutionResult;
import edu.austral.ingsis.clifford.result.ParsingResult;
import java.util.ArrayList;
import java.util.List;

public class FileSystemRunnerImpl implements FileSystemRunner {

  static {
    CommandRegistry.register("mkdir",
        new MkdirCommandFactory(),
        new MkdirCommandValidator());
    CommandRegistry.register("ls",
        new LsCommandFactory(),
        new LsCommandValidator());
    CommandRegistry.register("cd",
        new CdCommandFactory(),
        new CdCommandValidator());
    CommandRegistry.register("pwd",
        new PwdCommandFactory(),
        new PwdCommandValidator());
    CommandRegistry.register("touch",
        new TouchCommandFactory(),
        new TouchCommandValidator());
    CommandRegistry.register("rm",
        new RmCommandFactory(),
        new RmCommandValidator());
  }


  @Override
  public List<String> executeCommands(List<String> commands) {
    List<String> results = new ArrayList<>();
    FileSystem fileSystem = new ImmutableFileSystem();

    for (String rawCommand : commands) {
      ParsingResult parsed = CommandLineParser.parse(rawCommand);

      switch (parsed) {
        case ParsingResult.Failure failure -> results.add(failure.message());
        case ParsingResult.Success success -> {
          CommandParts command = success.commandParts();
          ExecutionResult result = CommandExecutor.execute(
              fileSystem,
              command.getCommandName(),
              command.getArgs(),
              command.getFlags()
          );

          switch (result) {
            case ExecutionResult.Success s -> {
              fileSystem = s.fs();
              results.add(s.output());
            }
            case ExecutionResult.Failure f -> results.add(f.message());
          }
        }
      }
    }

    return results;
  }
}
