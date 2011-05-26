package org.hybird.conversion.converters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hybird.conversion.Converter;

public class StringToDate extends Converter <String, Date>
{
    private SimpleDateFormat format = new SimpleDateFormat ("yyyy.MM.dd HH:mm:ss");
    
    @Override
    public Date convert (String from)
    {
        try
        {
            return format.parse (from);
        }
        catch (ParseException e)
        {
            throw new IllegalArgumentException ("The String '" + from + "' can't be converted to a Date with the 'yyyy.MM.dd HH:mm:ss' format: " + e);
        }
    }
}
