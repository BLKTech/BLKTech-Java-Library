package com.blktech.system.os.gui;  


import java.awt.*;
import java.net.URL;
import javax.swing.*;

public class Icon extends java.awt.TrayIcon
{
    
    public static boolean isSupported()
    {
        return SystemTray.isSupported();
    }

    public Icon(String imagePath) throws AWTException 
    {        
        super(Icon.createImage(imagePath, null));
        this.setImageAutoSize(true);
        SystemTray.getSystemTray().add(this);        
    }

    public Icon(String imagePath, String tooltip) throws AWTException 
    {
        this(imagePath);
        this.setToolTip(tooltip);               
    }

    public Icon(String imagePath, String tooltip, PopupMenu popup) throws AWTException 
    {
        this(imagePath, tooltip);
        this.setPopupMenu(popup);
    }
    
    public void showMessageError(String title,String message)
    {
       this.displayMessage(title, message, TrayIcon.MessageType.ERROR);
    }
    
    public void showMessageInfo(String title,String message)
    {
       this.displayMessage(title, message, TrayIcon.MessageType.INFO);
    }
    
    public void showMessage(String title,String message)
    {
       this.displayMessage(title, message, TrayIcon.MessageType.NONE);
    }

    public void showMessageWarning(String title,String message)
    {
       this.displayMessage(title, message, TrayIcon.MessageType.WARNING);
    }

    
    
    private static Image createImage(String path, String description) {
        URL imageURL = TrayIcon.class.getResource(path);
        
        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }    
    
    
    
    
}
