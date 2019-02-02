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

    
    private final Hash hashAlgorithm;    
    private final String passwordHash;
    
    private int timeDivider;       
    private long timeOffset;
    private long timeCurrent;
    
    private String currentPassword;
    
    
    
  
    public OneTimePassword(Hash hashAlgorithm, int timeDivider, String password, long timeOffset) 
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
            this.currentPassword = null;
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
            this.currentPassword = null;
        }
    }
    private long getSubTime()
    {
        return getTime() / this.timeDivider;
    }    
    
        
        
    
    public String getOneTimePassword(long timeOffset)
    {
        this.setTimeOffset(timeOffset);
        return getOneTimePassword();
    }    
    public String getOneTimePassword() 
    {                
        long timeCurrent = this.getSubTime();
        
        if(this.currentPassword==null || this.timeCurrent != timeCurrent)
        {
            this.timeCurrent = timeCurrent;                    
            String timeHash = this.hashAlgorithm.calc(String.valueOf(this.timeCurrent));       

            this.currentPassword = this.hashAlgorithm.calc(
                    this.passwordHash +
                    timeHash +
                    this.passwordHash
            );
        }

        return this.currentPassword;
    }    
    
    public void debug()
    {
        System.out.println("============================================");
        System.out.println("Time System: " + (System.currentTimeMillis() / 1000L));
        System.out.println("Time Offset: " + this.getTime());
        System.out.println("Time Part:   " + this.getSubTime());
        System.out.println("Time Hash:   " + this.hashAlgorithm.calc(String.valueOf(this.getSubTime())));
        System.out.println("Pass Hash:   " + this.passwordHash);
        System.out.println("OTP  Hash:   " + this.getOneTimePassword());
        System.out.println("============================================");
    }
}
