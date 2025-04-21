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
  public boolean isValid() {
    return commandLine.getFlags().isEmpty() && commandLine.getArguments().isEmpty();
  }

  @Override
  public ExecutionResult execute(FileSystem fs) {
    Directory currentDirectory = fs.getCurrentDirectory();
    StringBuilder path = new StringBuilder();

    while (currentDirectory.getParent() != null) {
      path.insert(0, "/" + currentDirectory.getName());
      currentDirectory = currentDirectory.getParent();
    }

    if (path.length() == 0) {
      path.append("/");
    }

    return new ExecutionResult.Success(path.toString());
  }

  @Override
  public String validationError() {
    return "pwd command does not take any arguments or flags";
  }
}
