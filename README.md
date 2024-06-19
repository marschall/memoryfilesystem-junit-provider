Memoryfilesystem JUnit Provider [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.marschall/memoryfilesystem-junit-provider/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.marschall/memoryfilesystem-junit-provider) [![Javadocs](https://www.javadoc.io/badge/com.github.marschall/memoryfilesystem-junit-provider.svg)](https://www.javadoc.io/doc/com.github.marschall/memoryfilesystem-junit-provider)
===============================

A memoryfilesystem based `@TempDir` provider for JUnit 5.10+.

Usage
-----

```xml
<dependency>
  <groupId>com.github.marschall</groupId>
  <artifactId>memoryfilesystem-junit-provider</artifactId>
  <version>1.0.1</version>
  <scope>test</scope>
</dependency>
```

You can either change individual elements annoated with `@TempDir` to use memoryfilesystem by use of the `factory` annotation value.

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

```

Alternatively you can also use the `@MemoryTempDir` meta-annotation.

```java
class SomeTests {

  @MemoryTempDir
  Path tempDirectory;

  @Test
  void someTest() {
    Path input = Files.createFile(this.tempDirectory.resolve("input.txt"));
    // test code
  }

}

```

Global Configuration
--------------------


You can either change all elements annoated with `@TempDir` to use memoryfilesystem by use of the `junit.jupiter.tempdir.factory.default` configuration property.

```
junit.jupiter.tempdir.factory.default=com.github.marschall.memoryfilesystem.junit.MemoryFileSystemTempDirFactory
```

