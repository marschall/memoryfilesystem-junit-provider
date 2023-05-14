package com.github.marschall.memoryfilesystem.junit;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.io.TempDirFactory;

import com.github.marschall.memoryfilesystem.MemoryFileSystemBuilder;

/**
 * A {@link TempDirFactory} that creates paths on a memory file system.
 */
public final class MemoryFileSystemTempDirFactory implements TempDirFactory {

  private FileSystem fileSystem;

  public MemoryFileSystemTempDirFactory() {
    super();
  }

  @Override
  public Path createTempDirectory(ExtensionContext context) throws Exception {
    if (this.fileSystem == null) {
      this.fileSystem = MemoryFileSystemBuilder.newEmpty().build();
    }
    Path firstRoot = fileSystem.getRootDirectories().iterator().next();
    return Files.createTempDirectory(firstRoot, "junit");
  }

  @Override
  public void close() throws IOException {
    this.fileSystem.close();
  }

}
