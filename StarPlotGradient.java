import java.awt.Graphics2D;
import java.io.File;
import java.util.Scanner;

public class StarPlotGradient extends JStarPlot
{
	public StarPlotGradient()
	{
		super();
	}
	
	@Override
	public void drawStar( Star star, AzimuthalEquidistantProjection p )
	{
		Graphics2D g2 = im.createGraphics();
		boolean originAtCenter = false;
		double starArea = Math.pow( MAG_UNIT, -1 * star.MAG ) * Math.PI;
		double starRadius = RADIUS * Math.sqrt( starArea / Math.PI );
		double[] coordinates = p.getXY( star.RA, star.DEC );

		coordinates[Y] *= -1;
		if ( !originAtCenter )
		{
			coordinates[X] += 1;
			coordinates[Y] += 1;
		}
		coordinates[X] *= SCALE;
		coordinates[Y] *= SCALE;

		coordinates[X] -= SCALE - WIDTH/2;
		coordinates[Y] -= SCALE - HEIGHT/2;
		
		if ( 0 > coordinates[X] || coordinates[X] > WIDTH ||
			 0 > coordinates[Y] || coordinates[Y] > HEIGHT )
			return;
		Star2D dStar = new Star2D( coordinates[X], coordinates[Y], 
		                           starRadius, star.SPECTRAL_TYPE );
		dStar.drawGradient( g2 );
		g2.dispose();
	}

	public static void main( String... args ) throws Throwable
	{
		StarPlotGradient im = new StarPlotGradient();
		im.openNewDisplayWindow();

		AzimuthalEquidistantProjection p;
		Scanner in = new Scanner( new File( "hipparcos-tycho2.pp4" ) );
		p = AzimuthalEquidistantProjection.TAURUS;
		im.drawMilkyWay( p );
		im.repaintCurrentDisplayWindow();
		im.drawHydrogenAlpha( p );
		im.repaintCurrentDisplayWindow();
		while ( in.hasNext() )
		{
			im.drawStar( processStar( in ), p );
			im.repaintCurrentDisplayWindow();
		}
		System.out.println( "done" );
	}
}
