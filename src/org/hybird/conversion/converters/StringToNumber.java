package org.hybird.conversion.converters;

import org.hybird.conversion.Converter;

public class StringToNumber extends Converter <String, Number>
{
    @Override
    public Number convert (String from)
    {
        if (from.contains ("."))
            return Double.parseDouble (from);
        return Long.parseLong (from);
    }
}