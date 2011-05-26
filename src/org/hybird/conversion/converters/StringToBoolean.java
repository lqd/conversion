package org.hybird.conversion.converters;

import org.hybird.conversion.Converter;

public class StringToBoolean extends Converter <String, Boolean>
{
    @Override
    public Boolean convert (String from)
    {
        return Boolean.parseBoolean (from);
    }
}