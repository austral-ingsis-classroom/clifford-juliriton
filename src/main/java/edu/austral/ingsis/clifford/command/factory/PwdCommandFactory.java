package edu.austral.ingsis.clifford.command.factory;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.command.PwdCommand;
import edu.austral.ingsis.clifford.command.util.CommandFactory;
import edu.austral.ingsis.clifford.file.util.FileSystem;

import java.util.Collection;

public class PwdCommandFactory implements CommandFactory {

  @Override
  public Command create(Collection<String> flags, Collection<String> args, FileSystem fs) {
    return new PwdCommand(fs);
  }
}
