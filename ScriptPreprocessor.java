import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ScriptPreprocessor
{
	private PP4Reader in;
	
	public ScriptPreprocessor( File file ) throws FileNotFoundException
	{
		in = new PP4Reader( file );
	}
	
	public ScriptPreprocessor( String filename ) throws FileNotFoundException
	{
		in = new PP4Reader( filename );
	}
	
	public void preprocess() throws IOException
	{
		/*
		 * Perhaps some basic validation should be
		 * performed before we get too deep into this.
		 * 
		 * The main reason for this is to find
		 * any problems before the file inclusion
		 * begins to generate huge files.
		 * 
		 * Or not.
		 * process included files without merging them.
		 */
		validateScript();
		
		/*
		 * Discard comments
		 */
		
		/*
		 * Certain parameters need to be fetched
		 * in some degree of order.
		 * First, the map size parameters must be
		 * fetched because they can serve as a filter
		 * when including data from other files.
		 */
		RightAscension ra = getRightAscensionFromScript();
		//getDeclinationFromScript();
		//getHeightFromScript();
		//getWidthFromScript();
		//getGradPerCmFromScript();
		
		/*
		 * Now the includes can be processed.
		 */
		//getIncludesFromScript();
		
		//for ( int i = 0; i < includes.length; i++ )
		//{
		//	preprocess( includes[i] );
		//	includeFile( includes[i] );
		//}
	}
	
	public RightAscension getRightAscensionFromScript() throws IOException
	{
		/* Local Variables */
		Statement statement = getStatement( "right_ascension", 1 );
		return new RightAscension( statement.ARGS[0] );
	}
	
	private Statement nextStatement() throws IOException
	{
		/* Local Variables */
		String   s = null;
		Statement statement = null;
		
		s = in.readStatement();
		if ( s == null )
			return null;
		statement = new Statement( parseCommand( s ), parseArgs( s ) );
		
		return statement;
	}
	
	public void validateScript() throws IOException
	{
		/* Local Variables */
		String input = "";
		String statement;
		String command;
		String[] args;
		
		/* Validate Script */
		while ( in.hasNextStatement() )
		{
			statement = in.readStatement();
			command = parseCommand( statement );
			args = parseArgs( statement );
			
			switch ( command )
			{
				case "right_ascension":
					validateWindowCommand( command, args );
					break;
				case "declination":
					validateWindowCommand( command, args );
					break;
				case "height":
					validateWindowCommand( command, args );
					break;
				case "width":
					validateWindowCommand( command, args );
					break;
				case "grad_per_cm":
					validateWindowCommand( command, args );
					break;
				case "include":
					validateInclude( command, args );
					break;
				case "output":
					validateOutput( command, args );
					break;
				case "switch":
					validateSwitch( command, args );
					break;
				case "set":
					validateSet( command, args );
					break;
				//case "add":
					//validateAdd( command, args );
					//break;
				//case "delete":
					//validateDelete( command, args );
					//break;
				//case "add_labels":
					//validateAddLabels( command, args );
					//break;
				//case "reposition":
					//validateReposition( command, args );
					//break;
				//case "set_label_text":
					//validateSetLabelText( command, args );
					//break;
				//case "text":
					//validateText( command, args );
					//break;
				case "star":
					validateStar( command, args );
					break;
				default:
					//throw new unknownCommandException( "Unknown command: " + command );
			}
		}
	}
	
	private void validateInclude( String command, String[] args )
	{
		//verify file exists
	}
	
	private void validateOutput( String command, String[] args )
	{
		//verify file can be created
	}
	
	private void validateSwitch( String command, String[] args )
	{
		//two operands
		//args[0] is valid parameter
		//args[1] is on or off
	}
	
	private void validateSet( String command, String[] args )
	{
		//two operands
		//args[0] is valid attribute
		//args[1] is valid
	}
	
	private void validateStar( String command, String[] args )
	{
		//three operands (for now)
		//args[0] is RightAscension
		//args[1] is Declination
		//args[2] is Magnitude
	}

	private Statement getStatement( String command, int numArgs ) throws IOException
	{
		/* Local Variables */
		Statement statement = null;
		
		do
		{
			statement = nextStatement();
		} while ( statement != null &&
		          !statement.COMMAND.equals( command ) );
		
		if ( statement != null && statement.ARGS.length != numArgs )
			throw new RuntimeException( "Too many arguments." );
		return statement;
	}
	
	private String parseCommand( String statement )
	{
		/* Local Variables */
		Scanner sc = new Scanner( statement );
		String command;
		
		/* Extract Command */
		command = sc.next();
		sc.close();
		
		return command.toLowerCase();
	}
	
	private String[] parseArgs( String statement )
	{
		/* Local Variables */
		ArrayList<String> args = new ArrayList<String>();
		Scanner sc = new Scanner( statement );
		String[] argsString;
		
		/* Skip Command */
		sc.next();
		
		/* Extract Arguments */
		while ( sc.hasNext() )
			args.add( sc.next() );
		
		sc.close();
		argsString = new String[args.size()];
		argsString = args.toArray( argsString );
		return argsString;
	}
	
	public void includeFile( File file )
	{
		
	}
	
	public static void main( String[] args ) throws Throwable
	{
		ScriptPreprocessor test = new ScriptPreprocessor( "test.pp4" );
		System.out.println( test.getRightAscensionFromScript() );
	}
	
	private class Statement
	{
		private final String COMMAND;
		private final String[] ARGS;
		
		private Statement( final String COMMAND, final String[] ARGS )
		{
			this.COMMAND = COMMAND;
			this.ARGS = ARGS;
		}
	}
}
