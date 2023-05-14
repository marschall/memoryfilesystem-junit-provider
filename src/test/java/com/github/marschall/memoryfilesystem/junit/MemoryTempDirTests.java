package com.github.marschall.memoryfilesystem.junit;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;

class MemoryTempDirTests {

  @MemoryTempDir
  Path tempDirectory;

  @Test
  void customFactory() {
    MemoryFileSystemAssertions.assertMemoryDirectory(this.tempDirectory);
  }

}
