package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.CommandLine;
import edu.austral.ingsis.clifford.ExecutionResult;
import edu.austral.ingsis.clifford.file.util.FileModificationResult;
import edu.austral.ingsis.clifford.file.util.FileSystem;

public class TouchCommand implements Command {
  private final CommandLine commandLine;

  public TouchCommand(CommandLine commandLine) {
    this.commandLine = commandLine;
  }

  @Override
  public String name() {
    return "TOUCH";
  }

  @Override
  public boolean isValid() {
    String fileName = commandLine.getArguments().get(0);
    return commandLine.getArguments().size() == 1
        && !fileName.contains("/")
        && !fileName.contains(" ");
  }

  @Override
  public ExecutionResult execute(FileSystem fs) {
    String fileName = commandLine.getArguments().get(0);
    FileModificationResult result = fs.createFile(fileName);

    return switch (result) {
      case FileModificationResult.Success success ->
          new ExecutionResult.Success("'" + fileName + "' file created");
      case FileModificationResult.Error error -> new ExecutionResult.Error("Error creating file");
    };
  }

  @Override
  public String validationError() {
    return "Invalid file name for touch command";
  }
}
