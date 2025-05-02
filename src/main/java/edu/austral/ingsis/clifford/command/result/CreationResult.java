package edu.austral.ingsis.clifford.command.result;

import edu.austral.ingsis.clifford.command.Command;

public sealed interface CreationResult permits CreationResult.Success, CreationResult.Failure {

  record Success(Command command) implements CreationResult {}

  record Failure(String message) implements CreationResult {}

}