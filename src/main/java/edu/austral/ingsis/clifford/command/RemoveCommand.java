package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.CommandLine;
import edu.austral.ingsis.clifford.ExecutionResult;
import edu.austral.ingsis.clifford.file.util.FileModificationResult;
import edu.austral.ingsis.clifford.file.util.FileSystem;
import java.util.List;

public class RemoveCommand implements Command {
  private final CommandLine commandLine;

  public RemoveCommand(CommandLine commandLine) {
    this.commandLine = commandLine;
  }

  @Override
  public boolean isValid() {
    List<String> args = commandLine.getArguments();
    List<String> flags = commandLine.getFlags();

    if (args.size() != 1) {
      return false;
    }
    if (flags.isEmpty()) {
      return true;
    }
    return flags.size() == 1 && flags.get(0).equals("--recursive");
  }


  @Override
  public ExecutionResult execute(FileSystem fs) {
    String file = commandLine.getArguments().get(0);

    FileModificationResult result = removeRecursivelyElseRemove(fs, file);

    return switch (result) {
      case FileModificationResult.Success success ->
          new ExecutionResult.Success("'" + file + "' removed");
      case FileModificationResult.Error error ->
          new ExecutionResult.Error(error.message());
    };
  }

  private FileModificationResult removeRecursivelyElseRemove(FileSystem fs, String file) {
    boolean recursive = commandLine.getFlags().size() == 1 &&
        "--recursive".equals(commandLine.getFlags().get(0));

    return fs.remove(file, recursive);
  }

  @Override
  public String validationError() {
    return "Invalid file or directory name for rm command";
  }

}
