/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blktech.types;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 *
 * @author ASUS
 */
public class Path 
{
//    public static String clearPath(String path)
//    {
//        path = path.replace('\\', File.separatorChar);
//        path = path.replace('/', File.separatorChar);
//        
//        ArrayList<String> es = new ArrayList<String>();
//        
//        for(String e : path.split(Pattern.quote(File.separator)))
//        {
//            if (e.isEmpty())
//                continue;
//            
//            if(e.equalsIgnoreCase("."))
//                continue;                        
//            
//            es.rem
//            es.add(e);
//        }
//        
//    }
    
    char separatorChar;
    String elements[];

    public Path(String path) {this(path, File.separatorChar);}
    
    public Path(String path, char separatorChar) 
    {
        this(path.replace('\\', separatorChar).replace('/', separatorChar).split(Pattern.quote(String.valueOf(separatorChar))));
        this.separatorChar = separatorChar;        
    }

    private Path(String[] elements) 
    {       
        ArrayList<String> aux = new ArrayList<String>();
                
        for(String e : elements)
        {
            if (e.isEmpty())
                continue;
            
            if(e.equalsIgnoreCase("."))
                continue;
            
            if(e.equalsIgnoreCase("..") && aux.size()>0)
                aux.remove(aux.size()-1);
                       
            aux.add(e);
            
        }

       
        this.elements = aux.toArray(new String[aux.size()]);
    }

    public Path getParent()
    {
        return new Path(Arrays.copyOfRange(this.elements , 0, this.elements.length-2));        
    }

    public Path getChild(String name)
    {
        String tmp[] = Arrays.copyOfRange(this.elements , 0, this.elements.length);
        tmp[this.elements.length] = name;
        return new Path(tmp);        
    }

    public Path combine(Path extraPath)
    {        
        String tmp[] = Arrays.copyOfRange(this.elements , 0, this.elements.length+extraPath.elements.length);
        for(int i = 0 ; i < extraPath.elements.length ; i++)
            tmp[this.elements.length+i] = extraPath.elements [i];

        return new Path(tmp);        
    }
    
    @Override
    public String toString() {
        String s = new String();
        for(String w : this.elements)
            s +=  String.valueOf(this.separatorChar) + w;
        return s; 
    }
}
