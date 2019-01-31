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
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.HashMap;

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
    

    
    
    
    public Hash(String name) {
    }
    
    
    
//
//
//    private $name;
//    private $example;
//    private function __construct($name)
//    {
//        $this->name = $name;
//        
//        if(!in_array($this->name, self::getAlgorithms()))
//            throw new HashAlgorithmNotFoundException($this->name);
//            
//        $this->example = $this->calc('');
//    }
//
//    public function calc($data)
//    {        
//        $t = hash($this->name, $data);
//        
//        if($t===false)
//            throw new HashAlgorithmCalcException($data);
//        
//        return strtoupper($t);
//    }
//    
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
//    public function checkHash($hashValue)
//    {
//        return strlen($hashValue) == strlen($this->example);
//    }
//    
//    public function validateHash($hashValue)
//    {        
//        if(!$this->checkHash($hashValue))
//            throw new InvalidHashValueException($hashValue);
//    }
//
//    function getName()
//    {
//        return $this->name;
//    }


}
