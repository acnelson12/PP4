/**
 * The RightAscension class stores an object's right ascension
 * for use in astronomy programs.
 * <p>
 * This class is useful for parsing right ascensions from Strings
 * and converting right ascensions to different formats.
 **/

public class RightAscension
{
    /* Instance Constants */
    private final int    HOURS;
    private final int    MINUTES;
    private final double SECONDS;
    
    /*== Constructors ==*/
    
    /**
     * Constructs a RightAscension object from a
     * String.
     * <p>
     * The string must be in the format of doubles
     * separated by the hour, minute, or second
     * symbols.  Acceptable symbols are h, and H
     * for hours; m, and M for minutes;
     * s, and S for seconds.  If no symbols
     * are used, the String will be treated as a double
     * containing the value in hours.
     * 
     * @param ra
     *      a String representing the right ascension.
     **/
    public RightAscension(  String ra )
    {
        /* Local Constants */
        final char[] HOUR_SYMBOLS   = { 'h', 'H' };
        final char[] MINUTE_SYMBOLS = { 'm', 'M' };
        final char[] SECOND_SYMBOLS = { 's', 'S' };
        
        /* Local Variables */
        int hourIndex   = -1;
        int minuteIndex = -1;
        int secondIndex = -1;
        Hour   hours   = new Hour(   0 );
        Minute minutes = new Minute( 0 );
        Second seconds = new Second( 0 );
        boolean stringIsDouble = true;
        
        /* Parse Right Ascension */
        if ( '-' == ra.charAt(0) )
            throw new RuntimeException( "No negative RAs" );
        
        for ( char symbol : HOUR_SYMBOLS )
        {
            hourIndex = ra.indexOf( symbol );
            if ( hourIndex != -1 )
            {
                stringIsDouble = false;
                break;
            }
        }
        
        for ( char symbol : MINUTE_SYMBOLS )
        {
            minuteIndex = ra.indexOf( symbol );
            if ( minuteIndex != -1 )
            {
                stringIsDouble = false;
                break;
            }
        }
        
        for ( char symbol : SECOND_SYMBOLS )
        {
            secondIndex = ra.indexOf( symbol );
            if ( secondIndex != -1 )
            {
                stringIsDouble = false;
                break;
            }
        }
        
        if ( stringIsDouble )
            hours = new Hour( Math.abs( Double.parseDouble( ra ) ) );
        else
        {
            if ( 0 < hourIndex )
                // The absolute value is taken as a percaution.
                hours = new Hour( Math.abs( Double.parseDouble(
                          ra.substring( 0, hourIndex ) ) ) );
            if ( hourIndex < minuteIndex )
                minutes = new Minute( Math.abs( Double.parseDouble(
                             ra.substring( hourIndex+1, minuteIndex ) ) ) );
            else
                /*
                 * minuteIndex must be modified because of
                 * the way it is used to get seconds.
                 */
                minuteIndex = hourIndex;
            if ( minuteIndex < secondIndex )
                seconds = new Second( Math.abs( Double.parseDouble(
                              ra.substring( minuteIndex+1, secondIndex ) ) ) );
        }
        seconds = new Second( hours.secondPart() +
                              minutes.secondPart() +
                              seconds.secondPart() );
        minutes = new Minute( hours.minutePart() +
                              minutes.minutePart() +
                              seconds.minutePart() );
        hours   = new Hour  ( hours.hourPart() +
                              minutes.hourPart() +
                              seconds.hourPart() );
        
        this.HOURS   = hours.hourPart();
        this.MINUTES = minutes.minutePart();
        this.SECONDS = seconds.secondPart();
    }
    
    public RightAscension( final int    HOURS,
                           final int    MINUTES,
                           final double SECONDS )
    {
        if ( 0 > HOURS || 0 > MINUTES || 0 > SECONDS )
            throw new RuntimeException();
        this.HOURS   = HOURS;
        this.MINUTES = MINUTES;
        this.SECONDS = SECONDS;
    }
    
    public RightAscension( final int HOURS, final double MINUTES )
    {
        this( HOURS, (int)MINUTES, (60*MINUTES)%60 );
    }
    
    public RightAscension( final double HOURS )
    {
        this( (int)HOURS, (int)((60*HOURS)%60), (int)((3600*HOURS)%60) );
    }

    /*== Accessors ==*/
    @Override
    public String toString()
    {
        /* Local Variables */
        String output = "";
        
        /* Calculate Output */
        output += String.format( "%02dh%02dm%05.2fs",
                                 HOURS, MINUTES, SECONDS );
        
        return output;
    }
    
    public double toDouble()
    {
        /* Local Variables */
        double raAsDouble;
        
        /* Convert Declination to double */
        raAsDouble = HOURS +
                     ( (double)MINUTES / 60 ) +
                     ( SECONDS / 3600 );
        
        return raAsDouble;
    }
    
    public double toDegrees()
    {
        return toDouble() * 15;
    }
    
    /**
     * Returns a double representation of
     * the right ascension in radians.
     * 
     * @return
     *      the right ascension in radians as a double.
     **/
    public double toRadians()
    {
        return toDouble() * Math.PI / 12;
    }
    
    @Override
    public boolean equals( Object that )
    {
        if ( this.toDouble() == ((RightAscension)that).toDouble() )
            return true;
        return false;
    }
    
    public static RightAscension parseRightAscension( String ra )
    {
        return new RightAscension( ra );
    }
    
    /*== Driver ==*/
    public static void main( String[] args )
    {
        RightAscension test0 = new RightAscension( 0.54 );
        RightAscension test1 = new RightAscension( "18h24m10.35s" );
        RightAscension test2 = new RightAscension( "1.54" );
        System.out.println( test0 );
        System.out.println( test1 );
        System.out.println( test1.toDouble() );
        System.out.println( test2 );
        System.out.println( test2.toDegrees() );
    }
    
    /*== Inner Classes ==*/
    private class Hour
    {
        private final double VALUE;
        
        private Hour( final double VALUE )
        {
            this.VALUE = VALUE;
        }
        
        private int hourPart()
        {
            return (int)VALUE;
        }
        
        private int minutePart()
        {
            return (int)( (VALUE * 60) % 60 );
        }
        
        private double secondPart()
        {
            return (VALUE * 3600) % 60 ;
        }
    }
    
    private class Minute
    {
        private double VALUE;
        
        private Minute( final double VALUE )
        {
            this.VALUE = VALUE;
        }
        
        private int hourPart()
        {
            return (int)( VALUE / 60 );
        }
        
        private int minutePart()
        {
            return (int)( VALUE % 60 );
        }
        
        private double secondPart()
        {
            return (VALUE * 60) % 60;
        }
    }
    
    private class Second
    {
        private double VALUE;
        
        private Second( final double VALUE )
        {
            this.VALUE = VALUE;
        }
        
        private int hourPart()
        {
            return (int)( VALUE / 3600 );
        }
        
        private int minutePart()
        {
            return (int)( (VALUE / 60) % 60 );
        }
        
        private double secondPart()
        {
            return VALUE % 60;
        }
    }
}
