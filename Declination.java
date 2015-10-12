/**
 * The Declination class stores an object's declination
 * for use in astronomy programs.
 * <p>
 * This class is useful for parsing declinations from Strings
 * and converting declinations to different formats.
 **/

public class Declination implements Comparable<Declination>
{
	/* Instance Constants */
	private final boolean POSITIVE;
	private final int     DEGREES;
	private final int     ARCMINUTES;
	private final double  ARCSECONDS;
	
	/*== Constructors ==*/
	
	/**
	 * Constructs a Declination object from a
	 * String.
	 * <p>
	 * The string must be in the format of doubles
	 * separated by the degree, arcminute, or arcsecond
	 * symbols.  Acceptable symbols are &deg;, d, and D
	 * for degrees; &prime;, ', m, and M for arcminutes;
	 * &Prime;, ", s, and S for arcseconds.  If no symbols
	 * are used, the String will be treated as a double
	 * containing the value in degrees.  Examples of
	 * acceptable input are: <p>
	 * "-34&deg;23&prime;04.5&Prime;"<p>
	 * "+22d12\'13\""<p>
	 * "22D24m36&Prime;"<p>
	 * "3.14159"
	 * 
	 * @param dec
	 * 		a String representing the declination.
	 **/
	public Declination(  String dec )
	{
		/* Local Constants */
		final char[] DEGREE_SYMBOLS    = { '°', 'd', 'D' };
		final char[] ARCMINUTE_SYMBOLS = { '′', '\'', 'm', 'M' };
		final char[] ARCSECOND_SYMBOLS = { '″', '\"', 's', 'S' };
		
		/* Local Variables */
		int degreeIndex    = -1;
		int arcminuteIndex = -1;
		int arcsecondIndex = -1;
		Degree    degrees    = new Degree(    0 );
		Arcminute arcminutes = new Arcminute( 0 );
		Arcsecond arcseconds = new Arcsecond( 0 );
		boolean stringIsDouble = true;
		
		/* Parse Declination */
		if ( dec.charAt(0) == '−' || // minus sign
		     dec.charAt(0) == '-' || // hyphen
		     dec.charAt(0) == '‒' || // en dash
		     dec.charAt(0) == '—' || // em dash
		     dec.charAt(0) == '―' )  // horizontal bar
		{
			this.POSITIVE = false;
			dec = dec.substring( 1 );
		}
		else
		{
			this.POSITIVE = true;
			if ( dec.charAt(0) == '+' )
				dec = dec.substring( 1 );
		}
		
		for ( char symbol : DEGREE_SYMBOLS )
		{
			degreeIndex = dec.indexOf( symbol );
			if ( degreeIndex != -1 )
			{
				stringIsDouble = false;
				break;
			}
		}
		
		for ( char symbol : ARCMINUTE_SYMBOLS )
		{
			arcminuteIndex = dec.indexOf( symbol );
			if ( arcminuteIndex != -1 )
			{
				stringIsDouble = false;
				break;
			}
		}
		
		for ( char symbol : ARCSECOND_SYMBOLS )
		{
			arcsecondIndex = dec.indexOf( symbol );
			if ( arcsecondIndex != -1 )
			{
				stringIsDouble = false;
				break;
			}
		}
		
		if ( stringIsDouble )
			degrees = new Degree( Math.abs( Double.parseDouble( dec ) ) );
		else
		{
			if ( 0 < degreeIndex )
				// The absolute value is taken as a percaution.
				degrees = new Degree( Math.abs( Double.parseDouble(
						  dec.substring( 0, degreeIndex ) ) ) );
			if ( degreeIndex < arcminuteIndex )
				arcminutes = new Arcminute( Math.abs( Double.parseDouble(
							 dec.substring( degreeIndex+1, arcminuteIndex ) ) ) );
			else
				/*
				 * arcminuteIndex must be modified because of
				 * the way it is used to get arcseconds.
				 */
				arcminuteIndex = degreeIndex;
			if ( arcminuteIndex < arcsecondIndex )
				arcseconds = new Arcsecond( Math.abs( Double.parseDouble(
							 dec.substring( arcminuteIndex+1, arcsecondIndex ) ) ) );
		}
		arcseconds = new Arcsecond( degrees.arcsecondPart() +
		                            arcminutes.arcsecondPart() +
		                            arcseconds.arcsecondPart() );
		arcminutes = new Arcminute( degrees.arcminutePart() +
		                            arcminutes.arcminutePart() +
		                            arcseconds.arcminutePart() );
		degrees    = new Degree   ( degrees.degreePart() +
		                            arcminutes.degreePart() +
		                            arcseconds.degreePart() );
		
		this.DEGREES    = degrees.degreePart();
		this.ARCMINUTES = arcminutes.arcminutePart();
		this.ARCSECONDS = arcseconds.arcsecondPart();
	}
	
	/**
	 * Constructs a Declination object
	 * with given values for degrees, arcminutes,
	 * and arcseconds.
	 * 
	 * @param DEGREES
	 * 		the degrees component.
	 * @param ARCMINUTES
	 * 		the arcminutes component.
	 * @param ARCSECONDS
	 * 		the arcseconds component.
	 **/
	public Declination( final int    DEGREES,
	                    final int    ARCMINUTES,
	                    final double ARCSECONDS )
	{
		if ( DEGREES < 0 )
		{
			this.POSITIVE = false;
			this.DEGREES  = DEGREES * -1;
		}
		else
		{
			this.POSITIVE = true;
			this.DEGREES  = DEGREES;
		}
		this.ARCMINUTES = ARCMINUTES;
		this.ARCSECONDS = ARCSECONDS;
	}
	
