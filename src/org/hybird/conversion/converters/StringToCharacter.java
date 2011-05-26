package org.hybird.conversion.converters;

import org.hybird.conversion.Converter;

public class StringToCharacter extends Converter <String, Character>
{
    @Override
    public Character convert (String from)
    {
        if (from.length () == 1)
            return from.charAt (0);
        return null;
    }
}