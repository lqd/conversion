package org.hybird.conversion.converters;

import org.hybird.conversion.Converter;

public class StringToFloat extends Converter <String, Float>
{
    @Override
    public Float convert (String from)
    {
        return Float.parseFloat (from);
    }
}