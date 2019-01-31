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

package com.blktech.cryptography;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author TheKito < blankitoracing@gmail.com >
 */

public class Hash
{

    private static HashMap<String,Hash> instances = new HashMap<>();
    public static Hash getAlgorithm(String name)
    {
        name = name.toLowerCase();
        
        if(instances.containsKey(name))
            return instances.get(name);
        
        Hash i = new Hash(name);
        
        instances.put(name, i);
        
        return i;
    }    
    
    
    public static ArrayList<String> getAlgorithms()
    {
        ArrayList<String> l = new ArrayList<>();
        for (Provider provider : Security.getProviders()) 
            for (Provider.Service service : provider.getServices())
                if (service.getType().equalsIgnoreCase(MessageDigest.class.getSimpleName()))                
                    l.add(service.getAlgorithm());
        return l;
    }
    

    
    
    private String name;
    private byte[] example;
    private MessageDigest messageDigest;
    public Hash(String name) throws NoSuchAlgorithmException 
    {
        this.name = name;
        this.messageDigest = MessageDigest.getInstance(name);        
        this.example = this.calc(new byte[0]);
    }
    
    
    private byte[] calc(byte[] data) 
    {
        this.messageDigest.reset();
        this.messageDigest.update(data);            
        return this.messageDigest.digest(); 
    }
    
    private String calc(String data) 
    {        
        return DatatypeConverter.printHexBinary(this.calc(data.getBytes()));
    }    

//    public function calcFile(File $file)
//    {
//        $t = hash_file($this->name, $file->__toString());
//        
//        if($t===false)
//            throw new HashAlgorithmCalcException ($file->__toString());
//        
//        return strtoupper($t);
//    }
//    
//    public function check($hashValue,$data)
//    {
//        return $this->calc($data) == strtoupper($hashValue);        
//    }
//
//    public function checkFile($hashValue, File $file)
//    {
//        return $this->calcFile($file) == strtoupper($hashValue);        
//    }
//
    public boolean checkHash(byte[] hashValue)
    {
        return hashValue.length == this.example.length;
    }
  
    public boolean checkHash(String hashValue)
    {
        return this.checkHash(DatatypeConverter.parseHexBinary(hashValue));
    }
    
    public void validateHash(byte[] hashValue) throws InvalidHashValueException
    {        
        if(this.checkHash(hashValue))
            throw new InvalidHashValueException(hashValue);
    }

    public void validateHash(String hashValue) throws InvalidHashValueException
    {
        validateHash(DatatypeConverter.parseHexBinary(hashValue));
    }
                
    public String getName()
    {
        return this.name;
    }

  


}
