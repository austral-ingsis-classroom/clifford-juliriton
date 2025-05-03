package edu.austral.ingsis.clifford.result;

import edu.austral.ingsis.clifford.file.FileSystem;

public sealed interface FileModificationResult
    permits FileModificationResult.Success, FileModificationResult.Failure {

  record Success(FileSystem fs, String output) implements FileModificationResult {}

  record Failure(String message) implements FileModificationResult {}
}
