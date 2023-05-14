package com.github.marschall.memoryfilesystem.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.spi.FileSystemProvider;

final class MemoryFileSystemAssertions {

  private MemoryFileSystemAssertions() {
    throw new AssertionError("not instantiable");
  }

  static void assertMemoryDirectory(Path tempDirectory) {
    assertNotNull(tempDirectory);

    assertTrue(Files.exists(tempDirectory));
    assertTrue(Files.isDirectory(tempDirectory));

    FileSystem fileSystem = tempDirectory.getFileSystem();
    assertNotNull(fileSystem);

    FileSystemProvider provider = fileSystem.provider();
    assertNotNull(provider);
    assertEquals("memory", provider.getScheme());

  }

}
