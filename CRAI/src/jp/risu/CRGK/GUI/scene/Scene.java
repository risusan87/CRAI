package jp.risu.CRGK.GUI.scene;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class Scene extends JPanel {
	private final String SCENE_NAME;
	private final List<Component> comps;
	private final List<String> names;
	
	/**
	 * 
	 * @param par1str - name of the scene
	 */
	public Scene(String par1str) {
		this.comps = new ArrayList<Component>();
		this.names = new ArrayList<String>();
		this.SCENE_NAME = par1str;
	}
	
	public String getSceneName() {
		return this.SCENE_NAME;
	}
	
	protected void addComponent(Component par1comp, String par2str) {
		this.comps.add(par1comp); 
		this.names.add(par2str);
	}
	
	protected Component getComponent(String par1str) {
		for (int i = 0; i < this.names.size(); i++)
			if (this.names.get(i).equals(par1str)) {
				this.comps.get(i).setVisible(false);
				this.remove(this.comps.get(i));
				return this.comps.get(i);
			}
		return null;
	}
	
	public void enableScene(boolean par1boolean) {
		for (int i = 0; i < this.comps.size(); i++) {
			this.comps.get(i).setEnabled(par1boolean);
			this.comps.get(i).setVisible(par1boolean);
		}
	}
}
