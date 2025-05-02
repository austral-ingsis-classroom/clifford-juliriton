package edu.austral.ingsis.clifford.command.util;

import edu.austral.ingsis.clifford.command.result.ExecutionResult;
import edu.austral.ingsis.clifford.command.result.ValidationResult;
import edu.austral.ingsis.clifford.command.Command;
import edu.austral.ingsis.clifford.command.validator.CommandValidator;
import edu.austral.ingsis.clifford.file.util.FileSystem;
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

    CommandValidator validator = CommandRegistry.getValidator(commandName);
    ValidationResult validation = validator.validate(args, flags);

    switch (validation) {
      case ValidationResult.Failure failure -> {
        return new ExecutionResult.Failure(failure.message());
      }
      case ValidationResult.Success success -> {
        CommandFactory factory = CommandRegistry.getFactory(commandName);
        Command command = factory.create(flags, args, fs);
        return command.execute();
      }
    }
  }

}
