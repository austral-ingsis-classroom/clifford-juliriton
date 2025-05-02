package edu.austral.ingsis.clifford.command.factory;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.command.LsCommand;
import edu.austral.ingsis.clifford.command.util.CommandFactory;
import edu.austral.ingsis.clifford.file.util.FileSystem;
import java.util.Collection;

class LsCommandFactory implements CommandFactory {

  public LsCommandFactory() {}

  @Override
  public Command create(Collection<String> flags, Collection<String> args, FileSystem fs) {
    String sortOrder = null;

    for (String flag : flags) {
      if (flag.equals("--ord")) {
        break;
      } else if (flag.startsWith("--ord=")) {
        String value = flag.substring("--ord=".length()).toLowerCase();
        if (value.equals("asc") || value.equals("desc")) {
          sortOrder = value;
        }
        break;
      }
    }
    return new LsCommand(sortOrder, fs);
  }
}

