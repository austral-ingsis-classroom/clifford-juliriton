package edu.austral.ingsis.clifford.command.factory;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.command.MkdirCommand;
import edu.austral.ingsis.clifford.command.util.CommandFactory;
import edu.austral.ingsis.clifford.file.util.FileSystem;

import java.util.Collection;

public class MkdirCommandFactory implements CommandFactory {

  @Override
  public Command create(Collection<String> flags, Collection<String> args, FileSystem fs) {
    String dirName = args.iterator().next();
    return new MkdirCommand(fs, dirName);
  }
}
