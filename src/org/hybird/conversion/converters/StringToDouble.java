package org.hybird.conversion.converters;

import org.hybird.conversion.Converter;

public class StringToDouble extends Converter <String, Double>
{
    @Override
    public Double convert (String from)
    {
        return Double.parseDouble (from);
    }
}