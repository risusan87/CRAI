package jp.risu.CRGK.util.color;

import java.awt.Color;

/**
 * Class {@code Colour} supports further flexible functions of image processing.
 * Color data given into this class will be broke into Reb, Green, Blue, White, and Black values
 * and will provide function to process, assess, and convert into {@code Color} objects.
 * <p>Unlike class {@code Color}, this class also supports {@code Raster} or array of pixels that
 * is encupsels of colors as databuffer, byte, or int.
 * <p>Date created: 2020/03/24
 * @author Risusan
 */
public class Colour {
	public final int Red;
	public final int Green;
	public final int Blue;
	public final float White;
	public final float Black;
	
	private static final int areawb = getWBArea();
	
	/**
	 * Creates {@code Colour} object from r,g,b values.
	 * @param par1int
	 * @param par2int
	 * @param par3int
	 */
	public Colour(int par1int, int par2int, int par3int) {
		PureColour pc = new PureColour(par1int, par2int, par3int);
		this.Red = pc.asColor().getRed();
		this.Green = pc.asColor().getGreen();
		this.Blue = pc.asColor().getBlue();
		this.White = pc.getWhite();
		this.Black = pc.getBlack();
	}
	
	public double getMatchedValue(Colour par1colour) {
		PureColour dst = par1colour.asPureColour();
		double match_p = this.asPureColour().getMatchedValue(dst);
		double match_w = 0.0;
		double match_b = 0.0;
		double rt;
		if (!PureColour.isPureColour(this.asColor())) {
			int sw = (int)Math.round(this.White * 10000.0);
			int sb = (int)Math.round(this.Black * 10000.0);
			int dw = (int)Math.round(par1colour.White * 10000.0);
			int db = (int)Math.round(par1colour.Black * 10000.0);
			int wdis = Math.abs(sw - dw);
			int bdis = Math.abs(sb - db);
			double warea = 0.0;
			for (int i = sw > dw ? dw : sw; i < wdis; i++)
				warea += Math.cos((9.0 * (double)i) / 1000.0);
			match_w = 1 - warea / areawb;
			double barea = 0.0;
			for (int i = sb > db ? db : sb; i < bdis; i++)
				barea += Math.sin((9.0 * (double)i) / 1000.0);
			match_b = 1 - barea / areawb;
			rt = match_p + match_w + match_b;
			rt = rt >= 1.0 ? 1.0 : rt;
			rt = rt <= 0.0 ? 0.0 : rt;
		} else
			rt = match_p;
		return rt;
	}
	
	public PureColour asPureColour() {
		return new PureColour(this.Red, this.Green, this.Blue);
	}
	
	public Color asColor() {
		return new Color(this.Red, this.Green, this.Blue);
	}
	
	/**
	 * Combines each rgb values in one integer. 
	 * @param par1int - R
	 * @param par2int - G
	 * @param par3int - B
	 * @return rgb in integer
	 */
	public static int convertRGBToInt(int par1int, int par2int, int par3int) {
		Color c = new Color(par1int, par2int, par3int);
		return c.getRGB();
	}
	
	/**
	 * Converts from int into byte array of Red, Green, and Blue values.
	 * returns 0, 0, 0 (black) if out of expected range.
	 * @param par1int - integer between 0x000000 to 0xFFFFFF
	 * @return byte array in order of RGB.
	 */
	public static byte[] convertIntToRGB(int par1int) {
		Color c = new Color(par1int);
		byte[] rgb = new byte[3];
		rgb[0] = (byte)c.getRed();
		rgb[1] = (byte)c.getGreen();
		rgb[2] = (byte)c.getBlue();
		return rgb;
	}
	
	private static final int getWBArea() {
		int rt = 0;
		for (int i = 0; i < 10000; i++)
			rt += Math.cos((9.0 * (double)i) / 1000.0);
		return rt;
	}
}
