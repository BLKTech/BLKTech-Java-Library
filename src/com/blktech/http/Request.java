/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blktech.http;

import com.blktech.types.URL;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.util.HashMap;

/**
 *
 * @author Lenovo
 */
public abstract class Request 
{
    public enum Type 
    {
        GET,
        POST,
        HEAD,
        PUT,
        DELETE
    }

    private Request.Type type;
    private URL url;
    private HashMap<String,String> headers;    
    
    public Request(URL url, HashMap<String,String> headers, Request.Type type) 
    {
        this.type = type;
        this.url = url;
        this.headers = headers;
    }
    public Request(URL url) {this(url, new HashMap<String,String>(), Type.GET);}
    public Request(URL url, HashMap<String,String> headers) {this(url, headers, Type.GET);}
    public Request(URL url, Request.Type type) {this(url, new HashMap<String,String>(), type);}

    public Type getType() {return type;}
    public URL getUrl() {return url;}
    public HashMap<String, String> getHeaders() {return headers;}
    
    public abstract void getBody(OutputStream os);
    
    public Response execute() throws ProtocolException, IOException
    {
        HttpURLConnection huc = (HttpURLConnection) (this.url.getURL()).openConnection();
        huc.setInstanceFollowRedirects(true);
        huc.setDoOutput(true);
        huc.setDoInput(true);        
        huc.setRequestMethod(this.type.toString());
        
        for(String header : this.headers.keySet())
            huc.setRequestProperty(header, this.headers.get(header));        

        this.getBody(huc.getOutputStream());
        
        return new Response(huc);
    }
}
