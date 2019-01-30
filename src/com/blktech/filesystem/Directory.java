/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blktech.filesystem;

import com.blktech.Exception;
import java.io.FileFilter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class Directory extends FileSystem
{
    public static Directory getHome() throws Exception{return new Directory(System.getProperty("user.home"));}
    public static Directory getRun() throws Exception{return new Directory(System.getProperty("user.dir"));}
    public static Directory getJAR() throws Exception, URISyntaxException{return new Directory(Directory.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());}
    
    public static Directory getData() throws DirectoryNotFound
    {
        Directory d;
        
        try 
        {                        
            d = getJAR();
            if(d.exists() && d.canRead() && d.canWrite())
                return new Directory(d, "data");
        } 
        catch (java.lang.Exception ex) {
            Logger.getLogger(Directory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try 
        {                        
            d = getRun();
            if(d.exists() && d.canRead() && d.canWrite())
                return new Directory(d, "data");
        } 
        catch (java.lang.Exception ex) {
            Logger.getLogger(Directory.class.getName()).log(Level.SEVERE, null, ex);
        }

        try 
        {                        
            d = getHome();
            if(d.exists() && d.canRead() && d.canWrite())
                return new Directory(d, "data");
        } 
        catch (java.lang.Exception ex) {
            Logger.getLogger(Directory.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        throw new DirectoryNotFound();
    }
    
    
    protected void validate() throws com.blktech.Exception {if(super.exists() && !super.isDirectory()) com.blktech.Exception.throwExceptionMessage("Exists and not is directory",super.getPath());}
    public static final FileFilter filter = new FileFilter() {public boolean accept(java.io.File pathname) {return pathname.isDirectory();}};
    public Directory(String pathname) throws Exception {super(pathname);}
    public Directory(String parent, String child) throws Exception {super(parent, child);}
    public Directory(Directory parent, String child) throws Exception {super(parent, child);}
    public Directory(URI uri) throws Exception {super(uri);}
    
    public File[] getFiles()
    {
        java.io.File files[] = super.listFiles(new FileFilter() {

            @Override
            public boolean accept(java.io.File pathname) 
            {
                return pathname.isFile();
            }
        });
        
        if(files == null)
            return new File[0];
        
        File tmp[] = new File[files.length];

        for (int i = 0 ; i < files.length ; i++)
            try 
            {
                tmp[i] = new File(files[i].getPath());
            } 
            catch (Exception ex) 
            {
                Logger.getLogger(Directory.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return tmp;
    }

    public Directory[] getDirectories()
    {
        java.io.File files[] = super.listFiles(new FileFilter() {

            @Override
            public boolean accept(java.io.File pathname) 
            {
                return pathname.isDirectory();
            }
        });
        
       if(files == null)
            return new Directory[0];        
        
        Directory tmp[] = new Directory[files.length];

        for (int i = 0 ; i < files.length ; i++)
            try 
            {
                tmp[i] = new Directory(files[i].getPath());
            } 
            catch (Exception ex) 
            {
                Logger.getLogger(Directory.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return tmp;
    }    
    
    public static Directory[] listRoots()
    {
        java.io.File roots[] = java.io.File.listRoots();
        Directory tmp[] = new Directory[roots.length];
        
        for (int i = 0 ; i < roots.length ; i++)
            try 
            {
                tmp[i] = new Directory(roots[i].getPath());
            } 
            catch (Exception ex) 
            {
                Logger.getLogger(Directory.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return tmp;
    }
}
    
