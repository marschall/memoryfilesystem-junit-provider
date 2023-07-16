package com.github.marschall.memoryfilesystem.junit;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.github.marschall.memoryfilesystem.MemoryFileSystemBuilder;

@Documented
@Retention(RUNTIME)
@Target({FIELD, PARAMETER, ANNOTATION_TYPE})
@interface MemoryTempDirType {

  Type value();

  enum Type {

    /**
     * Uses a Linux-type file system.
     * 
     * @see MemoryFileSystemBuilder#newLinux()
     */
    LINUX,

    
    /**
     * Uses a macOS-type file system.
     * 
     * @see MemoryFileSystemBuilder#newMacOs()
     */
    MAC_OS,

    
    /**
     * Uses a Windows-type file system.
     * 
     * @see MemoryFileSystemBuilder#newWindows()
     */
    WINDOWS;

  }

}
