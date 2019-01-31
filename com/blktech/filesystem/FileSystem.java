/*
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 */

package com.blktech.filesystem;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URI;

/**
 *
 * @author The Kito < blankitoracing@gmail.com >
 */

public abstract class FileSystem extends java.io.File
{    
    protected abstract void validate() throws com.blktech.Exception;
    
    public FileSystem(String pathname) throws com.blktech.Exception 
    {
        super(pathname);
        this.validate();
    }

    public FileSystem(String parent, String child) throws com.blktech.Exception 
    {
        super(parent, child);
        this.validate();        
    }

    public FileSystem(java.io.File parent, String child) throws com.blktech.Exception 
    {
        super(parent, child);
        this.validate();        
    }

    public FileSystem(URI uri) throws com.blktech.Exception 
    {
        super(uri);     
        this.validate();        
    }

    private void create() throws IOException {
        if(this.exists())
            return;
        
        if(this instanceof Directory)
            super.mkdir();

        if(this instanceof File)
            super.createNewFile();
        
    }
}
    