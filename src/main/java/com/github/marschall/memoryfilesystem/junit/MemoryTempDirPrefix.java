package com.github.marschall.memoryfilesystem.junit;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Denotes the prefix string to be used in generating the temporary directory's name.
 * 
 * @see java.nio.file.Files#createTempDirectory(java.nio.file.Path, String, java.nio.file.attribute.FileAttribute...)
 */
@Retention(RUNTIME)
@Target({ FIELD, PARAMETER, ANNOTATION_TYPE })
public @interface MemoryTempDirPrefix {

  String value();

}
