package com.github.marschall.memoryfilesystem.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;

class WindowsMemoryTempDirTests {

  @WindowsMemoryTempDir
  Path tempDirectory;

  @Test
  void customFactory() {
    MemoryFileSystemAssertions.assertMemoryDirectory(this.tempDirectory);

    FileSystem fileSystem = this.tempDirectory.getFileSystem();
    String separator = fileSystem.getSeparator();
    assertEquals("\\", separator, "separator");

    List<Path> rootDirectories = toList(fileSystem.getRootDirectories());
    assertEquals(Collections.singletonList(fileSystem.getPath("C:\\")), rootDirectories);
  }
  
  private static <T> List<T> toList(Iterable<T> i) {
    return StreamSupport.stream(i.spliterator(), false).collect(Collectors.toList());
  }

}
