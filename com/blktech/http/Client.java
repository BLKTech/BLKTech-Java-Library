/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blktech.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Map;

/**
 *
 * @author Lenovo
 */
public class Client 
{
    public static Response call(Request request) throws IOException
    {
        return Client.call(request, 10);
    }
    public static Response call(Request request, int deep) throws IOException
    {
        System.setProperty("http.maxRedirects", String.valueOf(deep));

        HttpURLConnection huc = (HttpURLConnection) (request.getUrl()).openConnection();
        
        huc.setInstanceFollowRedirects(true);
        huc.setDoOutput(true);
        huc.setDoInput(true);
        
        huc.setRequestMethod(request.getMethod().toString());
        
        for (Map.Entry<String, String> entry : request.getHeader().entrySet()) 
            huc.setRequestProperty(entry.getKey(),entry.getValue());
        
        byte[] data;
        if(request.getPayload()!=null)
        {
            data = request.getPayload().toString().getBytes();
            
            if(data.length>0)
            {
                huc.setRequestProperty("Content-length", String.valueOf(data.length));
                //huc.setRequestProperty("Content-MD5", DatatypeConverter.printBase64Binary(hash("MD5", data)));               
                huc.getOutputStream().write(data); 
            }
        }
                
        
        int responseCode = huc.getResponseCode();
        Header header = new Header(huc.getHeaderFields());
        data = null;
        
        InputStream is = huc.getInputStream();
        if(is.available()>0)
        {
            data = new byte[is.available()];
            is.read(data);
        }
        
        return new Response(responseCode, header, new String(data));
    }
    
    
  
}
