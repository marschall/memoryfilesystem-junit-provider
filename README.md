Memoryfilesystem JUnit Provider [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.marschall/memoryfilesystem-junit-provider/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.marschall/memoryfilesystem-junit-provider) [![Javadocs](https://www.javadoc.io/badge/com.github.marschall/memoryfilesystem-junit-provider.svg)](https://www.javadoc.io/doc/com.github.marschall/memoryfilesystem-junit-provider)
===============================

A memoryfilesystem based [@TempDir](https://junit.org/junit5/docs/current/api/org.junit.jupiter.api/org/junit/jupiter/api/io/TempDir.html) provider for JUnit 5.10+.

Only `Path` is supported, `File` can not be supported.

- versions 2.x are for JUnit 6 and require Java 17
- version 1.x are for Junit 5 and require Java 8

Usage
-----

```xml
<dependency>
  <groupId>com.github.marschall</groupId>
  <artifactId>memoryfilesystem-junit-provider</artifactId>
  <version>2.0.0</version>
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

You can also make all elements annoated with `@TempDir` to use memoryfilesystem by use of the [junit.jupiter.tempdir.factory.default](https://junit.org/junit5/docs/current/api/org.junit.jupiter.api/org/junit/jupiter/api/io/TempDir.html#DEFAULT_FACTORY_PROPERTY_NAME) configuration property.

```properties
junit.jupiter.tempdir.factory.default=com.github.marschall.memoryfilesystem.junit.MemoryFileSystemTempDirFactory
```

