package org.hybird.conversion.converters;

import org.hybird.conversion.Converter;

public class DoubleToString extends Converter <Double, String>
{
    @Override
    public String convert (Double from)
    {
        return Double.toString (from);
    }
}