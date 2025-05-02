package edu.austral.ingsis.clifford.command.util;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.file.util.FileSystem;

import java.util.Collection;

public interface CommandFactory {

  Command create(Collection<String> flags, Collection<String> args, FileSystem fs);

}
