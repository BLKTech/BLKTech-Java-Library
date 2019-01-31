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

package com.blktech.datatype;
import java.util.Map;
import java.util.StringTokenizer;

/**
 *
 * @author The Kito < blankitoracing@gmail.com >
 */

public class HashMap<K,V> extends java.util.HashMap<K,V>
{

    public static HashMap<String,String> getFromString(String string,String separatorElement,String separatorKeyValue)
    {
        StringTokenizer elements = new StringTokenizer(string, separatorElement);
        
        HashMap<String,String> r = new HashMap<>();
        
        while (elements.hasMoreTokens()) 
        {
            String element = elements.nextToken().trim();
            
            if(element.equals(""))
                continue;
            
            System.out.println(element);
            StringTokenizer keyvalue = new StringTokenizer(element, separatorKeyValue); 
            
            String key = keyvalue.nextToken().trim().toLowerCase();            
            String value = null;
            
            if(keyvalue.hasMoreTokens())
                value = keyvalue.nextToken().trim();
            
            r.put(key, value);
        }
        System.out.println(r);
        return r;
    }
    
    public HashMap(Map t) {
        super(t);
    }

    public HashMap() 
    {
        super();
    }




}
