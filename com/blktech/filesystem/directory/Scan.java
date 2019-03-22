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

package com.blktech.filesystem.directory;
import com.blktech.filesystem.Directory;
import com.blktech.filesystem.File;
import java.nio.file.FileSystems;
import java.nio.file.Files;

/**
 *
 * @author The Kito < blankitoracing@gmail.com >
 */

public abstract class Scan extends Thread
{
    protected abstract void processDirectory(Directory directory);
    protected abstract void processFile(File file);
   
    private void scanDirectory(Directory root) 
    {
        super.setName("Scan: " + root.getAbsolutePath());
        processDirectory(root);
        
        for(File f : root.getFiles())
            if(!Files.isSymbolicLink(FileSystems.getDefault().getPath(f.getPath())))
                processFile(f);       
                
        for(Directory d : root.getDirectories())
            if(!Files.isSymbolicLink(FileSystems.getDefault().getPath(d.getPath())))
                scanDirectory(d);       
    }

    private Directory root = null;
    public Scan(Directory root)  
    {
        super("Scan: " + root.getAbsolutePath());
        
        this.root = root;
    }    
    @Override
    public void run() 
    {
        super.run(); 
        this.scanDirectory(this.root);
    }
}
