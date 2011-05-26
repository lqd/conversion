package org.hybird.conversion.converters;

import org.hybird.conversion.Converter;

public class StringToLong extends Converter <String, Long>
{
    @Override
    public Long convert (String from)
    {
        return Long.parseLong (from);
    }
}