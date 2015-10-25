import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class StarPlot
{
	public static void main( String... args ) throws Throwable
	{
		final RightAscension RA = new RightAscension( "17h47m20.4s" );
		final Declination DEC = new Declination( "-28°23\'07\"" );
		AzimuthalEquidistantProjection p;
		Scanner in = new Scanner( new File( "stars.pp4" ) );
		FileWriter out = new FileWriter( new File( "starplot.tex" ) );
		p = new AzimuthalEquidistantProjection( RA, DEC );
		in.next(); in.next(); in.next(); in.next();
		out.write( "\\documentclass{article}\n" +
                   "\\usepackage{tikz}\n" +
                   "\\usepackage[nohead,nofoot,margin=0cm,paperwidth=11cm,paperheight=10.45cm]{geometry}\n" +
                   "\\usetikzlibrary{backgrounds}\n" +
                   "\\begin{document}\n" +
                   "\\noindent\n" +
                   "\\begin{tikzpicture}[background rectangle/.style={fill=black},\n" +
                   "                    show background rectangle]\n" +
                   "\t\\pgfsetfillopacity{1.0}\n" +
                   "\t\\filldraw [yellow!70] " );
		while ( in.hasNext() )
		{
			out.write( processStar( in, p ) );
		}
		out.write( ";\n\\end{tikzpicture}\n\\end{document}" );
		out.close();
	}
	
	public static String processStar( Scanner in,
	                                  AzimuthalEquidistantProjection p )
	{
		in.next();
		RightAscension ra  = new RightAscension( in.next() );
		Declination    dec = new Declination(    in.next() );
		String strMag = in.next();
		strMag = strMag.substring(0, strMag.length()-1 );
		double mag = Double.parseDouble(strMag);
		double[] xy = p.getXY( ra, dec );
		if ( 20 * xy[0] < -11 || 20 * xy[1] < -7 || 20 * xy[0] > 3 || 20 * xy[1] > 4 )
			return "";
		
		double starArea = Math.pow( 2.512, -1*mag );
		double starRadius = Math.sqrt(starArea/Math.PI)/Math.sqrt(Math.pow(0.25,2)/Math.PI);
		String output = "(" + 20*xy[0] + "," + 20*xy[1] + ") circle [radius=" +
		         starRadius + "pt]\n";
		return output;
	}
}
