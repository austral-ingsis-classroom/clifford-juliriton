package edu.austral.ingsis.clifford.file.util;

public sealed interface FileModificationResult permits
    FileModificationResult.Success,
    FileModificationResult.Error {

  record Success(String message) implements FileModificationResult {}

  record Error(String message) implements FileModificationResult {}

}