package edu.austral.ingsis.clifford.command.util;

import edu.austral.ingsis.clifford.CommandLine;
import edu.austral.ingsis.clifford.command.*;

public enum CommandType {
  LS {
    public Command create(CommandLine commandLine) {
      return new ListCommand(commandLine);
    }
  },
  CD {
    public Command create(CommandLine commandLine) {
      return new ChangeDirectoryCommand(commandLine);
    }
  },
  MKDIR {
    public Command create(CommandLine commandLine) {
      return new MakeDirectoryCommand(commandLine);
    }
  },
  TOUCH {
    @Override
    public Command create(CommandLine commandLine) {
      return new TouchCommand(commandLine);
    }
  },
  RM {
    public Command create(CommandLine commandLine) {
      return new RemoveCommand(commandLine);
    }
  },
  PWD {
    public Command create(CommandLine commandLine) {
      return new PrintWorkingDirectoryCommand(commandLine);
    }
  };

  public abstract Command create(CommandLine commandLine);

  public static CommandType from(String commandName) {
    for (CommandType type : values()) {
      String name = type.name().toLowerCase();
      if (name.equals(commandName)) {
        return type;
      }
    }
    return null;
  }
}
