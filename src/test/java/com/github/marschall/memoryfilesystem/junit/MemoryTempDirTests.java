package com.github.marschall.memoryfilesystem.junit;

import java.io.File;
import java.nio.file.Path;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class MemoryTempDirTests {

  @MemoryTempDir
  Path tempDirectory;

  @Test
  void customFactory() {
    MemoryFileSystemAssertions.assertMemoryDirectory(this.tempDirectory);
  }

  @Disabled("java.io.File can be injected")
  @Test
  void injectFile(@MemoryTempDir File tempFile) {
  }

}
