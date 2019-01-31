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

package com.blktech.system.os.Unix;
import com.blktech.system.Info;

/**
 *
 * @author The Kito < blankitoracing@gmail.com >
 */

public class DMI extends com.blktech.system.Info
{

    public DMI() 
    {
        if(Info.isWindows())
            throw new UnsupportedOperationException("No available on Windows");
    }

    @Override
    public String getBaseBoardSerialNumber() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getBaseBoardManufacturer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
