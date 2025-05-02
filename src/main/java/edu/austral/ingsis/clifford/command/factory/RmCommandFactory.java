package edu.austral.ingsis.clifford.command.factory;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.command.RmCommand;
import edu.austral.ingsis.clifford.command.util.CommandFactory;
import edu.austral.ingsis.clifford.file.util.FileSystem;

import java.util.Collection;

public class RmCommandFactory implements CommandFactory {

  @Override
  public Command create(Collection<String> flags, Collection<String> args, FileSystem fs) {
    String name = args.iterator().next();
    boolean recursive = flags.contains("--recursive");
    return new RmCommand(name, recursive, fs);
  }
}
