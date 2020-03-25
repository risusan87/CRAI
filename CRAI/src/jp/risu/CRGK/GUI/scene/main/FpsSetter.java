package jp.risu.CRGK.GUI.scene.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import jp.risu.CRGK.thread.ThreadProxy;

/**
 * Class {@code FpsSetter} is one of the components visible on {@code SceneMain}.
 * Purpose of the component is to allow to change FPS(PPS) via GUI.
 * <p>Date created: 2020/03/24
 * @author Risusan
 */
@SuppressWarnings("serial")
public class FpsSetter extends JComboBox<String> implements ActionListener {
	private final String choice[] = {"1 FPS", "3 FPS", "10 FPS", "15 FPS", "30 FPS", "60 FPS"};
	
	public FpsSetter() {
		super();
		for (String s : choice)
			this.addItem(s);
		this.setSelectedIndex(4);
		this.addActionListener(this);
		this.setBounds(60, 3, 70, 25);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox<?> jc = (JComboBox<?>)e.getSource();
		switch ((String)jc.getSelectedItem()) {
			case "1 FPS": ThreadProxy.REFRATE = 1f; break;
			case "3 FPS": ThreadProxy.REFRATE = 3f; break;
			case "10 FPS": ThreadProxy.REFRATE = 10f; break;
			case "15 FPS": ThreadProxy.REFRATE = 15f; break;
			case "30 FPS": ThreadProxy.REFRATE = 30f; break;
			case "60 FPS": ThreadProxy.REFRATE = 60f; break;
		}
	}

}
