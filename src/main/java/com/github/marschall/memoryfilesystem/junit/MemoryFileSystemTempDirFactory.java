package com.github.marschall.memoryfilesystem.junit;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.extension.AnnotatedElementContext;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.io.TempDirFactory;

import com.github.marschall.memoryfilesystem.MemoryFileSystemBuilder;

/**
 * A {@link TempDirFactory} that creates paths on a memory file system.
 * 
 * <h2>Usage</h2>
 * <pre><code>
 * class SomeTests {
 *
 *   &commat;TempDir(factory = MemoryFileSystemTempDirFactory.class)
 *   Path tempDirectory;
 *
 *   &commat;Test
 *   void someTest() {
 *     Path input = Files.createFile(this.tempDirectory.resolve("input.txt"));
 *     // test code
 *   }
 *
 * }
 * </code></pre>
 */
public final class MemoryFileSystemTempDirFactory implements TempDirFactory {

  private FileSystem fileSystem;

  public MemoryFileSystemTempDirFactory() {
    super();
  }
  
  @Override
  public Path createTempDirectory(AnnotatedElementContext elementContext, ExtensionContext extensionContext) throws Exception {
    this.fileSystem = elementContext.findAnnotation(MemoryTempDirType.class)
            .map(MemoryTempDirType::value)
            .map(MemoryFileSystemTempDirFactory::buildFileSystemUnChecked)
            .orElseGet(MemoryFileSystemTempDirFactory::buildDefaultFileSystem);

    Path firstRoot = fileSystem.getRootDirectories().iterator().next();
    return Files.createTempDirectory(firstRoot , "junit");
  }

  @Override
  public void close() throws IOException {
    this.fileSystem.close();
  }

  private static FileSystem buildDefaultFileSystem() {
    try {
      return MemoryFileSystemBuilder.newEmpty().build();
    } catch (IOException e) {
      throw new UncheckedIOException("could not create empty file system", e);
    }
  }

  private static FileSystem buildFileSystemUnChecked(MemoryTempDirType.Type type) {
    try {
      return buildFileSystemChecked(type);
    } catch (IOException e) {
      throw new UncheckedIOException("could not create file system of type: " + type, e);
    }
  }

  private static FileSystem buildFileSystemChecked(MemoryTempDirType.Type type) throws IOException {
    switch (type) {
      case LINUX:
        return MemoryFileSystemBuilder.newLinux().build();
      case MAC_OS:
        return MemoryFileSystemBuilder.newMacOs().build();
      case WINDOWS:
        return MemoryFileSystemBuilder.newWindows().build();
      default:
        throw new IncompatibleClassChangeError("unknown enum value: " + type);
    }
  }

}
