public class DataParameters
{
	public final RightAscension CENTER_RA;
	public final Declination CENTER_DEC;
	public final double HEIGHT;
	public final double WIDTH;
	public final double SCALE;
	
	public DataParameters( final RightAscension CENTER_RA,
						   final Declination CENTER_DEC,
						   final double HEIGHT, final double WIDTH,
						   final double SCALE )
	{
		this.CENTER_RA  = CENTER_RA;
		this.CENTER_DEC = CENTER_DEC;
		this.HEIGHT     = HEIGHT;
		this.WIDTH      = WIDTH;
		this.SCALE      = SCALE;
	}
}
