package jp.risu.CRGK.util.color;

import java.awt.Color;
import java.math.BigDecimal;

/**
 * Class {@code PureColour} extends RGB color model into RGBSB model, which is an addition of RGB and HSB models.
 * The color now supports red, green, blue, saturation, and brightness. 
 * <p>Date created: 2020/03/29
 * @author Risusan
 */
public class PureColour {
	public static final int RED = 0;
	public static final int GREEN = 1;
	public static final int BLUE = 2;
	
	private final Color pure;
	private final float white;
	private final float black;
	
	public final int bigVal;
	public final int midVal;
	public final int smaVal;
	
	public final int bigCol;
	public final int midCol;
	public final int smaCol;
	
	private static final double area = getArea();
	private static final double ratio = getRatio();
		
	/**
	 * Converts RGB values into RGBSB model. 
	 * @param par1int - R
	 * @param par2int - G
	 * @param par3int - B
	 */
	public PureColour(int par1int, int par2int, int par3int) {
		Color c = new Color(par1int, par2int, par3int);
		//Converting into pure color
		if (!isPureColour(c)) {
			this.white = getWhiteValue(c);
			this.black = getBlackValue(c);
			byte[] smb = sort(par1int, par2int, par3int);
			int[] rgb = {par1int, par2int, par3int};
			rgb[smb[1]] = (int)(rgb[smb[2]] - (float)(rgb[smb[2]] - rgb[smb[1]]) / (float)(1 - (float)rgb[smb[0]] / (float)rgb[smb[2]]));
			rgb[smb[1]] = (int)((float)rgb[smb[1]] / ((float)rgb[smb[2]] / 255.f));
			rgb[smb[0]] = 0; rgb[smb[2]] = 255;
			this.pure = new Color(rgb[0], rgb[1], rgb[2]);
		} else {
			this.pure = c;
			this.white = 0.f;
			this.black = 0.f;
		}
		
		this.bigVal = this.getSortValue(2);
		this.midVal = this.getSortValue(1);
		this.smaVal = this.getSortValue(0);
		
		this.bigCol = this.getSortColor(2);
		this.midCol = this.getSortColor(1);
		this.smaCol = this.getSortColor(0);
		
		//finding middle value in color. might be easier to encapsulate as sigle function
		int[] rgb = {this.pure.getRed(), this.pure.getGreen(), this.pure.getBlue()};
		int mid = rgb[sort(rgb[0], rgb[1], rgb[2])[1]];
		
	}
	
	public float getWhite() {
		return this.white;
	}
	
	public float getBlack() {
		return this.black;
	}
	
	/**
	 * @return this colour as {@code Color} object.
	 */
	public Color asColor() {
		return pure;
	}
	
