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
import java.net.URI;
import com.blktech.Exception;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author The Kito < blankitoracing@gmail.com >
 */

public class File extends FileSystem
{
    public static final File getTemp() throws IOException, Exception{return new File(java.io.File.createTempFile("blk", "tmp").getAbsolutePath());}    
    public static final FileFilter filter = new FileFilter() {public boolean accept(java.io.File pathname) {return pathname.isFile();}};
    protected void validate() throws Exception {if(super.exists() && !super.isFile()) Exception.throwExceptionMessage("Exists and not is file",super.getPath());}
    public File(String pathname) throws Exception {super(pathname);}
    public File(String parent, String child) throws Exception {super(parent, child);}
    public File(Directory parent, String child) throws Exception {super(parent, child);}
    public File(URI uri) throws Exception {super(uri);}    
    
    
    public byte[] hash(String algName) throws FileNotFoundException, NoSuchAlgorithmException, IOException 
    {                
        InputStream in = new FileInputStream(this);
        MessageDigest digest = MessageDigest.getInstance(algName);
        byte[] block = new byte[4096];
        int length;
        while ((length = in.read(block)) > 0) 
            digest.update(block, 0, length);
            
        return digest.digest();        
    }
    
    public String hashHEX(String algName) throws FileNotFoundException, NoSuchAlgorithmException, IOException 
    {   
        return DatatypeConverter.printHexBinary(hash(algName));        
    }    

    public String hashB64(String algName) throws FileNotFoundException, NoSuchAlgorithmException, IOException 
    {   
        return DatatypeConverter.printBase64Binary(hash(algName));        
    }    

    
    public long getSize()
    {
        return super.length();
    }    

    public long getModifiedTime() {
        return super.lastModified();
    }


    
}
