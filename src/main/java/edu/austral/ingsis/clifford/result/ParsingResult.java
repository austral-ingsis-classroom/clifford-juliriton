package edu.austral.ingsis.clifford.result;

import edu.austral.ingsis.clifford.command.util.CommandParts;

public sealed interface ParsingResult {

  record Success(CommandParts commandParts) implements ParsingResult {}

  record Failure(String message) implements ParsingResult {}
}
