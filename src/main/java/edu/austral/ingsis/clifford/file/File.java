package edu.austral.ingsis.clifford.file;

public interface File {

  String getName();

  Directory getParent();

  boolean isDirectory();

  String getPath();

}
