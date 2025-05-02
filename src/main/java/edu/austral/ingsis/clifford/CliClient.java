package edu.austral.ingsis.clifford;

/*

Usa Command Pattern:

Defino objetos independientes (comandos) que encapsulen una solicitud

Una clase delega una solicitud a un objeto de comando en lugar de
implementar una solicitud determinada directamente

Esto permite configurar una clase con un objeto de comando que se
utiliza para realizar una solicitud

La clase ya no está acoplada a una solicitud en particular y no
tiene conocimiento (es independiente) de cómo se lleva a cabo la solicitud.

Actua como Client. Interactua con los usuarios y utiliza al invoker para llamar a distintos
comandos

El cliente no sabe ningun detalle de implementacion de los comandos especificos, en especial
como se realiza la operacion de ejecucion

Decide que receivers le asigna a un Command object y que Command objects le asigna a los
invokers

*/

import edu.austral.ingsis.clifford.command.result.ExecutionResult;
import edu.austral.ingsis.clifford.command.result.ParsingResult;
import edu.austral.ingsis.clifford.command.util.*;
import edu.austral.ingsis.clifford.file.util.FileSystem;

import java.util.Collection;

public class CliClient {
  private FileSystem fs;

  public CliClient(FileSystem fs) {
    this.fs = fs;
  }

  public String run(String input) {
    ParsingResult parsing = CommandLineParser.parseCommandLine(input);

    CommandParts parts;
    switch (parsing) {
      case ParsingResult.Success success -> parts = success.commandParts();
      case ParsingResult.Failure failure -> {
        return failure.message();
      }
    }

    String commandName = parts.getCommandName();
    Collection<String> args = parts.getArgs();
    Collection<String> flags = parts.getFlags();

    ExecutionResult execution = CommandExecutor.execute(fs, commandName, args, flags);

    return execution.getMessage();
  }

}
