package com.github.marschall.memoryfilesystem.junit;

import com.github.marschall.memoryfilesystem.MemoryFileSystemBuilder;

public @interface MemoryTempDirType {

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
