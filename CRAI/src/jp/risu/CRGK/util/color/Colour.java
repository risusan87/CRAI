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
public class Colour extends PureColour {
	
	/**
	 * Creates {@code Colour} object from r,g,b values.
	 * @param par1int
	 * @param par2int
	 * @param par3int
	 */
	public Colour(int par1int, int par2int, int par3int) {
		super(par1int, par2int, par3int);
	}
	
	public Colour(Color par1color) {
		super(par1color.getRed(), par1color.getGreen(), par1color.getBlue());
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
	
	/**
	 * Returns best fit degree point from given color RGB value.
	 * @param par1color
	 * @return Best fit degree value from Color
	 */
	public static int getDegreesFromColor(Color par1color) {
		return 0;
	}
}
