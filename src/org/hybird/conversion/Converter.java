package org.hybird.conversion;

import java.awt.Color;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hybird.conversion.converters.*;


@SuppressWarnings("unchecked")
public abstract class Converter <From, To>
{
    private static final Map<Key, Converter> registry = new HashMap<Key, Converter> ();
    
    public static <From, To> void register (Class<From> from, Class<To> to, Converter converter)
    {
        registry.put (new Key (from, to), converter);
    }
    
    public static <From, To> To convert (Class<From> from, Class<To> to, From value)
    {
    	return find (from, to).convert (value);
    }
    
    public static <From, To> Converter<From, To> find (Class<From> from, Class<To> to)
    {
        return registry.get (new Key (from, to));
	}
    
    static
    {
        register (Double.class, String.class, new DoubleToString());

        register (String.class, Float.class, new StringToFloat());
        register (String.class, Double.class, new StringToDouble());
        register (String.class, Integer.class, new StringToInteger());
        register (String.class, Long.class, new StringToLong());
        register (String.class, Character.class, new StringToCharacter());
        register (String.class, Short.class, new StringToShort());
        register (String.class, Byte.class, new StringToByte());
        register (String.class, Number.class, new StringToNumber());
        register (String.class, Boolean.class, new StringToBoolean());
        
        register (String.class, Date.class, new StringToDate());
    }
    
    public static void registerSwingConverters ()
    {
        register (String.class, Color.class, new StringToColor ());
    }
    
    public abstract To convert (From from);
    
    private static class Key
    {
        private Class<?> from;
        private Class<?> to;

        public Key (Class<?> from, Class<?> to)
        {
            this.from = from;
            this.to = to;
        }

        @Override
        public int hashCode ()
        {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((from == null) ? 0 : from.hashCode ());
            result = prime * result + ((to == null) ? 0 : to.hashCode ());
            return result;
        }

        @Override
        public boolean equals (Object obj)
        {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass () != obj.getClass ())
                return false;
            Key other = (Key) obj;
            if (from == null)
            {
                if (other.from != null)
                    return false;
            }
            else if (!from.equals (other.from))
                return false;
            if (to == null)
            {
                if (other.to != null)
                    return false;
            }
            else if (!to.equals (other.to))
                return false;
            return true;
        }
    }
}