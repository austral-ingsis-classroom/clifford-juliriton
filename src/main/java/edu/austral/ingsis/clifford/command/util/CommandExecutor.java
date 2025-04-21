package edu.austral.ingsis.clifford.command.util;

import edu.austral.ingsis.clifford.*;
import edu.austral.ingsis.clifford.command.*;
import edu.austral.ingsis.clifford.file.util.FileSystem;

public class CommandExecutor {
  public static ExecutionResult execute(FileSystem fs, Command command) {
    return command.execute(fs);
  }
}
