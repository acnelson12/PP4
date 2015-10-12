// add line counter
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.EOFException;
import java.io.FileNotFoundException;

public class PP4Reader extends BufferedReader
{	
	/*== Constructors ==*/
	public PP4Reader( File file ) throws FileNotFoundException
	{
		super( new FileReader( file ) );
	}
	
	public PP4Reader( String filename ) throws FileNotFoundException
	{
		super( new FileReader( new File( filename ) ) );
	}
	
	/*== Accessors ==*/
	public String readStatement() throws IOException
	{
		/* Local Variables */
		boolean inComment   = false;
		boolean inLiteral   = false;
		boolean escapeChar  = false;
		boolean inStatement = true;
		String  statement   = null;
		int    currChar    = read();

		while ( inStatement && currChar >= 0 )
		{
			//System.out.println( (char)currChar );
			/* Read Statement */
			if ( inComment )
			{
				//System.out.println( "In Comment" );
				if ( '\n' == currChar )
					inComment = false;
			}
			else
			{
				if ( inLiteral )
				{
					if ( escapeChar )
						escapeChar = false;
					else
						if ( '\"' == currChar )
							inLiteral = false;
						else
							if ( '\\' == currChar )
								escapeChar = true;
					statement+= (char)currChar;
				}

				else
					if ( ';' == currChar )
					{
						if ( statement == null )
								statement = " ";
						inStatement = false;
					}
					else
						if ( '#' == currChar )
							inComment = true;
						else
						{
							if ( '\"' == currChar )
								inLiteral = true;
							if ( statement == null )
								statement = Character.toString((char)currChar);
							else
								statement+= (char)currChar;
						}
			}
			currChar = read();
		}
		
		if ( statement != null )
		{
			statement = removeExcessSpace(statement);
			
			if ( inStatement && -1 == currChar )
				throw new EOFException( "Unexpected EOF" );
		}
		
		return statement;
	}
	
	public boolean hasNextStatement() throws IOException
	{
		/* Local Variables */
		String statement = null;
		
		/* Peek ahead for next statement */
		mark( 8000 );
		statement = readStatement();
		
		/* Jump back and analyze */
		reset();
		if ( statement != null )
			return true;
		return false;
	}
	
	private String removeExcessSpace( String s )
	{
		s = s.trim();
		while ( s.contains( "\n" ) )
		{
			s = s.substring( 0, s.indexOf( "\n" ) ) +
			    " " + s.substring( s.indexOf( "\n" ) + 1 );
		}
		
		while ( s.contains( "\t" ) )
		{
			s = s.substring( 0, s.indexOf( "\t" ) ) +
			    ' ' + s.substring( s.indexOf( "\t" ) + 1 );
		}
		
		while ( s.contains( "  " ) )
		{
			s = s.substring( 0, s.indexOf( "  " ) ) +
			    ' ' + s.substring( s.indexOf( "  " ) + 2 );
		}
		return s;
	}
	
	public static void main( String[] args ) throws Throwable
	{
		PP4Reader test = new PP4Reader( "test.pp4" );
		String statement = "";
		
		while ( test.hasNextStatement() )
		{
			statement = test.readStatement();
			System.out.println( statement );
		}
	}
}
