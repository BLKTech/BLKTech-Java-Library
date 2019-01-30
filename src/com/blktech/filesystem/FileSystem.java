/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blktech.filesystem;

import java.io.FileFilter;
import java.io.IOException;
import java.net.URI;

/**
 *
 * @author ASUS
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
    