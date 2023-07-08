package com.github.marschall.memoryfilesystem.junit;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.api.io.TempDirFactory;

/**
 * A meta-annotation that that configures {@link TempDirFactory} to use {@link MemoryFileSystemTempDirFactory}.
 * 
 * <h2>Usage</h2>
 * <pre><code>
 * class SomeTests {
 *
 *   @MemoryTempDir
 *   Path tempDirectory;
 *
 *   @Test
 *   void someTest() {
 *     Path input = Files.createFile(this.tempDirectory.resolve("input.txt"));
 *     // test code
 *   }
 *
 * }
 * </code></pre>
 */
@Documented
@Retention(RUNTIME)
@Target({FIELD, PARAMETER, ANNOTATION_TYPE})
@TempDir(factory = MemoryFileSystemTempDirFactory.class)
public @interface MemoryTempDir {
  
}
