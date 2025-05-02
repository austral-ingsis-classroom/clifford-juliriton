package edu.austral.ingsis.clifford.command.util;

/*

Me permite agregar nuevos comandos simplemente mediante extension y sin modificacion
de clases preexistentes

*/

import edu.austral.ingsis.clifford.command.validator.CommandValidator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
  private static final Map<String, CommandFactory> FACTORIES = new HashMap<>();
  private static final Map<String, CommandValidator> VALIDATORS = new HashMap<>();

  public CommandRegistry() {}

  public static void register(String commandIdentifier,
                                     CommandFactory factory,
                                     CommandValidator validator) {
    FACTORIES.put(commandIdentifier, factory);
    VALIDATORS.put(commandIdentifier, validator);
  }

  public static boolean exists(String commandName) {
    return FACTORIES.containsKey(commandName);
  }

  public static CommandFactory getFactory(String commandName) {
    return FACTORIES.get(commandName);
  }

  public static Collection<CommandFactory> getAllFactories() {
    return new ArrayList<>(FACTORIES.values());
  }

  public static CommandValidator getValidator(String commandName) {
    return VALIDATORS.get(commandName);
  }

  public static Collection<CommandValidator> getAllValidators() {
    return new ArrayList<>(VALIDATORS.values());
  }

}