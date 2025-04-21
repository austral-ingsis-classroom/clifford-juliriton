package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.CommandLine;
import edu.austral.ingsis.clifford.ExecutionResult;
import edu.austral.ingsis.clifford.file.util.FileSystem;
import edu.austral.ingsis.clifford.file.util.FileModificationResult;

public class ChangeDirectoryCommand implements Command {
  private final CommandLine commandLine;

  public ChangeDirectoryCommand(CommandLine commandLine) {
    this.commandLine = commandLine;
  }

  @Override
  public String name() {
    return "CD";
  }

  @Override
  public boolean isValid() {
    return commandLine.getArguments().size() == 1 && commandLine.getFlags().isEmpty();
  }

  @Override
  public ExecutionResult execute(FileSystem fs) {
    String dirName = commandLine.getArguments().get(0);
    FileModificationResult result = fs.changeDirectory(dirName);

    return switch (result) {
      case FileModificationResult.Success success ->
          new ExecutionResult.Success("Moved to directory: '" + dirName + "'");
      case FileModificationResult.Error error -> new ExecutionResult.Error(error.message());
    };
  }

  @Override
  public String validationError() {
    return "cd command requires a single directory argument and no flags";
  }
}
