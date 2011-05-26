package org.hybird.conversion.converters;

import org.hybird.conversion.Converter;

public class StringToShort extends Converter <String, Short>
{
    @Override
    public Short convert (String from)
    {
        return Short.parseShort (from);
    }
}