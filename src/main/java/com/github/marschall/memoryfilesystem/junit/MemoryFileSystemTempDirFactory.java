package com.github.marschall.memoryfilesystem.junit;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.io.TempDirFactory;
import org.junit.platform.commons.util.AnnotationUtils;

import com.github.marschall.memoryfilesystem.MemoryFileSystemBuilder;

/**
 * A {@link TempDirFactory} that creates paths on a memory file system.
 */
public final class MemoryFileSystemTempDirFactory implements TempDirFactory {

  private Map<FileSystemKey, FileSystem> fileSystems;

  public MemoryFileSystemTempDirFactory() {
    this.fileSystems = Collections.synchronizedMap(new EnumMap<>(FileSystemKey.class));
  }

  @Override
  public Path createTempDirectory(ExtensionContext context) throws Exception {
    FileSystemKey key = context.getElement()
            .flatMap(element -> AnnotationUtils.findAnnotation(element, MemoryTempDirType.class))
            .map(memoryTempDirType -> lookupKey(memoryTempDirType.value()))
            .orElse(FileSystemKey.DEFAULT);

    FileSystem fileSystem = this.fileSystems.computeIfAbsent(key, k -> buildFileSystemUnchecked(k));
    Path firstRoot = fileSystem.getRootDirectories().iterator().next();
    return Files.createTempDirectory(firstRoot , "junit");
  }



  @Override
  public void close() throws IOException {
    for (FileSystem fileSystem : this.fileSystems.values()) {
      fileSystem.close();
    }
  }
  
  private static FileSystem buildFileSystemUnchecked(FileSystemKey key) {
    try {
      return buildFileSystemChecked(key);
    } catch (IOException e) {
      throw new UncheckedIOException("could not create file system", e);
    }
  }

  private static FileSystem buildFileSystemChecked(FileSystemKey key) throws IOException {
    switch (key) {
      case DEFAULT:
        return MemoryFileSystemBuilder.newEmpty().build();
      case LINUX:
        return MemoryFileSystemBuilder.newLinux().build();
      case MAC_OS:
        return MemoryFileSystemBuilder.newMacOs().build();
      case WINDOWS:
        return MemoryFileSystemBuilder.newMacOs().build();
      default:
        throw new IncompatibleClassChangeError("unknown enum value: " + key);
    }
  }

  private static FileSystemKey lookupKey(MemoryTempDirType.Type type) {
    switch (type) {
      case LINUX:
        return FileSystemKey.LINUX;
      case MAC_OS:
        return FileSystemKey.MAC_OS;
      case WINDOWS:
        return FileSystemKey.WINDOWS;
      default:
        throw new IncompatibleClassChangeError("unknown enum value: " + type);
    }
  }

  enum FileSystemKey {

    DEFAULT,

    LINUX,

    MAC_OS,

    WINDOWS;

  }

}