	/**
	 * Constructs a Declination object
	 * with given values for degrees and arcminutes.
	 * 
	 * @param DEGREES
	 * 		the degrees component.
	 * @param ARCMINUTES
	 * 		the arcminutes component.
	 **/
	public Declination( final int DEGREES, final double ARCMINUTES )
	{
		if ( DEGREES < 0 )
		{
			this.POSITIVE = false;
			this.DEGREES  = DEGREES * -1;
		}
		else
		{
			this.POSITIVE = true;
			this.DEGREES  = DEGREES;
		}
		this.ARCMINUTES = (int)ARCMINUTES;
		this.ARCSECONDS = (60*ARCMINUTES)%60;
	}
	
	/**
	 * Constructs a Declination object from a
	 * given number of degrees.
	 * 
	 * @param DEGREES
	 * 		the degrees of declination.
	 **/
	public Declination( final double DEGREES )
	{
		/* Local Variables */
		double degrees = Math.abs( DEGREES );
		double arcminutes = (60*degrees)%60;
		
		if ( DEGREES < 0 )
			this.POSITIVE = false;
		else
			this.POSITIVE = true;
		
		this.DEGREES    = (int)degrees;
		this.ARCMINUTES = (int)arcminutes;
		this.ARCSECONDS = (int)(60*arcminutes)%60;
	}

	/*== Accessors ==*/
	
	/**
	 * Returns a String representation of the
	 * declination.
	 * <p>
	 * The String is in the form "&plusmn;##&deg;##&prime;##.#&Prime;".
	 * 
	 * @return
	 * 		a String representation of this object.
	 **/
	@Override
	public String toString()
	{
		/* Local Variables */
		String output = "";
		
		/* Calculate Output */
		output += POSITIVE ? '+' : '−';
		output += String.format( "%02d°%02d′%04.1f″",
		                         DEGREES, ARCMINUTES, ARCSECONDS );
		
		return output;
	}
	
	/**
	 * Returns a double representation of
	 * the degrees of declination.
	 * 
	 * @return
	 * 		the degrees of declination as a double.
	 **/
	public double toDouble()
	{
		/* Local Variables */
		double decAsDouble;
		
		/* Convert Declination to double */
		decAsDouble = DEGREES +
		              ( (double)ARCMINUTES / 60 ) +
		              ( ARCSECONDS / 3600 );
		if ( !POSITIVE )
			decAsDouble *= -1;
		
		return decAsDouble;
	}
	
	/**
	 * Returns true if the given Object is equal
	 * to this Declination.
	 * <p>
	 * The comparison is performed by calling
	 * the Objects' toDouble() methode and
	 * comparing the result. 
	 * 
	 * @param that
	 * 		the Object to compare.
	 * @return
	 * 		true if that equals this.
	 **/
	@Override
	public boolean equals( Object that )
	{
		if ( this.toDouble() == ((Declination)that).toDouble() )
			return true;
		return false;
	}
	
	/**
	 * Compares this Declination with the specified Declination
	 * for order. Returns a negative integer, zero, or a positive
	 * integer as this Declination is less than, equal to, or
	 * greater than the specified Declination.
	 * 
	 * @param that
	 * 		the Declination being compared to.
	 * @return
	 * 		a negative integer, zero, or a positive integer
	 * 		as this Declination is less than, equal to, or greater
	 * 		than the specified Declination.
	 **/
	public int compareTo( Declination that )
	{
		if ( this.toDouble() < that.toDouble() )
			return -1;
		if ( this.toDouble() > that.toDouble() )
			return 1;
		else
			return 0;
	}
	
	/**
	 * Parses the string argument as a declination.
	 * 
	 * @param dec
	 * 		the String to parse.
	 * @return
	 * 		a Declination represented by the argument.
	 **/
	public static Declination parseDeclination( String dec )
	{
		return new Declination( dec );
	}
	
	/**
	 * Parses the string argument as a declination and
	 * returns its double value
	 * 
	 * @param dec
	 * 		the String to parse.
	 * @return
	 * 		the double value of the degrees of declination.
	 **/
	public static double toDouble( String dec )
	{
		return parseDeclination( dec ).toDouble();
	}
	
	/*== Driver ==*/
	public static void main( String[] args )
	{
		Declination test0 = new Declination( -0.54 );
		Declination test1 = new Declination( "-34°23′04.5″" );
		Declination test2 = new Declination( "34.523566" );
		System.out.println( test0 );
		System.out.println( test1 );
		System.out.println( test1.toDouble() );
		System.out.println( test2 );
		System.out.println( test2.toDouble() );
	}
	
	/*== Inner Classes ==*/
	private class Degree
	{
		private final double VALUE;
		
		private Degree( final double VALUE )
		{
			this.VALUE = VALUE;
		}
		
		private int degreePart()
		{
			return (int)VALUE;
		}
		
		private int arcminutePart()
		{
			return (int)( (VALUE * 60) % 60 );
		}
		
		private double arcsecondPart()
		{
			return (VALUE * 3600) % 60 ;
		}
	}
	
	private class Arcminute
	{
		private double VALUE;
		
		private Arcminute( final double VALUE )
		{
			this.VALUE = VALUE;
		}
		
		private int degreePart()
		{
			return (int)( VALUE / 60 );
		}
		
		private int arcminutePart()
		{
			return (int)( VALUE % 60 );
		}
		
		private double arcsecondPart()
		{
			return (VALUE * 60) % 60;
		}
	}
	
	private class Arcsecond
	{
		private double VALUE;
		
		private Arcsecond( final double VALUE )
		{
			this.VALUE = VALUE;
		}
		
		private int degreePart()
		{
			return (int)( VALUE / 3600 );
		}
		
		private int arcminutePart()
		{
			return (int)( (VALUE / 60) % 60 );
		}
		
		private double arcsecondPart()
		{
			return VALUE % 60;
		}
	}
}
