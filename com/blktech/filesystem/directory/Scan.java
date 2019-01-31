/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blktech.filesystem.directory;
import com.blktech.filesystem.Directory;
import com.blktech.filesystem.File;
import java.nio.file.FileSystems;
import java.nio.file.Files;

/**
 *
 * @author Lenovo
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
