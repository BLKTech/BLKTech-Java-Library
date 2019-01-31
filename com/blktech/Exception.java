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

package com.blktech;

import java.util.zip.CRC32;

/**
 *
 * @author The Kito < blankitoracing@gmail.com >
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
