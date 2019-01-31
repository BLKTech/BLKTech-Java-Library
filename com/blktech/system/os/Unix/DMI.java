/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blktech.system.os.Unix;

import com.blktech.system.Info;

/**
 *
 * @author instalacion
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
