package edu.austral.ingsis;

import edu.austral.ingsis.clifford.CommandLineInterface;
import edu.austral.ingsis.clifford.file.util.FileSystem;
import edu.austral.ingsis.clifford.file.util.VirtualFileSystem;
import java.util.ArrayList;
import java.util.List;

public class FileSystemRunnerImpl implements FileSystemRunner {

  private final CommandLineInterface cli;

  public FileSystemRunnerImpl() {
    FileSystem fs = new VirtualFileSystem();
    this.cli = new CommandLineInterface(fs);
  }

  @Override
  public List<String> executeCommands(List<String> commands) {
    List<String> results = new ArrayList<>();
    for (String cmd : commands) {
      results.add(cli.input(cmd));
    }
    return results;
  }

}
