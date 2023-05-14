package com.github.marschall.memoryfilesystem.junit;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class MemoryFileSystemTempDirFactoryTests {

  @TempDir(factory = MemoryFileSystemTempDirFactory.class)
  Path tempDirectory;

  @Test
  void customFactory() {
    MemoryFileSystemAssertions.assertMemoryDirectory(this.tempDirectory);
  }

}
