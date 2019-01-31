/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blktech.cryptography;

import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Lenovo
 */
public class OneTimePassword 
{
 
    private Hash hashAlgorithm;
    private long timeOffset;
    private int timeDivider;

    public OneTimePassword(Hash hashAlgorithm, long timeOffset, int timeDivider) 
    {
        this.hashAlgorithm = hashAlgorithm;
        this.timeOffset = timeOffset;
        this.timeDivider = timeDivider;
    }

    public OneTimePassword(Hash hashAlgorithm, long timeOffset) 
    {
        this(hashAlgorithm, timeOffset, 60);
    }

    public OneTimePassword(Hash hashAlgorithm, int timeDivider) 
    {
        this(hashAlgorithm, 0, timeDivider);
    }

    public OneTimePassword(Hash hashAlgorithm) 
    {
        this(hashAlgorithm, 0, 60);
    }

    public OneTimePassword(long timeOffset, int timeDivider) throws NoSuchAlgorithmException 
    {
        this(Hash.getAlgorithm("SHA-256"), timeOffset, timeDivider);
    }

    public OneTimePassword(long timeOffset) throws NoSuchAlgorithmException 
    {
        this(timeOffset, 60);
    }

    public OneTimePassword(int timeDivider) throws NoSuchAlgorithmException 
    {
        this(0, timeDivider);
    }
    
    public long getTimeOffset() 
    {
        return timeOffset;
    }

    public void setTimeOffset(long timeOffset) 
    {
        this.timeOffset = timeOffset;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   private static long getTime(long timeOffset) 
    {
        return  System.currentTimeMillis() / 1000L + timeOffset;
    }
    
    private static long getSubTime(long timeOffset,int timeDivider)
    {
        return getTime(timeOffset) / timeDivider;
    }    
    
    private static byte[] getSubTimeBytes(long timeOffset,int timeDivider)
    {
        return com.blktech.datatype.Byte.fromLong(getSubTime(timeOffset, timeDivider));
    }    
    
    private static byte[] calcHash(Hash hash, byte[] data)
    {
        return hash.calc(data);
    }
        
    
    
    private byte[] getPasswordHash(byte[] password)
    {
        return this.hashAlgorithm.calc(password);
    }
    
    private byte[] getPasswordHash(String password)
    {
        return this.getPasswordHash(password.getBytes());
    }    
    
    public byte[] calc(String password, long timeOffset, int timeDivider)
    {
        return this.calc(password.getBytes(), long timeOffset, int timeDivider);
    }
    
    public byte[] calc(byte[] password, long timeOffset, int timeDivider)
    {
        byte[] passwordHash = calcHash(this.hashAlgorithm, password);
        byte[] timeHash = calcHash(this.hashAlgorithm, getSubTimeBytes(timeOffset, timeDivider));
        byte[] result = new byte[passwordHash.length+timeHash.length+passwordHash.length];
        
        System.arraycopy(passwordHash, 0, result, 0, passwordHash.length);
        System.arraycopy(timeHash, 0, result, passwordHash.length, timeHash.length);
        System.arraycopy(passwordHash, 0, result, passwordHash.length + timeHash.length, passwordHash.length);          
        
        return calcHash(this.hashAlgorithm,  result);
    }

    
//    private byte[] getTimePassword(byte[] password) 
//    {
//        byte[] time = com.blktech.datatype.Byte.fromLong(this.getTime()/60);
//        byte[] result = new byte[password.length+time.length+password.length];
//        System.arraycopy(password, 0, result, 0, password.length);
//        System.arraycopy(time, 0, result, password.length, time.length);
//        System.arraycopy(password, 0, result, password.length + time.length, password.length);        
//        return Hash.getAlgorithm("SHA-256").calc(result);
//    }
//    


    
    
}
