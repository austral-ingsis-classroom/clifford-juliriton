package edu.austral.ingsis.clifford;

public class CommandParser {

  public static Result parse(String input) {
    if (input == null || input.trim().isEmpty()) {
      return new Result.InvalidCommand("Input is empty");
    }

    String trimmed = input.trim();
    String[] parts = trimmed.split("\\s+");

    String commandString = parts[0];

    //Falta validacion de que el comando existe y que los flags son validos y existen

    return new Result.ValidCommand(parts);
  }
}
