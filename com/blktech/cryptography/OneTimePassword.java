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

/**
 *
 * @author The Kito < blankitoracing@gmail.com >
 */

public class OneTimePassword 
{
    private static byte[] combineBytes(byte[] passwordHash, byte[] timeHash)
    {
        byte result[] = new byte[passwordHash.length + timeHash.length + passwordHash.length];        
            
        System.arraycopy(passwordHash,  0, result,  0,                                      passwordHash.length);
        System.arraycopy(timeHash,      0, result,  passwordHash.length,                    timeHash.length);
        System.arraycopy(passwordHash,  0, result,  passwordHash.length + timeHash.length,  passwordHash.length);
        
        return result;
    }
    
    
    private Hash hashAlgorithm;    
    private byte[] passwordHash;
    
    private int timeDivider;       
    private long timeOffset;
    private long timeCurrent;
    
    private byte[] currentPassword;
    
    
    
    public OneTimePassword(Hash hashAlgorithm, int timeDivider, String password, long timeOffset) 
    {
        this(hashAlgorithm, timeDivider, password.getBytes(), timeOffset);
    }
    public OneTimePassword(Hash hashAlgorithm, int timeDivider, byte[] password, long timeOffset) 
    {
        this.hashAlgorithm = hashAlgorithm;
        this.timeDivider = timeDivider;
        this.passwordHash = hashAlgorithm.calc(password);
        this.timeOffset = timeOffset;
    }

    
    
    public long getTimeOffset() 
    {
        return timeOffset;
    }    
    public void setTimeOffset(long timeOffset) 
    {
        if(this.timeOffset != timeOffset)
        {
            this.timeOffset = timeOffset;
            this.currentPassword = new byte[0];
        }
    }
    private long getTime() 
    {
        return  System.currentTimeMillis() / 1000L + this.timeOffset;
    }

    
    
    public int getTimeDivider() 
    {
        return timeDivider;
    }
    public void setTimeDivider(int timeDivider) 
    {
        if(this.timeDivider != timeDivider)
        {
            this.timeDivider = timeDivider;
            this.currentPassword = new byte[0];
        }
    }
    private long getSubTime()
    {
        return getTime() / this.timeDivider;
    }    
    
        
        
    
    public byte[] getOneTimePassword(long timeOffset)
    {
        this.setTimeOffset(timeOffset);
        return getOneTimePassword();
    }    
    private byte[] getOneTimePassword() 
    {                
        long timeCurrent = this.getSubTime();
        
        if(this.currentPassword.length==0 || this.timeCurrent != timeCurrent)
        {
            this.timeCurrent = timeCurrent;                    
            byte[] timeHash = this.hashAlgorithm.calc(com.blktech.datatype.Byte.fromLong(this.timeCurrent));                                    
            this.currentPassword = this.hashAlgorithm.calc(combineBytes(this.passwordHash, timeHash));
        }

        return this.currentPassword;
    }    
    

}
