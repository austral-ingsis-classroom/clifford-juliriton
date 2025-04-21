package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.CommandLine;
import edu.austral.ingsis.clifford.ExecutionResult;
import edu.austral.ingsis.clifford.file.Directory;
import edu.austral.ingsis.clifford.file.util.FileSystem;

public class PrintWorkingDirectoryCommand implements Command {
  private final CommandLine commandLine;

  public PrintWorkingDirectoryCommand(CommandLine commandLine) {
    this.commandLine = commandLine;
  }

  @Override
  public String name() {
    return "PWD";
  }

  @Override
  public boolean isValid() {
    return commandLine.getFlags().isEmpty() && commandLine.getArguments().isEmpty();
  }

  @Override
  public ExecutionResult execute(FileSystem fs) {
    Directory currentDirectory = fs.getCurrentDirectory();
    return new ExecutionResult.Success("/" + currentDirectory.getName());
  }

  @Override
  public String validationError() {
    return "pwd command does not take any arguments or flags";
  }
}
