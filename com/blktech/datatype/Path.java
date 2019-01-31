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
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 *
 * @author The Kito < blankitoracing@gmail.com >
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
        ArrayList<String> aux = new ArrayList<>();
                
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
