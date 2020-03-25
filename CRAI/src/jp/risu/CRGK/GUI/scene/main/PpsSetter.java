package jp.risu.CRGK.GUI.scene.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import jp.risu.CRGK.util.ThreadProxy;

/**
 * PPS(Process Per Second)
 * <p>Date created: 2020/03/25
 * @author Risusan
 */
@SuppressWarnings("serial")
public class PpsSetter extends JComboBox<String> implements ActionListener {
	private static final String[] choice = {"5 PPS", "10 PPS", "15 PPS", "30 PPS", "60 PPS", "120 PPS", "250 PPS", "500 PPS"};
	public PpsSetter() {
		super(choice);
		this.setSelectedIndex(4);
		this.setBounds(100, 50, 80, 20);
		this.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox<?> jc = (JComboBox<?>)e.getSource();
		switch ((String)jc.getSelectedItem()) {
			case "5 PPS": ThreadProxy.PPS = 5.f; break;
			case "10 PPS": ThreadProxy.PPS = 10.f; break;
			case "15 PPS": ThreadProxy.PPS = 15.f; break;
			case "30 PPS": ThreadProxy.PPS = 30.f; break;
			case "60 PPS": ThreadProxy.PPS = 60.f; break;
			case "120 PPS": ThreadProxy.PPS = 120.f; break;
			case "250 PPS": ThreadProxy.PPS = 250.f; break;
			case "500 PPS": ThreadProxy.PPS = 500.f; break;
		}
	}
}
