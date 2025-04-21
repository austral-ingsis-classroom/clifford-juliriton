package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.CommandLine;
import edu.austral.ingsis.clifford.ExecutionResult;
import edu.austral.ingsis.clifford.file.Directory;
import edu.austral.ingsis.clifford.file.util.FileSystem;
import edu.austral.ingsis.clifford.file.util.FileModificationResult;

import java.util.List;

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
    List<String> args = commandLine.getArguments();

    if (args.size() != 1) return false;

    if (!commandLine.getFlags().isEmpty()) return false;

    String dir = args.get(0);
    if ("..".equals(dir) || ".".equals(dir)) return true;

    return true;
  }

  @Override
  public ExecutionResult execute(FileSystem fs) {
    String dirName = commandLine.getArguments().get(0);

    if ("..".equals(dirName)) {
      return tryToParentDirectory(fs);
    }

    if (".".equals(dirName)) {
      return new ExecutionResult.Success("Already in the current directory");
    }

    return tryToCalledDirectory(fs, dirName);
  }

  private static ExecutionResult tryToCalledDirectory(FileSystem fs, String dirName) {
    FileModificationResult result = fs.changeDirectory(dirName);
    return switch (result) {
      case FileModificationResult.Success success ->
          new ExecutionResult.Success("Moved to directory: '" + dirName + "'");
      case FileModificationResult.Error error -> new ExecutionResult.Error(error.message());
    };
  }

  private ExecutionResult tryToParentDirectory(FileSystem fs) {
    Directory parent = fs.getCurrentDirectory().getParent();
    if (parent != null) {
      fs.changeDirectory(parent.getName());
      return new ExecutionResult.Success("Moved to parent directory");
    } else {
      return new ExecutionResult.Error("No parent directory available");
    }
  }

  @Override
  public String validationError() {
    return "cd command requires a single directory argument and no flags";
  }
}
