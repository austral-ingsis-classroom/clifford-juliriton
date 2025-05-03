package edu.austral.ingsis.clifford.file;

import edu.austral.ingsis.clifford.result.FileModificationResult;

/*

Actua como receiver. Sabe realizar las operaciones de un FileSystem asociado a un comando
que fue llamado (buisness logic), pero no decide quien realiza la operacion, ni porque
ni cuando

 */

public interface FileSystem {

  FileModificationResult changeDirectory(String path);

  FileSystem createFile(String fileName);

  FileSystem createDirectory(String dirName);

  FileSystem removeItem(String path, boolean recursive);

  String getCurrentPath();

  Directory getCurrentDirectory();
}
