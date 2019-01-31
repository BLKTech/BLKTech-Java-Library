/*
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 */

package com.blktech.datatype;
import java.net.MalformedURLException;
import java.net.URLStreamHandler;
import java.util.HashMap;

/**
 *
 * @author The Kito < blankitoracing@gmail.com >
 */

public class URL 
{
    private String protocol;
    private String user;
    private String password;
    private String host;
    private Integer port;
    private Path path;
    private HashMap<String,String> params;

    public URL(java.net.URL context, String spec) throws MalformedURLException {this(new java.net.URL(context, spec));}
    public URL(java.net.URL context, String spec, URLStreamHandler handler) throws MalformedURLException {this(new java.net.URL(context, spec, handler));}    
    public URL(String protocol, String host, int port, String file) throws MalformedURLException {this(new java.net.URL(protocol, host, port, file));}
    public URL(String protocol, String host, String file) throws MalformedURLException {this(new java.net.URL(protocol, host, file));}
    public URL(String spec) throws MalformedURLException {this(new java.net.URL(spec));}
    public URL(URL context, String spec) throws MalformedURLException {this(new java.net.URL(context.getURL(), spec));}
    public java.net.URL getURL() throws MalformedURLException{return new java.net.URL(this.toString());}
 
    public String getProtocol() {return protocol;}
    public String getUser() {return user;}
    public String getPassword() {return password;}
    public String getHost() {return host;}
    public Integer getPort() {return port;}
    public Path getPath() {return path;}
    public HashMap<String, String> getParams() {return params;}
    
    public void setProtocol(String protocol) {this.protocol = protocol;}
    public void setUser(String user) {this.user = user;}
    public void setPassword(String password) {this.password = password;}
    public void setHost(String host) {this.host = host;}
    public void setPort(Integer port) {this.port = port;}    
    public void setParam(String key,String value) {this.params.put(key, value);}
    
    private URL(java.net.URL u) throws MalformedURLException 
    {      
        this.protocol = u.getProtocol();                
        this.path = new Path(u.getPath(),'/');
        
        if(u.getHost()!=null && !u.getHost().isEmpty())
            this.host = u.getHost();        
        
        if(u.getPort()!=-1)
            this.port = u.getPort();    
        
        if(u.getUserInfo()!=null)
        {
            String[] s = u.getUserInfo().split("\\:",2); 
            this.user = s[0];
            if(s.length>1)
                this.password = s[1];            
        }
        
        if(u.getQuery()!=null)
        {
            for(String s : u.getQuery().split("\\&"))
            {
                String[] ss = s.split("\\=", 2);
                
                if(ss.length==1)
                    params.put(ss[0], new String());
                else
                    params.put(ss[0], ss[1]);
            }
        }
    } 

    @Override
    public String toString() {
        String url = "";

        if(this.protocol!=null)
            url += this.protocol + "://";                 
        
        if(this.host!=null)
        {
            if(this.user!=null)
            {
                url += this.user;   

                if(this.password!=null)
                    url += ":" + this.password;

                url += "@";   
            }
            
            url += this.host;
            
            if(this.port!=null)
                url += ":" + String.valueOf(this.port);            
        }
        
        if(this.path!=null)
            url += this.path.toString();
        
        if(this.params!=null && this.params.size()>0)
        {
            url += "?";
            for(String key : this.params.keySet())
            {
                url += key;
                
                if(this.params.get(key)!=null && this.params.get(key).isEmpty()==false)                
                    url += "=" + this.params.get(key);
                
                url += "&";
            }
            
            url = url.substring(0,url.length()-2);
        }
        
        return url;
    }   
}
