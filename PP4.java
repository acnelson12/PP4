import java.util.Scanner;

public class PP4
{
	public static void main( String[] args )
	{
		/* Local Variables */
		String filename;
		Scanner in = new Scanner( System.in );
		DataParameters dataParams;
		PP4Processor processor;
		
		if ( args.length > 0 )
			filename = args[0];
		else
		{
			System.out.println( "Enter Filename: " );
			filename = in.nextLine();
		}
		
		processor = new PP4Processor( filename );
	}
}
