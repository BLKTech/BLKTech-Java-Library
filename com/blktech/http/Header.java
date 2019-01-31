/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blktech.http;

import com.blktech.datatype.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author instalacion
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
