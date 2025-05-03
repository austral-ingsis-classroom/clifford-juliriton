package edu.austral.ingsis.clifford.command.factory;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.file.FileSystem;
import java.util.Collection;

public interface CommandFactory {

  Command create(Collection<String> flags, Collection<String> args, FileSystem fs);

}
