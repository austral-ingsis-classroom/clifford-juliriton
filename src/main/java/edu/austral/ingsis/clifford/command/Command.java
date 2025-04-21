package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.ExecutionResult;
import edu.austral.ingsis.clifford.file.util.FileSystem;

public interface Command {

  boolean isValid();

  ExecutionResult execute(FileSystem fs);

  String validationError();

}
