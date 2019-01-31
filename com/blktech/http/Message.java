/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blktech.http;

/**
 *
 * @author instalacion
 */
public class Message 
{
    private Header header;   
    private Object payload;

    public Message(Header header) 
    {
        this(header, null);
    }
    
    public Message(Header header, Object payload) 
    {
        this.header = header;
        this.payload = payload;
    }

    public Header getHeader() 
    {
        return header;
    }

    public Object getPayload() 
    {
        return payload;
    }
    
  
}
