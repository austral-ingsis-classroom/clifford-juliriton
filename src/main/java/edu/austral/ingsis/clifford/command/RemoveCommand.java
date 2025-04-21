package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.CommandLine;
import edu.austral.ingsis.clifford.ExecutionResult;
import edu.austral.ingsis.clifford.file.util.FileModificationResult;
import edu.austral.ingsis.clifford.file.util.FileSystem;

public class RemoveCommand implements Command {
  private final CommandLine commandLine;

  public RemoveCommand(CommandLine commandLine) {
    this.commandLine = commandLine;
  }

  @Override
  public String name() {
    return "RM";
  }

  @Override
  public boolean isValid() {
    boolean a = commandLine.getArguments().size() == 1;
    boolean b = commandLine.getFlags().isEmpty();
    boolean c = commandLine.getFlags().size() == 1;
    boolean d = commandLine.getFlags().get(0).equals("--recursive");
    return a && b || c && d;
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
    boolean recursive = commandLine.getArguments().size() == 1
        && "--recursive".equals(commandLine.getFlags().get(0));

    FileModificationResult result;

    if (recursive) {
      result = fs.remove(file, true);
    } else {
      result = fs.remove(file, false);
    }
    return result;
  }

  @Override
  public String validationError() {
    return "Invalid file or directory name for rm command";
  }
}
