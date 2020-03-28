package jp.risu.CRGK.util;

import java.awt.Color;

/**
 * Class {@code Colour} supports further flexible functions for image processing.
 * Date created: 2020/03/24
 * @author Risusan
 */

@SuppressWarnings("serial")
public class Colour extends Color {
	/**
	 * Creates while color.
	 */
	public Colour() {
		super(255, 255, 255);
	}
	
	public Colour(int par1int, int par2int, int par3int) {
		super(par1int, par2int, par3int);
	}
	
	/**
	 * Cnbines each rgb values in one integer. 
	 * @param par1int - R
	 * @param par2int - G
	 * @param par3int - B
	 * @return rgb in integer
	 */
	public static int convertIntRGB(int par1int, int par2int, int par3int) {
		return new Color(par1int, par2int, par3int).getRGB();
	}
	
	/**
	 * Returns
	 * @param par1int - degree
	 * @return
	 * @throws IllegalArgumentException
	 * <p>Thrown when range out of 0 - 359 is given.
	 */
	public static Color getColorFromDegrees(int par1int) {
		if (!(0 <= par1int && par1int < 360))
			throw new IllegalArgumentException();
		int R = (par1int >= 300 || par1int <= 60) ? 
				255 : (60 <= par1int && par1int <= 120) ?
						(int)(255 - 4.25 * (par1int - 60)) : (240 <= par1int && par1int <= 300) ?
								(int)(4.25 * (par1int - 240)) : 0;
		int B = (180 <= par1int && par1int <= 300) ? 
				255 : (120 <= par1int && par1int <= 180) ?
						(int)(4.25 * (par1int - 120)) : (300 <= par1int && par1int <= 360) ?
								(int)(255 - 4.25 * (par1int - 300)) : 0;
		int G = (par1int >= 60 && par1int <= 180) ? 
				255 : (0 <= par1int && par1int <= 60) ?
						(int)(4.25 * (par1int)) : (180 <= par1int && par1int <= 240) ?
								(int)(255 - 4.25 * (par1int - 180)) : 0;
		return new Colour(R, G, B);
	}

}
