package coordinates;

import static java.lang.Math.sin;
import static java.lang.Math.cos;
import static java.lang.Math.acos;
import static java.lang.Math.asin;
import static java.lang.Math.atan;
import static java.lang.Math.sqrt;

public class AzimuthalEquidistantProjection extends CelestialMapProjection
{
	public static final AzimuthalEquidistantProjection ANDROMEDA =
			new AzimuthalEquidistantProjection( new RightAscension( "0h46m00.0s" ),
					                            new Declination( "37°00\'00.0\"" ) );
	public static final AzimuthalEquidistantProjection ANTLIA =
			new AzimuthalEquidistantProjection( new RightAscension( "10h00m00s" ),
					                            new Declination( "-30°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection APUS =
			new AzimuthalEquidistantProjection( new RightAscension( "16h00m00s" ),
					                            new Declination( "-75°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection AQUARIUS =
			new AzimuthalEquidistantProjection( new RightAscension( "23h00m00s" ),
					                            new Declination( "-15°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection AQUILA =
			new AzimuthalEquidistantProjection( new RightAscension( "20h00m00s" ),
					                            new Declination( "+5°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection ARA =
			new AzimuthalEquidistantProjection( new RightAscension( "17h23m24s" ),
					                            new Declination( "-53°34\'48\"" ) );
	public static final AzimuthalEquidistantProjection ARIES =
			new AzimuthalEquidistantProjection( new RightAscension( "3h00m00s" ),
					                            new Declination( "+20°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection AURIGA =
			new AzimuthalEquidistantProjection( new RightAscension( "6h00m00s" ),
					                            new Declination( "+40°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection BOOTES =
			new AzimuthalEquidistantProjection( new RightAscension( "15h00m00s" ),
					                            new Declination( "+30°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection CAELUM =
			new AzimuthalEquidistantProjection( new RightAscension( "5h00m00s" ),
					                            new Declination( "-40°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection CAMELOPARDALIS =
			new AzimuthalEquidistantProjection( new RightAscension( "6h00m00s" ),
					                            new Declination( "+70°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection CANCER =
			new AzimuthalEquidistantProjection( new RightAscension( "9h00m00s" ),
					                            new Declination( "+20°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection CANES_VENATICI =
			new AzimuthalEquidistantProjection( new RightAscension( "13h00m00s" ),
					                            new Declination( "+40°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection CANIS_MAJOR =
			new AzimuthalEquidistantProjection( new RightAscension( "7h00m00s" ),
					                            new Declination( "-20°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection CANIS_MINOR =
			new AzimuthalEquidistantProjection( new RightAscension( "8h00m00s" ),
					                            new Declination( "+5°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection CAPRICORNUS =
			new AzimuthalEquidistantProjection( new RightAscension( "21h00m00s" ),
					                            new Declination( "-20°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection CARINA =
			new AzimuthalEquidistantProjection( new RightAscension( "9h00m00s" ),
					                            new Declination( "-60°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection CASSIOPEIA =
			new AzimuthalEquidistantProjection( new RightAscension( "1h00m00s" ),
					                            new Declination( "+60°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection CENTAURUS =
			new AzimuthalEquidistantProjection( new RightAscension( "13h00m00s" ),
					                            new Declination( "-50°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection CEPHEUS =
			new AzimuthalEquidistantProjection( new RightAscension( "22h00m00s" ),
					                            new Declination( "+70°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection CETUS =
			new AzimuthalEquidistantProjection( new RightAscension( "1h25m12s" ),
					                            new Declination( "-11°21\'00\"" ) );
	public static final AzimuthalEquidistantProjection CHAMAELEON =
			new AzimuthalEquidistantProjection( new RightAscension( "11h00m00s" ),
					                            new Declination( "-80°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection CIRCINUS =
			new AzimuthalEquidistantProjection( new RightAscension( "15h00m00s" ),
					                            new Declination( "-60°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection COLUMBA =
			new AzimuthalEquidistantProjection( new RightAscension( "6h00m00s" ),
					                            new Declination( "-35°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection COMA_BERENICES =
			new AzimuthalEquidistantProjection( new RightAscension( "12h45m36s" ),
					                            new Declination( "+21°49\'48\"" ) );
	public static final AzimuthalEquidistantProjection CORONA_AUSTRALIS =
			new AzimuthalEquidistantProjection( new RightAscension( "19h00m00s" ),
					                            new Declination( "-40°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection CORONA_BOREALIS =
			new AzimuthalEquidistantProjection( new RightAscension( "16h00m00s" ),
					                            new Declination( "+30°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection CORVUS =
			new AzimuthalEquidistantProjection( new RightAscension( "12h00m00s" ),
					                            new Declination( "-20°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection CRATER =
			new AzimuthalEquidistantProjection( new RightAscension( "11h00m00s" ),
					                            new Declination( "-16°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection CRUX =
			new AzimuthalEquidistantProjection( new RightAscension( "12h30m00s" ),
					                            new Declination( "-60°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection CYGNUS =
			new AzimuthalEquidistantProjection( new RightAscension( "20h37m12s" ),
					                            new Declination( "+42°01\'48\"" ) );
	public static final AzimuthalEquidistantProjection DELPHINUS =
			new AzimuthalEquidistantProjection( new RightAscension( "20h42m00s" ),
					                            new Declination( "+13°48\'00\"" ) );
	public static final AzimuthalEquidistantProjection DORADO =
			new AzimuthalEquidistantProjection( new RightAscension( "5h00m00s" ),
					                            new Declination( "-65°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection DRACO =
			new AzimuthalEquidistantProjection( new RightAscension( "17h00m00s" ),
					                            new Declination( "+65°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection EQUULEUS =
			new AzimuthalEquidistantProjection( new RightAscension( "21h00m00s" ),
					                            new Declination( "+10°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection ERIDANUS =
			new AzimuthalEquidistantProjection( new RightAscension( "3h15m00s" ),
					                            new Declination( "-29°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection FORNAX =
			new AzimuthalEquidistantProjection( new RightAscension( "3h00m00s" ),
					                            new Declination( "-30°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection GEMINI =
			new AzimuthalEquidistantProjection( new RightAscension( "7h00m00s" ),
					                            new Declination( "+20°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection GRUS =
			new AzimuthalEquidistantProjection( new RightAscension( "22h00m00s" ),
					                            new Declination( "-47°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection HERCULES =
			new AzimuthalEquidistantProjection( new RightAscension( "17h00m00s" ),
					                            new Declination( "+30°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection HOROLOGIUM =
			new AzimuthalEquidistantProjection( new RightAscension( "3h00m00s" ),
					                            new Declination( "-60°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection HYDRA =
			new AzimuthalEquidistantProjection( new RightAscension( "10h00m00s" ),
					                            new Declination( "-20°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection HYDRUS =
			new AzimuthalEquidistantProjection( new RightAscension( "2h00m00s" ),
					                            new Declination( "-70°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection INDUS =
			new AzimuthalEquidistantProjection( new RightAscension( "21h00m00s" ),
					                            new Declination( "-55°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection LACERTA =
			new AzimuthalEquidistantProjection( new RightAscension( "22h30m00" ),
					                            new Declination( "+45°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection LEO =
			new AzimuthalEquidistantProjection( new RightAscension( "11h00m00s" ),
					                            new Declination( "+15°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection LEO_MINOR =
			new AzimuthalEquidistantProjection( new RightAscension( "10h00m00s" ),
					                            new Declination( "+35°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection LEPUS =
			new AzimuthalEquidistantProjection( new RightAscension( "6h00m00s" ),
					                            new Declination( "-20°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection LIBRA =
			new AzimuthalEquidistantProjection( new RightAscension( "15h00m00s" ),
					                            new Declination( "-15°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection LUPUS =
			new AzimuthalEquidistantProjection( new RightAscension( "15h18m00s" ),
					                            new Declination( "-45°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection LYNX =
			new AzimuthalEquidistantProjection( new RightAscension( "8h00m00s" ),
					                            new Declination( "+45°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection LYRA =
			new AzimuthalEquidistantProjection( new RightAscension( "19h00m00s" ),
					                            new Declination( "+40°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection MENSA =
			new AzimuthalEquidistantProjection( new RightAscension( "5h00m00s" ),
					                            new Declination( "-80°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection MICROSCOPIUM =
			new AzimuthalEquidistantProjection( new RightAscension( "21h00m00s" ),
					                            new Declination( "-36°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection MONOCEROS =
			new AzimuthalEquidistantProjection( new RightAscension( "7h09m00s" ),
					                            new Declination( "-5°44\'24\"" ) );
	public static final AzimuthalEquidistantProjection MUSCA =
			new AzimuthalEquidistantProjection( new RightAscension( "12h27m36s" ),
					                            new Declination( "-70°20\'24\"" ) );
	public static final AzimuthalEquidistantProjection NORMA =
			new AzimuthalEquidistantProjection( new RightAscension( "16h03m00s" ),
					                            new Declination( "-52°00\'36\"" ) );
	public static final AzimuthalEquidistantProjection OCTANS =
			new AzimuthalEquidistantProjection( new RightAscension( "22h00m00s" ),
					                            new Declination( "-90°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection OPHIUCHUS =
			new AzimuthalEquidistantProjection( new RightAscension( "17h00m00s" ),
					                            new Declination( "+00°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection ORION =
			new AzimuthalEquidistantProjection( new RightAscension( "5h30m00s" ),
					                            new Declination( "+00°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection PAVO =
			new AzimuthalEquidistantProjection( new RightAscension( "20h00m00s" ),
					                            new Declination( "-65°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection PEGASUS =
			new AzimuthalEquidistantProjection( new RightAscension( "23h00m00s" ),
					                            new Declination( "+20°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection PERSEUS =
			new AzimuthalEquidistantProjection( new RightAscension( "03h00m00s" ),
					                            new Declination( "+45°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection PHOENIX =
			new AzimuthalEquidistantProjection( new RightAscension( "00h00m00s" ),
					                            new Declination( "-50°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection PICTOR =
			new AzimuthalEquidistantProjection( new RightAscension( "5h00m00s" ),
					                            new Declination( "-50°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection PISCES =
			new AzimuthalEquidistantProjection( new RightAscension( "01h00m00s" ),
					                            new Declination( "+15°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection PISCIS_AUSTRINUS =
			new AzimuthalEquidistantProjection( new RightAscension( "22h00m00s" ),
					                            new Declination( "-30°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection PUPPIS =
			new AzimuthalEquidistantProjection( new RightAscension( "07h30m00s" ),
					                            new Declination( "-30°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection PYXIS =
			new AzimuthalEquidistantProjection( new RightAscension( "09h00m00s" ),
					                            new Declination( "-30°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection RETICULUM =
			new AzimuthalEquidistantProjection( new RightAscension( "04h00m00s" ),
					                            new Declination( "-60°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection SAGITTA =
			new AzimuthalEquidistantProjection( new RightAscension( "19h50m00s" ),
					                            new Declination( "+18°40\'00\"" ) );
	public static final AzimuthalEquidistantProjection SAGITTARIUS =
			new AzimuthalEquidistantProjection( new RightAscension( "19h00m00s" ),
					                            new Declination( "-25°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection SCORPIUS =
			new AzimuthalEquidistantProjection( new RightAscension( "16h53m15s" ),
					                            new Declination( "-30°44\'12\"" ) );
	public static final AzimuthalEquidistantProjection SCULPTOR =
			new AzimuthalEquidistantProjection( new RightAscension( "00h00m00s" ),
					                            new Declination( "-30°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection SCUTUM =
			new AzimuthalEquidistantProjection( new RightAscension( "18h42m00s" ),
					                            new Declination( "-10°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection SERPENS =
			new AzimuthalEquidistantProjection( new RightAscension( "17h00m00s" ),
					                            new Declination( "+00°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection SEXTANS =
			new AzimuthalEquidistantProjection( new RightAscension( "10h00m00s" ),
					                            new Declination( "+00°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection TAURUS =
			new AzimuthalEquidistantProjection( new RightAscension( "04h00m00s" ),
					                            new Declination( "+15°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection TELESCOPIUM =
			new AzimuthalEquidistantProjection( new RightAscension( "19h00m00s" ),
					                            new Declination( "-50°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection TRIANGULUM =
			new AzimuthalEquidistantProjection( new RightAscension( "02h00m00s" ),
					                            new Declination( "+30°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection TRIANGULUM_AUSTRALE =
			new AzimuthalEquidistantProjection( new RightAscension( "16h00m00s" ),
					                            new Declination( "-65°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection TUCANA =
			new AzimuthalEquidistantProjection( new RightAscension( "00h00m00s" ),
					                            new Declination( "-65°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection URSA_MAJOR =
			new AzimuthalEquidistantProjection( new RightAscension( "10h40m12s" ),
					                            new Declination( "+55°22\'48\"" ) );
	public static final AzimuthalEquidistantProjection URSA_MINOR =
			new AzimuthalEquidistantProjection( new RightAscension( "15h00m00s" ),
					                            new Declination( "+75°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection VELA =
			new AzimuthalEquidistantProjection( new RightAscension( "09h00m00s" ),
					                            new Declination( "-50°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection VIRGO =
			new AzimuthalEquidistantProjection( new RightAscension( "13h00m00s" ),
					                            new Declination( "+00°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection VOLANS =
			new AzimuthalEquidistantProjection( new RightAscension( "08h00m00s" ),
					                            new Declination( "-70°00\'00\"" ) );
	public static final AzimuthalEquidistantProjection VULPECULA =
			new AzimuthalEquidistantProjection( new RightAscension( "20h00m00s" ),
					                            new Declination( "+25°00\'00\"" ) );
	/*== Constructors ==*/
    public AzimuthalEquidistantProjection( final RightAscension CENTER_RA,
                                           final Declination CENTER_DEC )
    {
        super( CENTER_RA, CENTER_DEC );
    }
    
    /*== Accessors ==*/
    public double[] getXY( final RightAscension RA, final Declination DEC )
    {
        /* Local Constants */
        final double α = RA.toRadians();
        final double δ = DEC.toRadians();
        final double K;
        final double COS_C;
        final double C;
        
        /* Local Variables */
        double[] xy = new double[2];
        
        /* Calculate x and y Coordinates */
        COS_C = sin(δ0)*sin(δ) + cos(δ0)*cos(δ)*cos(α - α0);
        C = acos(COS_C);
        K = C / sin(C);
        xy[0] = -1 * K * cos(δ)*sin(α - α0);
        xy[1] = K * ( cos(δ0)*sin(δ) - sin(δ0)*cos(δ)*cos(α - α0) );
        
        if ( xy[0] != xy[0] )
			xy[0] = 0;
		if ( xy[1] != xy[1] )
			xy[1] = 0;
        return xy;
        
        /*
         * See here:
         * http://mathworld.wolfram.com/AzimuthalEquidistantProjection.html
         */
    }
}
