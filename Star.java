public class Star
{
	public final RightAscension RA;
	public final Declination    DEC;
	public final double         MAG;
	public final String         SPECTRAL_TYPE;
	
	public Star( final RightAscension RA,
			     final Declination    DEC,
			     final double         MAG,
			     final String         SPECTRAL_TYPE )
	{
		this.RA            = RA;
		this.DEC           = DEC;
		this.MAG           = MAG;
		this.SPECTRAL_TYPE = SPECTRAL_TYPE;
	}
}
