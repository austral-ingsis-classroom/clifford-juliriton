package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.CommandLine;
import edu.austral.ingsis.clifford.ExecutionResult;
import edu.austral.ingsis.clifford.file.util.FileModificationResult;
import edu.austral.ingsis.clifford.file.util.FileSystem;

public class MakeDirectoryCommand implements Command {
  private final CommandLine commandLine;

  public MakeDirectoryCommand(CommandLine commandLine) {
    this.commandLine = commandLine;
  }

  @Override
  public boolean isValid() {
    return commandLine.getFlags().isEmpty()
        && commandLine.getArguments().size() == 1
        && !commandLine.getArguments().get(0).contains("/")
        && !commandLine.getArguments().get(0).contains(" ");
  }

  @Override
  public ExecutionResult execute(FileSystem fs) {
    String directory = commandLine.getArguments().get(0);
    FileModificationResult result = fs.createDirectory(directory);

    return switch (result) {
      case FileModificationResult.Success success ->
          new ExecutionResult.Success("'" + directory + "' directory created");
      case FileModificationResult.Error error -> new ExecutionResult.Error(error.message());
    };
  }

  @Override
  public String validationError() {
    return "Invalid directory name for mkdir command : Names cannot contain '/' or spaces";
  }
}
