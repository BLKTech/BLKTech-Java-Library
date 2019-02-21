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

package com.blktech.system.os.Windows;

import com.blktech.datatype.HashMap;
import com.blktech.system.Info;
import com.blktech.system.os.Process;
/**
 *
 * @author The Kito < blankitoracing@gmail.com >
 */
public class WMI extends com.blktech.system.Info
{

    HashMap<String,String> dataBaseBoard = null;
    public WMI() 
    {
        if(!Info.isWindows())
            throw new UnsupportedOperationException("Only available on Windows");
        
        this.dataBaseBoard = HashMap.getFromString(Process.basicCallReader("wmic baseboard get /value"), "\n", "=");
    }

    @Override
    public String getBaseBoardSerialNumber() 
    {
        return this.dataBaseBoard.get("serialnumber");
    }

    @Override
    public String getBaseBoardManufacturer() 
    {
        return this.dataBaseBoard.get("manufacturer");
    }
    
    
}
