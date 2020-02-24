package jp.risu.CRGK.GUI.component;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * ComponentManager--2020/02/23
 * The class manages all the components could be shown on the frame.
 * The instance can be specified up to hierarchy of {@link java.awt.Component}, but mostly meant to contain JComponent objects.
 * Each components must have their own unique name to represent in what component they belong. 
 * Every component resistered in this class can be called from anywhere with in this project, 
 * by using getComponentBy... method either giving name, or component object.
 * @author risu87
 */
public class ComponentManager {
	private static List<Component> Components = new ArrayList<Component>();
	private static List<String>    Names      = new ArrayList<String>();
	
	/**
	 * Used to add component of the frame.
	 * @param par1c - Component to be resistered
	 * @param par2str - A unique name of the component
	 */
	public static void addComponent(Component par1c, String par2str) {
		Components.add(par1c);
		Names.add(par2str);
	}
	
	/**
	 * Returns component by name
	 * @param par1str - Name of the component
	 * @return Requested object of component, null if not available
	 */
	public static Component getComponentByname(String par1str) {
		try {
			for (int i = 0; i < Names.size(); i++)
				if (Names.get(i).equals(par1str))
					return Components.get(i);
		} catch (NullPointerException e) {
			throw new NullPointerException("Component can't be found by the name:" + par1str +
					".\nThe process will be ignored.");
		}
		return null;
	}
	
	public static Component getComponentByComponent(Component c) {
		for (int i = 0; i < Components.size(); i++)
			if (Names.get(i).equals(c))
				return Components.get(i);
		return null;
	}
	
	public static int removeComponentByName(String n) {
		for (int i = 0; i < Names.size(); i++)
			if (Names.get(i).equals(n)) {
				Components.remove(i);
				Names.remove(i);
				return 0;
			}
		return -1;
	}
	
	public static int removeComponentByComponent(String n) {
		for (int i = 0; i < Names.size(); i++)
			if (Names.get(i).equals(n)) {
				Components.remove(i);
				Names.remove(i);
				return 0;
			}
		return -1;
	}
}
