import static java.lang.Math.sin;
import static java.lang.Math.cos;
import static java.lang.Math.acos;

public class AzimuthalEquidistantProjection
{
    /* Instance Variables */
    private final double α0;
    private final double δ0;
    
    /*== Constructors ==*/
    public AzimuthalEquidistantProjection( final RightAscension CENTER_RA,
                                           final Declination CENTER_DEC )
    {
        α0 = CENTER_RA.toRadians();
        δ0 = CENTER_DEC.toRadians();
    }
    
    /*== Accessors ==*/
    public double[] getXY( final RightAscension RA, final Declination DEC  )
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
        
        return xy;
        
        /*
         * See here:
         * http://mathworld.wolfram.com/AzimuthalEquidistantProjection.html
         */
    }
}
