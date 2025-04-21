package edu.austral.ingsis.clifford.command.util;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.ExecutionResult;
import edu.austral.ingsis.clifford.file.util.FileSystem;

public class CommandExecutor {
  public static ExecutionResult execute(FileSystem fs, Command command) {
    return command.execute(fs);
  }
}
