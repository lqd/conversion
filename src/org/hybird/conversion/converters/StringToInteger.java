package org.hybird.conversion.converters;

import org.hybird.conversion.Converter;

public class StringToInteger extends Converter <String, Integer>
{
    @Override
    public Integer convert (String from)
    {
        return Integer.parseInt (from);
    }
}