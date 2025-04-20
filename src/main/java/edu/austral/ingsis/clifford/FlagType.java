package edu.austral.ingsis.clifford;

import java.util.Map;
import java.util.Optional;

public enum FlagType {
  ORD("--ord"),
  ORD_ASC("--ord=asc"),
  ORD_DEC("--ord=dec"),
  REC("--recursive");

  private final String keyword;

  FlagType(String keyword) {
    this.keyword = keyword;
  }

  private static final Map<String, FlagType> FLAGS = Map.of(
      "--ord", ORD,
      "--ord=asc", ORD_ASC,
      "--ord=dec", ORD_DEC,
      "--recursive", REC
  );

  public static Optional<FlagType> fromString(String s) {
    return Optional.ofNullable(FLAGS.get(s));
  }
}

