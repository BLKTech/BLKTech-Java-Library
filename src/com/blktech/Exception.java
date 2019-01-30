/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blktech;

import java.util.zip.CRC32;

/**
 *
 * @author ASUS
 */
public class Exception extends java.lang.Exception
{
    private long code = -1;
    
    public Exception(String message) 
    {
        this(message, getMessageCode(message));
    }

    public Exception(String message, String data) 
    {
        this(message + " " + data, getMessageCode(message));
    }
    
    public Exception(String message, Throwable cause) 
    {   
        this(message, getMessageCode(message),cause);
    }

    public Exception(String message,long code) 
    {
        super(message);
        this.code = code;                
    }

    public Exception(String message,long code, Throwable cause) 
    {
        super(message, cause);
        this.code = code;        
    }
    

    public static long getMessageCode(String message)
    {
        CRC32 crc = new CRC32();
        crc.update(message.getBytes());
        return crc.getValue();
    }
    
    public static void throwExceptionMessage(String message, Throwable cause) throws Exception 
    {
        throw new Exception(message,cause);
    }
    
    public static void throwExceptionMessage(String message) throws Exception 
    {
        throw new Exception(message);
    }    
    public static void throwExceptionMessage(String message,String data) throws Exception 
    {
        throw new Exception(message, data);
    }        
}
