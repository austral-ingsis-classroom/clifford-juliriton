package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.CommandLine;
import edu.austral.ingsis.clifford.ExecutionResult;
import edu.austral.ingsis.clifford.file.File;
import edu.austral.ingsis.clifford.file.util.FileSystem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListCommand implements Command {
  private final CommandLine commandLine;

  public ListCommand(CommandLine commandLine) {
    this.commandLine = commandLine;
  }

  @Override
  public boolean isValid() {
    List<String> flags = commandLine.getFlags();
    List<String> args = commandLine.getArguments();

    if (!args.isEmpty()) {
      return false;
    }

    if (flags.isEmpty()) {
      return true;
    }

    if (flags.size() == 1 && flags.get(0).startsWith("--ord")) {
      String flag = flags.get(0);

      if (flag.equals("--ord")) {
        return true;
      }

      if (flag.startsWith("--ord=")) {
        String value = flag.substring("--ord=".length());
        return value.equals("asc") || value.equals("desc");
      }
    }

    return false;
  }

  @Override
  public ExecutionResult execute(FileSystem fs) {
    List<File> files = fs.listFiles();
    List<String> names = getFileNames(files);

    List<String> flags = commandLine.getFlags();

    sortNamesIfCalled(flags, names);

    String result = format(names);

    return new ExecutionResult.Success(result);
  }

  private static List<String> getFileNames(List<File> files) {
    List<String> names = new ArrayList<>();

    for (File file : files) {
      names.add(file.getName());
    }
    return names;
  }

  private static String format(List<String> names) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < names.size(); i++) {
      result.append(names.get(i));
      if (i < names.size() - 1) {
        result.append(" ");
      }
    }
    return result.toString();
  }

  private static void sortNamesIfCalled(List<String> flags, List<String> names) {
    boolean sortAsc = false;
    boolean sortDesc = false;

    if (!flags.isEmpty()) {
      String arg = flags.get(0);

      if (arg.equals("--ord")) {
      } else if (arg.startsWith("--ord=")) {
        String value = arg.substring("--ord=".length());
        if (value.equals("asc")) {
          sortAsc = true;
        }
        if (value.equals("desc")) {
          sortDesc = true;
        }
      }
    }

    if (sortAsc) {
      Collections.sort(names);
    }
    if (sortDesc) {
      Collections.sort(names, Collections.reverseOrder());
    }
  }

  @Override
  public String validationError() {
    return "Invalid parameters for ls command : ls [--ord=asc|desc]";
  }
}
