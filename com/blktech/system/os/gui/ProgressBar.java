/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blktech.system.os.gui;

/**
 *
 * @author ASUS
 */
public class ProgressBar extends javax.swing.ProgressMonitor
{

    public ProgressBar(String title, String message) {
        super(null, title, message, -1, 1001);
        this.update(message, 0);        
    }
    
    public final void update(String message, int progress)
    {
        if(this.isCanceled())
            return;
        
        this.setProgress(progress*10);
        this.setNote(String.valueOf(progress) + "% " + message);   
        
        if(progress==100)
        {
            com.blktech.system.jvm.Thread.sleep(1000);
            this.setProgress(1001);    
            this.close();
            System.gc();
        }
    }
}
