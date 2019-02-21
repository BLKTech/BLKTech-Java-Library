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

public class Response extends Message
{
    private int code;

    public Response(int code, Header header) 
    {
        super(header);
        this.code = code;
    }

    public Response(int code, Header header, Object payload) 
    {
        super(header, payload);
        this.code = code;
    }

    
}
