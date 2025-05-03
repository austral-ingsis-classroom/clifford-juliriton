package edu.austral.ingsis.clifford.command.factory;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.command.TouchCommand;
import edu.austral.ingsis.clifford.file.FileSystem;
import java.util.Collection;

public class TouchCommandFactory implements CommandFactory {

  public TouchCommandFactory() {}

  @Override
  public Command create(Collection<String> flags, Collection<String> args, FileSystem fs) {
    String fileName = args.iterator().next();
    return new TouchCommand(fileName, fs);
  }
}