	/**
	 * Determines how close or far two colors are apart, interms of {@code PureColour}.
	 * @param par1purecolour color object to be compared
	 * @return 0.0 to 1.0 value in double indicating how close those clolors are.
	 */
	public double getMatchedValue(PureColour par1purecolour) {
		int dis = 0;
		int dir = -1;
		/*NOTE::d=distination, s = source, ds = dis-smallest, ss = src smallest
		  source(0 , 11, 255), dst(12, 0,  255)
		         ss  sm  sb        ds  dm  db
		*/
		int db = par1purecolour.getValByColor(this.bigCol);
		int sb = this.bigVal;
		int dm = par1purecolour.getValByColor(this.midCol);
		int sm = this.midVal;
		int ds = par1purecolour.getValByColor(this.smaCol);
		int ss = this.smaVal;
		//evaluating direction and magnitude
		if (db == sb) {
			
			dis += Math.abs(sm - dm);
			dis += ds != 0 ? ds : 0;
			
			if (sm == dm && sm != 0)
				dir = 0;
			//can be simplified
			else if (sm > dm) {
				switch (this.midCol) {
				case 0: dir = this.bigCol == 1 ? 1 : -1; break;
				case 1: dir = this.bigCol == 2 ? 1 : -1; break;
				case 2: dir = this.bigCol == 0 ? 1 : -1; break;
				}
			} else {
				
				switch (this.midCol) {
				case 0: dir = this.bigCol == 1 ? -1 : 1; break;
				case 1: dir = this.bigCol == 2 ? -1 : 1; break;
				case 2: dir = this.bigCol == 0 ? -1 : 1; break;
				}
			}
		} else if (ds == ss) {
			
			dis += Math.abs(sm - dm);
			dis += db != 255 ? 255 - db : 0;
			
			if (sm == dm && sm != 255)
				dir = 0;
			//can be simplified
			else if (sm > dm) {
				switch (this.midCol) {
				case 0: dir = this.bigCol == 1 ? 1 : -1; break;
				case 1: dir = this.bigCol == 2 ? 1 : -1; break;
				case 2: dir = this.bigCol == 0 ? 1 : -1; break;
				}
			} else {
				switch (this.midCol) {
				case 0: dir = this.bigCol == 1 ? -1 : 1; break;
				case 1: dir = this.bigCol == 2 ? -1 : 1; break;
				case 2: dir = this.bigCol == 0 ? -1 : 1; break;
				}
			}
		} else {
			if (dm != 0 && dm != 255) {
				dis += (510 - sm - dm <= sm + dm)? 510 - sm - dm : sm + dm;
				dis += db != 255 ? 255 : 0;
				dis += ds != 0 ? 255 : 0;
				
				if (510 - sm - dm <= sm + dm) {
					switch (this.midCol) {
					case 0: dir = this.bigCol == 1 ? -1 : 1; break;
					case 1: dir = this.bigCol == 2 ? -1 : 1; break;
					case 2: dir = this.bigCol == 0 ? -1 : 1; break;
					}
				} else {
					switch (this.midCol) {
					case 0: dir = this.bigCol == 1 ? 1 : -1; break;
					case 1: dir = this.bigCol == 2 ? 1 : -1; break;
					case 2: dir = this.bigCol == 0 ? 1 : -1; break;
					}
				}
			} else if (dm == 0) {
				dis += sm;
				dis += db != 255 ? 255 - db : ds;
				
				switch (this.midCol) {
				case 0: dir = this.bigCol == 1 ? 1 : -1; break;
				case 1: dir = this.bigCol == 2 ? 1 : -1; break;
				case 2: dir = this.bigCol == 0 ? 1 : -1; break;
				}
			} else {
				dis += 255 - sm;
				dis += ds != 0 ? ds + 255 : 255 - db;
				
				switch (this.midCol) {
				case 0: dir = this.bigCol == 1 ? 1 : -1; break;
				case 1: dir = this.bigCol == 2 ? 1 : -1; break;
				case 2: dir = this.bigCol == 0 ? 1 : -1; break;
				}
			}
		} //magnitude -> dis, direction -> dir
		
		if (dir == 0)
			return 1.0f;
		int mc = this.midCol;  
		int mv = sm;
		double lif = 0d;
		for (int i = 0; i < dis; i++) {
				switch (mc) {
				case RED: 
					mv += this.bigCol == BLUE ? dir : -dir;
					if (mv < 0) {
						mv = 1;
						mc = BLUE;
					} else if (mv > 255) {
						mv = 254;
						mc = BLUE;
					}
					break;
				case GREEN: 
					mv += this.bigCol == RED ? dir : -dir;
					if (mv < 0) {
						mv = 1;
						mc = RED;
					} else if (mc > 255) {
						mv = 254;
						mc = RED;
					}
					break;
				case BLUE: 
					mv += this.bigCol == GREEN ? dir : -dir;
					if (mv < 0) {
						mv = 1;
						mc = GREEN;
					} else if (mv > 255) {
						mv = 254;
						mc = GREEN;
					}
					break;
				}
				lif += (1d - Math.abs(Math.cos(Math.toRadians((6.d * mv) / 17.d))));
		} 
		double rt = 1d - lif / area;
		rt = rt >= 1.0 ? 1.0 : rt;
		rt = rt <= 0.0 ? 0.0 : rt;
		return rt;
	}
	
