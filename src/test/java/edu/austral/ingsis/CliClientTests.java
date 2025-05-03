package edu.austral.ingsis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.austral.ingsis.clifford.CliClient;
import edu.austral.ingsis.clifford.command.factory.*;
import edu.austral.ingsis.clifford.command.util.CommandRegistry;
import edu.austral.ingsis.clifford.command.validator.*;
import edu.austral.ingsis.clifford.file.ImmutableFileSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CliClientTests {

  private CliClient client;

  static {
    CommandRegistry.register("mkdir", new MkdirCommandFactory(), new MkdirCommandValidator());
    CommandRegistry.register("ls", new LsCommandFactory(), new LsCommandValidator());
    CommandRegistry.register("cd", new CdCommandFactory(), new CdCommandValidator());
    CommandRegistry.register("pwd", new PwdCommandFactory(), new PwdCommandValidator());
    CommandRegistry.register("touch", new TouchCommandFactory(), new TouchCommandValidator());
    CommandRegistry.register("rm", new RmCommandFactory(), new RmCommandValidator());
  }

  @BeforeEach
  void setUp() {
    client = new CliClient(new ImmutableFileSystem());
  }

  @Test
  void testParsingFailure() {
    // Given
    String input = "invalid command";

    // When
    String output = client.run(input);

    // Then
    assertEquals("unknown command 'invalid'", output);
  }

  @Test
  void testExecutionSuccess() {
    // Given
    String input = "mkdir hello";

    // When
    String output = client.run(input);

    // Then
    assertEquals("'hello' directory created", output);
  }
}
