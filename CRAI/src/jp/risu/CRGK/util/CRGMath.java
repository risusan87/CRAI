package jp.risu.CRGK.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Class {@code MathAI} implements advanced and reliable quality of calculation for AI.
 * <p>Date created: 2020/03/24
 * @author Risusan
 */
public final class CRGMath {
	private BigDecimal mainbd;
	private CRGMath(BigDecimal par1bigdecimal) {
		this.mainbd = par1bigdecimal;
	}
	
	/**
	 * Rounds value of the object to given decimal point.
	 * @param par1int decimal point
	 * @param par2rm {@code RoundingMode}. Null will be HALF_UP
	 * @see RoundingMode
	 */
	public CRGMath roundTo(int par1int, RoundingMode par2rm) {
		if (par2rm == null)
			par2rm = RoundingMode.HALF_UP;
		BigDecimal bd = this.mainbd.round(new MathContext(par1int, par2rm));
		this.mainbd = bd;
		return this;
	}
	
	public String asString() {
		return this.toString();
	}
	
	public int asInteger() {
		return Integer.parseInt(this.mainbd.toString());
	}
	
	public float asFloatValue() {
		return this.mainbd.floatValue();
	}
	
	public double asDoubleValue() {
		return this.mainbd.doubleValue();
	}
	
	@Override
	public String toString() {
		return this.mainbd.toString();
	}
	
	public static CRGMath addTogether(String par1str, String par2str) {
		BigDecimal rt = new BigDecimal(par1str).add(new BigDecimal(par2str));
		return new CRGMath(rt);
	}
	
	public static CRGMath addAll(String... par1str) {
		BigDecimal rt = new BigDecimal("0.0");
		for (String s : par1str)
			rt.add(new BigDecimal(s));
		return new CRGMath(rt);
	}
	
	public static CRGMath subtractBoth(String par1str, String par2str) {
		BigDecimal rt = new BigDecimal(par1str).subtract(new BigDecimal(par2str));
		return new CRGMath(rt);
	}
}
