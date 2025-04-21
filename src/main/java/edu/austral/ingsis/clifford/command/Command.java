package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.ExecutionResult;
import edu.austral.ingsis.clifford.file.util.FileSystem;

public interface Command {

  String name();

  boolean isValid();

  ExecutionResult execute(FileSystem fs);

  String validationError();

}
