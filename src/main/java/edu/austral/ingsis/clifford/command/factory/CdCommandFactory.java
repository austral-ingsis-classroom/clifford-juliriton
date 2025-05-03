package edu.austral.ingsis.clifford.command.factory;

import edu.austral.ingsis.clifford.command.CdCommand;
import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.file.FileSystem;
import java.util.Collection;

public class CdCommandFactory implements CommandFactory {

  public CdCommandFactory() {}

  @Override
  public Command create(Collection<String> flags, Collection<String> args, FileSystem fs) {
    String path = args.iterator().next();
    return new CdCommand(path, fs);
  }
}
