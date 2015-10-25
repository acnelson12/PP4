public abstract class CelestialMapProjection
{
	/* Instance Variables */
    protected final double α0;
    protected final double δ0;
    
    /*== Constructors ==*/
    public CelestialMapProjection( final RightAscension CENTER_RA,
                                   final Declination    CENTER_DEC )
    {
        α0 = CENTER_RA.toRadians();
        δ0 = CENTER_DEC.toRadians();
    }
    
    public abstract double[] getXY( final RightAscension RA,
                                    final Declination    DEC );
}
