/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blktech.http;

import com.blktech.datatype.URL;



/**
 *
 * @author instalacion
 */
public class Request extends Message
{
    private Method method;
    private URL url;

    public Request(Method method, URL url, Header header) 
    {
        super(header);
        this.method = method;
        this.url = url;
    }

    public Request(Method method, URL url, Header header, Object payload) 
    {
        super(header, payload);
        this.method = method;
        this.url = url;
    }

    public Method getMethod() {
        return method;
    }

    public URL getUrl() {
        return url;
    }


}
