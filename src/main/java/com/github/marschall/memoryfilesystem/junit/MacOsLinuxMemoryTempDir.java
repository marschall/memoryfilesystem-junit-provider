package com.github.marschall.memoryfilesystem.junit;

import static com.github.marschall.memoryfilesystem.junit.MemoryTempDirType.Type.MAC_OS;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target({FIELD, PARAMETER, ANNOTATION_TYPE})
@MemoryTempDir
@MemoryTempDirType(MAC_OS)
@interface MacOsLinuxMemoryTempDir {

}
