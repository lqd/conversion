package org.hybird.conversion;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.awt.Color;

import org.hybird.conversion.converters.DoubleToString;
import org.hybird.conversion.converters.StringToColor;
import org.junit.Test;

public class ConversionTest
{
    @Test
    public void find()
    {
        Converter<Double, String> converter = Converter.find (Double.class, String.class);
        assertNotNull (converter);
        assertThat (converter, is (DoubleToString.class));
    }
    
    @Test
    public void color()
    {
        StringToColor converter = new StringToColor ();
        
        assertEquals (Color.red, converter.convert ("Color.red"));
        assertEquals (Color.red, converter.convert ("Color.RED"));
        assertEquals (Color.red, converter.convert ("red"));
        assertEquals (Color.red, converter.convert ("RED"));
        assertEquals (Color.red, converter.convert ("#FF0000"));
        assertEquals (Color.red, converter.convert ("#FF0000FF"));
        assertEquals (Color.red, converter.convert ("0xFF0000"));
        assertEquals (Color.red, converter.convert ("0xFF0000FF"));
        assertEquals (Color.red, converter.convert ("0xF00"));
        assertEquals (Color.red, converter.convert ("#F00"));
        assertEquals (Color.red, converter.convert ("Color.rgb(255,0,0)"));
        assertEquals (Color.red, converter.convert ("rgb(255,0,0)"));
        assertEquals (Color.red, converter.convert ("Color.rgb (255,0,0)"));
        assertEquals (Color.red, converter.convert ("rgb (255,0,0)"));
        assertEquals (Color.red, converter.convert ("Color.rgb(255, 0, 0)"));
        assertEquals (Color.red, converter.convert ("rgb(255, 0, 0)"));
        assertEquals (Color.red, converter.convert ("Color.rgb (255, 0, 0)"));
        assertEquals (Color.red, converter.convert ("rgb (255, 0, 0)"));
        assertEquals (Color.red, converter.convert ("Color.rgba (255, 0, 0, 255)"));
        assertEquals (Color.red, converter.convert ("rgba (255, 0, 0, 255)"));
        
        assertEquals (new Color (255, 12, 104), converter.convert ("Color.rgb (255,    12,  104)"));
        assertEquals (new Color (1, 2, 3), converter.convert ("rgb(1,2,3)"));
        assertEquals (new Color (255, 0, 0, 0), converter.convert ("#FF000000"));
        assertEquals (new Color (255, 12, 104, 125), converter.convert ("Color.rgba (255, 12, 104, 125)"));
        assertEquals (new Color (1, 2, 3, 4), converter.convert ("rgba(1, 2,3, 4)"));
        
    }
}
