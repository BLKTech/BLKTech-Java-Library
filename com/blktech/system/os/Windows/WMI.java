/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blktech.system.os.Windows;

import com.blktech.datatype.HashMap;
import com.blktech.system.Info;
import com.blktech.system.os.Process;
/**
 *
 * @author instalacion
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
