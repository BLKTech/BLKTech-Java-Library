/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blktech.system;

import com.blktech.system.os.Unix.DMI;
import com.blktech.system.os.Windows.WMI;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author mkyong
 */
abstract public class Info 
{
    private static Info INSTANCE=null;

    @Override
    public Object clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException("Cannot clone instance of this class");
    }    
    
    public static Info getInstance() 
    {
        if(INSTANCE==null)
        {
            if(Info.isWindows())
                INSTANCE = new WMI();
            else
                INSTANCE = new DMI();
        }
        
        return INSTANCE;
    }

    
    
    private static String osName =  System.getProperty("os.name").toLowerCase();
    public static boolean isWindows() 
    {
        return (osName.indexOf("win") >= 0);
    }

    public static boolean isMac() 
    {
        return (osName.indexOf("mac") >= 0);
    }

    public static boolean isUnix() 
    {
        return (osName.indexOf("nix") >= 0 || osName.indexOf("nux") >= 0 || osName.indexOf("aix") > 0 );
    }

    public static boolean isSolaris() 
    {
        return (osName.indexOf("sunos") >= 0);
    }

    public static String getOperatingSystemName() 
    {
        return osName;
    }
    
    public abstract String getBaseBoardSerialNumber();
    public abstract String getBaseBoardManufacturer();
    public String getBaseBoardUID()
    {
        String base = this.getBaseBoardManufacturer()+"#"+this.getBaseBoardSerialNumber();
        return java.util.UUID.nameUUIDFromBytes(base.getBytes()).toString();
    }
    
    public String getHostName() throws UnknownHostException
    {
        return InetAddress.getLocalHost().getHostName();
    }
    
}