	/**
	 * RGB(100, 200, 10) will return 100.
	 * @return The value located at middle of 3.
	 */
	public int getSortValue(int par1int) {
		int Red = this.pure.getRed();
		int Green = this.pure.getGreen();
		int Blue = this.pure.getBlue();
		
		byte[] smb = sort(Red, Green, Blue);
		
		int rt;
		switch (smb[par1int]) {
			case 0: rt = Red; break;
			case 1: rt = Green; break;
			case 2: rt = Blue; break;
			default: rt = -1;
		}
		return rt;
	}
	
	/**
	 * RGB(100, 200, 10) will return 10.
	 * @return
	 */
	public int getSortColor(int par1int) {
		int Red = this.pure.getRed();
		int Green = this.pure.getGreen();
		int Blue = this.pure.getBlue();
		
		byte[] smb = sort(Red, Green, Blue);
		
		return smb[par1int];
	}
	
	public int getValByColor(int par1int) {
		switch (par1int) {
			case 0: return this.pure.getRed();
			case 1: return this.pure.getGreen();
			case 2: return this.pure.getBlue();
			default: return -1;
		}
	}
	
	/**
	 * Calculates how much white value contains in the color given.
	 * @return value of white 0 - 1.0
	 */
	public static float getWhiteValue(Color par1color) {
		int r = par1color.getRed();
		int g = par1color.getGreen();
		int b = par1color.getBlue();
		byte[] smb = sort(r, g, b);
		int[] rgb = {r, g, b};
		float rt = (float)rgb[smb[0]] / (float)rgb[smb[2]];
		return rt;
	}
	
	/**
	 * Calculates how much black value contains in the color given.
	 * @return value of black 0 - 1.0
	 */
	public static float getBlackValue(Color par1color) {
		int r = par1color.getRed();
		int g = par1color.getGreen();
		int b = par1color.getBlue();
		byte[] smb = sort(r, g, b);
		int[] rgb = {r, g, b};
		float rt = 1.f - (float)rgb[smb[2]] / 255f;
		return rt;
	}
	
	/**
	 * Returns {@code Color} object that all black and white value levels are removed.
	 * @param par1color - color to be processed
	 * @return processed color
	 */
	public static Color removeBWof(Color par1color) {
		PureColour pc = new PureColour(par1color.getRed(), par1color.getGreen(), par1color.getBlue());
		return pc.asColor();
	}
	
	/**
	 * Checks if color given is a part of {@code PureColor}
	 * @param c
	 * @return
	 */
	public static boolean isPureColour(Color par1color) {
		if (par1color.getRed() == 255 || par1color.getGreen() == 255 || par1color.getBlue() == 255)
			if (par1color.getRed() == 0 || par1color.getGreen() == 0 || par1color.getBlue() == 0)
				return true;
		return false;
	}
	
	/**
	 * Sorts ints smallest to biggest.
	 * In the byte array returns contains 0 as smallest to 2 as largest, will have 0, 1, 2 as rgb.
	 * @param par1int - red
	 * @param par2int - green
	 * @param par3int - blue
	 * @return byte array, specify 0,1,2 as small, mid, big, containing 0, 1, or 2 as Red, Green, or Blue.
	 * <p> For example, when byte[0] contains 2, it means the color has Blue(2), as smallest value(0).
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
	
	/**
	 * utility
	 */
	private static final double getArea() {
		//calculating area
		double cont = 0;
		for (int i = 0; i < 256; i++) 
				cont += (1d - Math.abs(Math.cos(Math.toRadians((6.d * (double)i) / 17d))));
		return cont;
	}
	
	/**
	 * utility
	 */
	private static final double getRatio() {
		return 1d / area;
	}
}
