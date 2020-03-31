package jp.risu.CRGK.util.color;

import java.awt.Color;

/**
 * Class {@code PureColour} represents any possible combinations of color from RGB regardless values black and white.
 * The class supports more detailed possibilities than {@code Colour.getColorFromDegree()}.
 * 
 * <p>Date created: 2020/03/29
 * @author Risusan
 */
public class PureColour {
	public static final int RED = 0xFF0000;
	public static final int GREEN = 0xFF00;
	public static final int BLUE = 0xFF;
	
	private final Color pure;
	
	/**
	 * Converts mixed RGB value into pure RGB, which contains at least one 255, and 0 value. 
	 * @param par1int
	 * @param par2int
	 * @param par3int
	 */
	public PureColour(int par1int, int par2int, int par3int) {
		Color c = new Color(par1int, par2int, par3int);
		if (!isPureColour(c)) {
			byte[] smb = sort(par1int, par2int, par3int);
			int[] rgb = {par1int, par2int, par3int};
			rgb[smb[1]] = (int)(rgb[smb[2]] - (float)(rgb[smb[2]] - rgb[smb[1]]) / (float)(1 - (float)rgb[smb[0]] / (float)rgb[smb[2]]));
			rgb[smb[1]] = (int)((float)rgb[smb[1]] / ((float)rgb[smb[2]] / 255.f));
			rgb[smb[0]] = 0; rgb[smb[2]] = 255;
			this.pure = new Color(rgb[0], rgb[1], rgb[2]);
		} else
			this.pure = c;
	}
	
	
	public static PureColour getPureColour(int par1int, int par2int, int par3int) {
		return new Colour(par1int, par2int, par3int);
	}
	
	
	/**
	 * Returns color in terms of 0 - 359 degree system.
	 * There are 1530 possible combinations of color regardless white and black values.
	 * This method will provide 360 kinds out of it which is just enough to start seeing 
	 * difference to the human brain. In this application, this system will be called Color wheel degree system.
	 * @param par1int - degree
	 * @return
	 * @throws IllegalArgumentException
	 * <p>Thrown when range out of 0 - 359 is given.
	 */
	public static PureColour getColorFromDegrees(int par1int) {
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
		return new PureColour(R, G, B);
	}
	
	/**
	 * Returns this colour as {@code Color} object.
	 * @return
	 */
	public Color asColor() {
		return pure;
	}
	
	protected static boolean isPureColour(Color c) {
		if (c.getRed() == 255 || c.getGreen() == 255 || c.getBlue() == 255)
			if (c.getRed() == 0 || c.getGreen() == 0 || c.getBlue() == 0)
				return true;
		return false;
	}
	
	/**
	 * Sorts ints smallest to biggest
	 * @param par1int - src1
	 * @param par2int - src2
	 * @param par3int - src3
	 */
	private static byte[] sort(int par1int, int par2int, int par3int) {
		byte[] rgb = new byte[3];
		if (par1int <= par2int && par1int <= par3int) {
			rgb[0] = 0;
			if (par2int <= par3int) {
				rgb[1] = 1;
				rgb[2] = 2;
			} else {
				rgb[1] = 2;
				rgb[2] = 1;
			}
		}
		else if (par2int <= par1int && par2int <= par3int) {
			rgb[0] = 1;
			if (par1int <= par3int) {
				rgb[1] = 0;
				rgb[2] = 2;
			} else {
				rgb[1] = 2;
				rgb[2] = 0;
			}
		}else if (par3int <= par1int && par3int <= par2int) {
			rgb[0] = 2;
			if (par2int <= par1int) {
				rgb[1] = 1;
				rgb[2] = 0;
			} else {
				rgb[1] = 0;
				rgb[2] = 1;
			}
		}
		return rgb;
	}
}
