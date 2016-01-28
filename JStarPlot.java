import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JStarPlot
{
	public static final double RADIUS = 50;
	public static final double MAG_UNIT = Math.pow( 100.0, 0.2 );
	public static final int X = 0;
	public static final int Y = 1;
	protected final boolean ORIGIN_AT_CENTER = false;
	
	protected BufferedImage im;
	protected BufferedImage mw;
	protected BufferedImage ha;
	protected final int WIDTH = 500;
	protected final int HEIGHT = 500;
	protected final double SCALE = 750;
	protected ImagePanel theDisplay;

	private class ImagePanel extends JPanel
	{
		private static final long serialVersionUID = 1L;
		JStarPlot im;
		JFrame frame;

		public ImagePanel( JStarPlot im )
		{
			this.im = im; // Reference to the image - not a copy
							// So if you call repaint() on this class it will
							// redraw the updated pixels

			this.setSize( im.getWidth(), im.getHeight() );

			// Setup the frame that I belong in
			frame = new JFrame( "Image Viewer" );
			frame.addWindowListener( new WindowAdapter()
			{
				public void windowClosing( WindowEvent e )
				{
					System.exit( 1 );
				}
			} );
			frame.getContentPane().add( this );

			// Set a fake size for now to get it up - we will never see this
			// show up in these dimensions
			frame.setSize( im.getWidth() + 10, im.getHeight() + 10 );
			frame.setVisible( true );

			// Get the insets now that it is visible
			// The insets will be 0 until it is visible
			Insets fInsets = frame.getInsets();

			// Resize the image based on the visible insets
			frame.setSize( im.getWidth() + fInsets.left + fInsets.right,
					im.getHeight() + fInsets.top + fInsets.bottom );
		}

		// Resets the image being displayed and redoes the frame to fit it
		public void changeImage( JStarPlot im )
		{
			this.im = im;

			Insets fInsets = frame.getInsets();

			// Resize the image based on the visible insets
			frame.setSize( im.getWidth() + fInsets.left + fInsets.right,
					im.getHeight() + fInsets.top + fInsets.bottom );

			this.repaint();
		}

		public void paintComponent( Graphics g )
		{
			super.paintComponent( g );

			im.draw( g );
		}
	}
	
	public JStarPlot()
	{
		this.im = new BufferedImage( WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB );

		// Create a graphics context for the new BufferedImage
		Graphics2D g2 = this.im.createGraphics();

		// Fill in the image with black
		g2.setColor( Color.black );
		g2.fillRect( 0, 0, WIDTH, HEIGHT );
		File fmw = new File( "/home/aaron/workspace/PP4/bin/images/GAIAMilkyWay.png" );
		File fha = new File( "/home/aaron/workspace/PP4/bin/images/HAlpha.png" );

		try
		{
			this.mw = ImageIO.read( fmw );
			this.ha = ImageIO.read( fha );

		} catch ( IOException e )
		{
			e.printStackTrace();
			System.exit( 1 );
		}
	}

	public int getWidth()
	{
		return WIDTH;
	}

	public int getHeight()
	{
		return HEIGHT;
	}
	
	public void draw( Graphics g )
	{
		g.drawImage( im, 0, 0, null );
	}

	public void openNewDisplayWindow()
	{
		this.theDisplay = new ImagePanel( this );
	}

	// To mutate the existing window to fit the image's current dimensions and
	// pixels
	public void repaintCurrentDisplayWindow()
	{
		this.theDisplay.changeImage( this );
		this.theDisplay.repaint();
	}

	public static Star processStar( Scanner in )
	{
		in.next();
		String strRA = in.next();
		RightAscension ra = new RightAscension( strRA );
		Declination dec = new Declination( in.next() );
		String strMag = in.next();
		String type = in.next();
		type = type.substring( 0, 1 );
		double mag = Double.parseDouble( strMag );
		Star star = new Star( ra, dec, mag, type );
		return star;
	}

	public void drawMilkyWay( AzimuthalEquidistantProjection p )
	{
	    double latShift = mw.getHeight()/-2.0;
	    double latScale = mw.getHeight()/180.0;
	    double longShift = mw.getWidth()/2.0;
	    double longScale = mw.getWidth()/360.0;

	    for ( double i = 0; i < mw.getWidth(); i+=0.3 )
	        for ( double j = 0; j < mw.getHeight(); j+=0.3 )
	        {
	            double currLat = (j+latShift)/latScale;
	            double currLong = (i+longShift)%mw.getWidth();
	            currLong /= longScale;
	            currLong %= 360;
	            int c = mw.getRGB( (int)i, (int)j );

	            double[] coordinates = gal2fk5( -currLat, -currLong );
	            RightAscension RA = new RightAscension( coordinates[0] );
	            Declination DEC = new Declination( coordinates[1] );
	            coordinates = p.getXY( RA, DEC );
	            coordinates[Y] *= -1;
	    		if ( !ORIGIN_AT_CENTER )
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
    				continue;
	    		
	            im.setRGB( (int)coordinates[0], (int)coordinates[1], c );
	            if ( theDisplay != null )
	            	repaintCurrentDisplayWindow();
	        }
	}
	
	public void drawHydrogenAlpha( AzimuthalEquidistantProjection p )
	{
	    double latShift = ha.getHeight()/-2.0;
	    double latScale = ha.getHeight()/180.0;
	    double longShift = ha.getWidth()/2.0;
	    double longScale = ha.getWidth()/360.0;
		Graphics2D g2 = im.createGraphics();

	    for ( double i = 0; i < ha.getWidth(); i+=0.5 )
	        for ( double j = 0; j < ha.getHeight(); j+=0.5 )
	        {
	            double currLat = (j+latShift)/latScale;
	            double currLong = (i+longShift)%ha.getWidth();
	            currLong /= longScale;
	            currLong %= 360;
	            double[] coordinates = gal2fk5( -currLat, -currLong );
	            RightAscension RA = new RightAscension( coordinates[X] );
	            Declination DEC = new Declination( coordinates[Y] );
	            coordinates = p.getXY( RA, DEC );
	            coordinates[Y] *= -1;
	    		if ( !ORIGIN_AT_CENTER )
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
    				continue;
	            int c = ha.getRGB( (int)i, (int)j );
	            c &= 0x000000FF;
	            double alpha = c/255.0;
	            alpha *= alpha;
	            alpha /= 5.0;
	    	    
	            c = makeARGB( alpha, 0xFF0000 );
	            g2.setPaint( new Color(c,true) );
	    		g2.fill( new Rectangle2D.Double( coordinates[0], coordinates[1], 1, 1) );
	    		if ( theDisplay != null )
	            	repaintCurrentDisplayWindow();
	        }
		g2.dispose();
	}

	public static double[] gal2fk5( double dphi, double dtheta )
	{
		double[] pos = new double[3];
		double[] pos1 = new double[3];
		double r,dl,db,rl,rb,rra,rdec,dra,ddec;
		int i;
		double[][] jgal = {{-0.054875539726,-0.873437108010,-0.483834985808},
						   { 0.494109453312,-0.444829589425, 0.746982251810},
						   {-0.867666135858,-0.198076386122, 0.455983795705}};

		/*  Spherical to Cartesian */
		dl = dtheta;
		db = dphi;
		rl = dl * Math.PI / 180;
		rb = db * Math.PI / 180;
		r = 1.0;
		pos[0] = r * Math.cos(rl) * Math.cos(rb);
		pos[1] = r * Math.sin(rl) * Math.cos(rb);
		pos[2] = r * Math.sin(rb);

		/*  Rotate to equatorial coordinates */
		for (i = 0; i < 3; i++) {
			pos1[i] = pos[0]*jgal[0][i] + pos[1]*jgal[1][i] + pos[2]*jgal[2][i];
			}

		/*  Cartesian to Spherical */
		double x,y,z,rxy,rxy2,z2;

		x = pos1[0];
		y = pos1[1];
		z = pos1[2];

		rra = Math.atan2 (y, x);
		if (rra < 0.)
			rra += 2.0*Math.PI;

		rxy2 = x*x + y*y;
		rxy = Math.sqrt(rxy2);
		rdec = Math.atan( z / rxy );

		z2 = z * z;
		r = Math.sqrt(rxy2 + z2);

		dra = rra * 180.0 / Math.PI;
		ddec = rdec * 180.0 / Math.PI;
		dtheta = dra;
		dphi = ddec;
		double ra2 = ((dtheta / 15.0) + 360.0) % 360.0;

		double[] output = { ra2, ddec };
		return output;
	}

	public void drawStar( Star star, AzimuthalEquidistantProjection p )
	{
		Graphics2D g2 = im.createGraphics();
		double starArea = Math.pow( MAG_UNIT, -1 * star.MAG ) * Math.PI;
		double starRadius = RADIUS * Math.sqrt( starArea / Math.PI );
		double[] coordinates = p.getXY( star.RA, star.DEC );

		coordinates[Y] *= -1;
		if ( !ORIGIN_AT_CENTER )
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
		dStar.draw( g2 );
		g2.dispose();
	}
	
	public int makeARGB( double a, int rgb )
	{
		int color = (int)(255*a);
		color = color << 24;
		color += rgb;
		return color;
	}
	
	public static void main( String... args ) throws Throwable
	{
		JStarPlot im = new JStarPlot();
		im.openNewDisplayWindow();

		AzimuthalEquidistantProjection p;
		Scanner in = new Scanner( new File( "/home/aaron/workspace/PP4/catalogs/hipparcos-tycho2.pp4" ) );
		p = AzimuthalEquidistantProjection.ORION;
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
