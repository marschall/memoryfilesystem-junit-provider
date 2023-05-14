Memoryfilesystem JUnit Provider
===============================

A Memoryfilesystem based @TempDir provider for JUnit 5.10+.

Usage
-----


```java
class SomeTests {

  @TempDir(factory = MemoryFileSystemTempDirFactory.class)
  Path tempDirectory;

  @Test
  void someTest() {
    Path input = Files.createFile(this.tempDirectory.resolve("input.txt"));
    // test code
  }

}

````

Global Configuration
--------------------

```
junit.jupiter.tempdir.factory.default=com.github.marschall.memoryfilesystem.junit.MemoryFileSystemTempDirFactory
```

