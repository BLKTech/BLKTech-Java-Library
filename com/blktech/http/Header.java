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

import com.blktech.datatype.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author The Kito < blankitoracing@gmail.com >
 */

public class Header extends HashMap<String, String>
{

    public static Header getHeader(Map<String, List<String>> t)
    {
        Header h = new Header();
        
        for (Entry<String, List<String>> entry : t.entrySet()) 
        {
            String key = entry.getKey();
            String value = null;
            
            for(String v : entry.getValue())
                value = v;
            
            h.put(key, value);
            
        }
        return h;
    }
    
    public Header() 
    {
        super();
    }

    public Header(Map t) {
        super(t);
    }
    
}
