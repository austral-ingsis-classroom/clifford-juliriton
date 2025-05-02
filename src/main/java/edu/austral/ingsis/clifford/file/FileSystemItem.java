package edu.austral.ingsis.clifford.file;

public interface FileSystemItem {
  String getName();

  boolean isDirectory();

  String getPath();

  Directory getParent();
}