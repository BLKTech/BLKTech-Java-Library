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
import com.blktech.datatype.URL;

/**
 *
 * @author The Kito < blankitoracing@gmail.com >
 */

public class Request extends Message
{
    private Method method;
    private URL url;

    public Request(Method method, URL url, Header header) 
    {
        super(header);
        this.method = method;
        this.url = url;
    }

    public Request(Method method, URL url, Header header, Object payload) 
    {
        super(header, payload);
        this.method = method;
        this.url = url;
    }

    public Method getMethod() {
        return method;
    }

    public URL getUrl() {
        return url;
    }


}
