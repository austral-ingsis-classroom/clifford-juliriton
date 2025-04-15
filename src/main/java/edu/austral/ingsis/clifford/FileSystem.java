package edu.austral.ingsis.clifford;

public interface FileSystem {}

/*

CommandLineInterface --> text and writing tools, print messages
CommandReader --> syntax check
CommandExecuter --> find correct command to run, initialize it, check for missing params, etc.
FileSystemRepository --> manages operations over filesystem type like pwd
FileRepsoitory --> manages operations over file in a filesystem

 */