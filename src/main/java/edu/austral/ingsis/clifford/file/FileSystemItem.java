package edu.austral.ingsis.clifford.file;

interface FileSystemItem {
  String getName();

  boolean isDirectory();

  String getPath();

  Directory getParent();
}