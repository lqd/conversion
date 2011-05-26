package org.hybird.conversion.converters;

import org.hybird.conversion.Converter;

public class StringToByte extends Converter <String, Byte>
{
    @Override
    public Byte convert (String from)
    {
        return Byte.parseByte (from);
    }
}