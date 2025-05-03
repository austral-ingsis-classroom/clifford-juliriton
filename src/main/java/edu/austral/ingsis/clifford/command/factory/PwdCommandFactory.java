package edu.austral.ingsis.clifford.command.factory;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.command.PwdCommand;
import edu.austral.ingsis.clifford.file.FileSystem;
import java.util.Collection;

public class PwdCommandFactory implements CommandFactory {

  public PwdCommandFactory() {}

  @Override
  public Command create(Collection<String> flags, Collection<String> args, FileSystem fs) {
    return new PwdCommand(fs);
  }
}
