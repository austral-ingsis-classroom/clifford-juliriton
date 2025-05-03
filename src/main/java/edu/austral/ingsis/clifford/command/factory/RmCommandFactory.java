package edu.austral.ingsis.clifford.command.factory;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.command.RmCommand;
import edu.austral.ingsis.clifford.file.FileSystem;
import java.util.Collection;

public class RmCommandFactory implements CommandFactory {

  public RmCommandFactory() {}

  @Override
  public Command create(Collection<String> flags, Collection<String> args, FileSystem fs) {
    String name = args.iterator().next();
    boolean recursive = flags.contains("--recursive");
    return new RmCommand(name, recursive, fs);
  }
}
