/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blktech.http;


/**
 *
 * @author Lenovo
 */
public class Response extends Message
{
    private int code;

    public Response(int code, Header header) 
    {
        super(header);
        this.code = code;
    }

    public Response(int code, Header header, Object payload) 
    {
        super(header, payload);
        this.code = code;
    }

    
}
