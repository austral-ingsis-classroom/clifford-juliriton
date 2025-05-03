package edu.austral.ingsis.clifford.command.util;

import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.command.factory.CommandFactory;
import edu.austral.ingsis.clifford.command.validator.CommandValidator;
import edu.austral.ingsis.clifford.file.FileSystem;
import edu.austral.ingsis.clifford.result.ExecutionResult;
import edu.austral.ingsis.clifford.result.ValidationResult;
import java.util.Collection;

/*

Actua como Invoker. Recibe el comando del cliente y llama al Command para que lo ejecute

Para llamar al Command correcto debe parsear el input

Crea un commando usando el CommandFactory adecuado

Invoca al comando

*/

public class CommandExecutor {

  public static ExecutionResult execute(FileSystem fs,
                                        String commandName,
                                        Collection<String> args,
                                        Collection<String> flags) {

    if (CommandRegistry.exists(commandName)) {
      CommandValidator validator = CommandRegistry.getValidator(commandName);
      ValidationResult validation = validator.validate(args, flags);

      return switch (validation) {
        case ValidationResult.Failure failure -> new ExecutionResult.Failure(failure.message());
        case ValidationResult.Success success -> {
          CommandFactory factory = CommandRegistry.getFactory(commandName);
          Command command = factory.create(flags, args, fs);
          yield command.execute();
        }
      };
    }
    return new ExecutionResult.Failure("unknown command '" + commandName + "'");
  }
}

