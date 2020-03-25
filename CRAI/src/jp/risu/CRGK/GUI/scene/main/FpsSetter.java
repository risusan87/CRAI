package jp.risu.CRGK.GUI.scene.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import jp.risu.CRGK.util.ThreadProxy;

/**
 * Class {@code FpsSetter} is one of the components visible on {@code SceneMain}.
 * Purpose of the component is to allow to change FPS(Frame Per Second) via GUI.
 * <p>Date created: 2020/03/24
 * @author Risusan
 */
@SuppressWarnings("serial")
public class FpsSetter extends JComboBox<String> implements ActionListener {
	private static final String choice[] = {"1 FPS", "3 FPS", "10 FPS", "15 FPS", "30 FPS", "60 FPS", "80 FPS", "120 FPS"};
	
	public FpsSetter() {
		super(choice);
		this.setSelectedIndex(4);
		this.addActionListener(this);
		this.setBounds(100, 20, 80, 20);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox<?> jc = (JComboBox<?>)e.getSource();
		switch ((String)jc.getSelectedItem()) {
			case "1 FPS": ThreadProxy.FPS = 1f; break;
			case "3 FPS": ThreadProxy.FPS = 3f; break;
			case "10 FPS": ThreadProxy.FPS = 10f; break;
			case "15 FPS": ThreadProxy.FPS = 15f; break;
			case "30 FPS": ThreadProxy.FPS = 30f; break;
			case "60 FPS": ThreadProxy.FPS = 60f; break;
			case "80 FPS": ThreadProxy.FPS = 80f; break;
			case "120 FPS": ThreadProxy.FPS = 120f; break;
		}
	}

}
