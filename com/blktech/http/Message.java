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

package com.blktech.http;

/**
 *
 * @author The Kito < blankitoracing@gmail.com >
 */

public class Message 
{
    private Header header;   
    private Object payload;

    public Message(Header header) 
    {
        this(header, null);
    }
    
    public Message(Header header, Object payload) 
    {
        this.header = header;
        this.payload = payload;
    }

    public Header getHeader() 
    {
        return header;
    }

    public Object getPayload() 
    {
        return payload;
    }
    
  
}
