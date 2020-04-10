package jp.risu.CRGK.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import jp.risu.CRGK.CoreCRGK;

/**
 * Class {@code FileIOUtils} gives access to any out or inside resources for the program.
 * Resources loaded by this class will be returned as input stream, keeping eyes on memory leaking and releasing if nessessary.
 * <p>Date created: 2020/03/24
 * @author Risusan
 */
public class FileIOUtils { 
	private static boolean isJar;
	private static String defaultLib = "D:/Program Files(x86)/java library/opencv/build/java/x64";
	private static JarFile Jar = null;
	
	/**
	 * Checks if the program is running from the jar file or IDE, and establishes resource paths.
	 */
	public static final void initIO(Class<?> par1class) {
		URL url = par1class.getResource("");
		isJar = url.getProtocol().equals("jar");
		
		if (isJar) {
			String[] str;
			try {
				String path = par1class.getResource("").getPath();
				path = URLDecoder.decode(path, "UTF-8");
				
				str = path.split(":");
				path = str[str.length - 1].split("!")[0];
				
				System.out.println(path);
				if (!path.endsWith(CoreCRGK.PROJNAM + ".jar")) {
					JOptionPane.showMessageDialog
						(new JFrame(), CoreCRGK.PROJNAM + ".jar can't be found.", "Error", JOptionPane.ERROR_MESSAGE);
					System.exit(0);
				}
				defaultLib = path.replace(CoreCRGK.PROJNAM, "");
				Jar = new JarFile(new File(path));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static boolean isRunningFromJarFile() {
		return isJar;
	}
	
	/**
	 * Returns any outcoming file in resources folder as a {@code InputStream} object. Paths are
	 * specified having the origin as the root of the jar file:
	 * <p>ex: {@code resources/path/to/some/files/SomeFile.file} will access to CRGK.jar/resources/path/...
	 * @param par1str - Path to the destination
	 * @return file in as {@code InputStream}, or {@code null} if file not found.
	 */
	public static InputStream getResource(String par1str) {
		if (isRunningFromJarFile()) {
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
	
	/**
	 * Returns file as {@code InputStream} object.
	 * It searches path given in the same folder where the jar belongs, if running from jar file.
	 * Do not include first "/" sign.
	 * <p>ex: {@code path/to/the/file/file.file} will access to sys/path/to/the/file/file.file
	 * @return
	 */
	public static boolean getResourceFromHomeFolder(String par1str) {
		if (isRunningFromJarFile())
			return new File(defaultLib + "/sys/" + par1str).exists();
		else
			return new File("./src/resources/sys/" + par1str).exists();
	}
}
