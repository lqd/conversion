package org.hybird.conversion.converters;

import java.awt.Color;
import java.lang.reflect.Field;

import org.hybird.conversion.Converter;

public class StringToColor extends Converter<String, Color>
{
    @Override
    public Color convert (String from)
    {
    	String color = from;
    	
    	if (color.startsWith ("Color."))
    	{
    		// Color.red, Color.RED
        	// Color.rgb, Color.rgba
    		
    		color = color.substring ("Color.".length());
    		
    		if (color.startsWith ("rgb") == false)
    			return getFieldNamed (from, color);
    	}
    	
    	if (color.startsWith ("rgb"))
    	{
    		// rgb, rgba
    	    
    	    String type = color.substring (0, color.indexOf ("("));
    	    
    	    if ("rgb".equals (type.trim()))
    	    {
    	    	color = color.substring (type.length());
    	    	color = color.substring (1, color.length() - 1);
    	    	
    	    	String[] parameters = color.split (",");
				
				try
	            {
	                int r = Integer.parseInt (parameters[0].trim());
	                int g = Integer.parseInt (parameters[1].trim());
	                int b = Integer.parseInt (parameters[2].trim());
	                
	                return new Color (r, g, b);
	            }
	            catch (NumberFormatException e)
	            {
	                throw new IllegalArgumentException ("Could not create color from '" + from + "': " + e);
	            }
    	    }
    	    else if (type.startsWith ("rgba"))
    	    {
    	    	color = color.substring (type.length());
    	    	color = color.substring (1, color.length() - 1);
    	    	
    	    	String[] parameters = color.split (",");
				
				try
	            {
	                int r = Integer.parseInt (parameters[0].trim());
	                int g = Integer.parseInt (parameters[1].trim());
	                int b = Integer.parseInt (parameters[2].trim());
	                int a = Integer.parseInt (parameters[3].trim());
	                
	                return new Color (r, g, b, a);
	            }
	            catch (NumberFormatException e)
	            {
	                throw new IllegalArgumentException ("Could not create color from '" + from + "': " + e);
	            }
    	    }
    	    else
    	    	throw new IllegalArgumentException ("Could not create color from '" + from + "', unknown factory: '" + type + "'");
    	}
    	
    	if (color.startsWith ("#"))
    	{
    	    // #C0C0C0 #C0C0C0C0 #CCC
    	    color = color.replace ("#", "0x");
    	}	
    		
    	if (color.startsWith ("0x"))
    	{
    		// OxFFFFFF OxFFFFFFFF 0xFFF
    	    int length = color.length();
    	    
    	    if (length == 3+2) // 0xFFF
    	    {
    	        color = "0x" + color.charAt (2) + color.charAt (2) + color.charAt (3) + color.charAt (3) + color.charAt (4) + color.charAt (4);
    	        length = color.length();
    	    }
    	    
    	    if (length != 6+2 && length != 8+2)
                throw new IllegalArgumentException ("Could not create color from '" + from + "'");
    	    
            if (length == 6+2)
                return Color.decode (color);
            
            try
            {
                int r = Integer.parseInt (color.substring (0+2, 4), 16);
                int g = Integer.parseInt (color.substring (2+2, 6), 16);
                int b = Integer.parseInt (color.substring (4+2, 8), 16);
                int a = Integer.parseInt (color.substring (6+2, 10), 16);
                
                return new Color (r, g, b, a);
            }
            catch (NumberFormatException e)
            {
                throw new IllegalArgumentException ("Could not create color from '" + from + "': " + e);
            }
    	}
    	
    	// let's say the String is a known color (for now: only the Color class constants)
    	// maybe add some colors from the HTML predefined ones or SWT, .NET, QT, X11 ?
    	return getFieldNamed (from, color);
    }

    private static Color getFieldNamed (String from, String fieldName)
    {
        try
        {
            Field field = Color.class.getDeclaredField (fieldName);
            return (Color) field.get (null);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException ("Could not create color from '" + from + "': " + e);
        }
    }
}