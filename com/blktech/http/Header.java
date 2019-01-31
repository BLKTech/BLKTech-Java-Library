/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blktech.http;

import com.blktech.datatype.HashMap;
import java.util.Map;

/**
 *
 * @author instalacion
 */
class Header extends HashMap<String, String>
{

    public Header() 
    {
        super();
    }

    public Header(Map t) {
        super(t);
    }
    
}
