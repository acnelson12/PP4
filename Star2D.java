import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Star2D
{
	private final double X;
	private final double Y;
	private final double RAD;
	private final int    COLOR;
	
	public Star2D( final double X, 
	               final double Y,
	               final double RAD,
	               final String TYPE )
	{
		this.X = X;
		this.Y = Y;
		this.RAD = RAD;
		switch ( TYPE )
		{
			case "O":
				COLOR = 0xa4baff;
				break;
			case "B":
				COLOR = 0xb5c6ff;
				break;
			case "A":
				COLOR = 0xdbe0ff;
				break;
			case "F":
				COLOR = 0xfff7fc;
				break;
			case "G":
				COLOR = 0xffefdd;
				break;
			case "K":
				COLOR = 0xffd1ae;
				break;
			case "M":
				COLOR = 0xffc879;
				break;
			default:
				COLOR = 0xffffff;
		}
	}
	
	public void draw( Graphics2D g )
	{
		g.setPaint( new Color( COLOR ) );
		g.fill( new Ellipse2D.Double( X, Y, RAD, RAD) );
	}
	
	public void drawGradient( Graphics2D g )
	{
		BufferedImage starPic;
		if ( RAD <= 20 && COLOR == 0xFFFFFF )
			starPic = createSmallStar( COLOR );
		else
			starPic = createStar( COLOR );
		
		g.drawImage( starPic, (int)(X-RAD), (int)(Y-RAD), (int)(2*RAD), (int)(2*RAD), null );
		g.dispose();
	}
	
	private BufferedImage createStar( int c )
	{
		final int DIM = 200;
		BufferedImage star = new BufferedImage( DIM, DIM, BufferedImage.TYPE_INT_ARGB );
		Graphics2D gStar = star.createGraphics();

        // Fill in the image with transparency
        gStar.setColor(makeARGB(0,0));
        gStar.fillRect(0, 0, DIM, DIM);
		final int R = DIM / 2;
		Point2D center = new Point2D.Float(DIM/2.0f, DIM/2.0f);
	     float radius = DIM/2.0f;
	     float[] dist = {0.0f, 0.1f, 0.25f, 1.0f};
	     Color[] colors = {makeARGB( 1, 0xFFFFFF ), makeARGB( 0.75, c ), makeARGB( 0.1, c ), makeARGB(0,0) };
	     RadialGradientPaint p =
	         new RadialGradientPaint(center, radius, dist, colors);
	    gStar.setPaint( p );
		gStar.fill(new Ellipse2D.Double(0, 0, DIM, DIM));
		gStar.dispose();
		return star;
	}
	
	private BufferedImage createSmallStar( int c )
	{
		final int DIM = 200;
		BufferedImage star = new BufferedImage( DIM, DIM, BufferedImage.TYPE_INT_ARGB );
		Graphics2D gStar = star.createGraphics();

        // Fill in the image with transparency
        gStar.setColor(makeARGB(0,0));
        gStar.fillRect(0, 0, DIM, DIM);
		final int R = DIM / 2;
		Point2D center = new Point2D.Float(DIM/2.0f, DIM/2.0f);
	     float radius = DIM/2.0f;
	     float[] dist = {0.0f, 0.25f, 1.0f};
	     Color[] colors = {makeARGB( 0.5, c ), makeARGB( 0.1, c ), makeARGB(0,0) };
	     RadialGradientPaint p =
	         new RadialGradientPaint(center, radius, dist, colors);
	    gStar.setPaint( p );
		gStar.fill(new Ellipse2D.Double(0, 0, DIM, DIM));
		gStar.dispose();
		return star;
	}
	
	public Color makeARGB( double a, int rgb )
	{
		int color = (int)(255*a);
		color = color << 24;
		color += rgb;
		return new Color( color, true );
	}
}
