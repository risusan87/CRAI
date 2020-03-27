package jp.risu.CRGK.util;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import jp.risu.CRGK.CoreCRGK;

/**
 * <p>Date created: 2020/03/24
 * @author Risusan
 */
public class FileIOUtils { 
	private static boolean isJar;
	public static String nativeLib = "D:/Program Files(x86)/java library/opencv/build/java/x64";
	public static String prefferedLib = "";
	private static JarFile Jar = null;
	
	/**
	 * NOTE:!!THIS MUST BE CALLED AS SOON AS MAIN CLASS IS LOADED!!
	 */
	public static void initIO() {
		URL url = CoreCRGK.class.getResource("");
		isJar = url.getProtocol().equals("jar");
		if (isJar) {
			String[] str;
			try {
				String path = CoreCRGK.class.getResource("").getPath();
				path = URLDecoder.decode(path, "UTF-8");
				
				str = path.split(":");
				path = str[str.length - 1].split("!")[0];
				
				nativeLib = path.replace("ÉNÉâÉçÉèÉKÉ`ÇËåN.jar", "");
				Jar = new JarFile(new File(path));
				
				File f = new File(nativeLib + "LIB_PATH_.config");
				if (f.exists()) {
					BufferedReader br = new BufferedReader(new FileReader(f));
					String lib = br.readLine(); br.close();
					String form = "";
					for (byte b : lib.getBytes()) {
						lib = lib.substring(1);
						if ((char)b == '=')
							break;
						form += Character.toString((char)b);
					}
					if (form.equals("-Djava.library.path")) {
						if (lib.startsWith("\"") && lib.endsWith("\"")) {
							lib = lib.substring(1); lib = lib.substring(0, lib.length() - 1);
							System.out.println(lib);
							nativeLib = lib;
						} else {
							System.out.println("Native path not set right: \'\"\' not found");
						}
					}else {
						System.out.println("Native path not set right: set to be -Djava.library.path");
						System.out.println(form);
					}
				}
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static InputStream getResource(String par1str) {
		if (isJar) {
			ZipEntry z = Jar.getEntry(par1str);
			try {
				return Jar.getInputStream(z);
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return CoreCRGK.class.getResourceAsStream("/" + par1str);
		}
	}
	
	public static boolean isPathExist(String par1str) {
		return false;
	}
}
