package jp.risu.CRGK.GUI.scene.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jp.risu.CRGK.util.ThreadProxy;

/**
 * 
 * <p>Date created: 2020/03/25
 * @author Risusan
 */
@SuppressWarnings("serial")
public class FPPSSetter extends JPanel {
	public final JLabel fps;
	public final JLabel pps;
	private final JComboBox<String> fpssetter;
	private final JComboBox<String> ppssetter;
	
	private static final String fps_choice[] 
		= {"1 FPS", "3 FPS", "10 FPS", "15 FPS", "30 FPS", "60 FPS", "80 FPS", "120 FPS"};
	private static final String pps_choice[]
		= {"5 PPS", "10 PPS", "15 PPS", "30 PPS", "60 PPS", "120 PPS", "250 PPS", "500 PPS"};
	
	public FPPSSetter() {
		super();
		
		this.setLayout(null);
		this.setBorder(BorderFactory.createTitledBorder("FPS & PPS setting"));
		this.setBounds(35, 5, 220, 80);
		
		fps = new JLabel();
		pps = new JLabel();
		fpssetter = new JComboBox<String>(fps_choice);
		ppssetter = new JComboBox<String>(pps_choice);
		
		this.fps.setBounds(30, 13, 80, 30);
		this.pps.setBounds(30, 45, 80, 30);
		this.fpssetter.setBounds(100, 20, 80, 20);
		this.ppssetter.setBounds(100, 50, 80, 20);
		
		this.fpssetter.setSelectedIndex(4);
		this.ppssetter.setSelectedIndex(4);
		
		this.fpssetter.addActionListener(new ActionListener() {
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
		});
		this.ppssetter.addActionListener(new ActionListener() {
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
		});
		
		this.add(this.fpssetter);
		this.add(this.ppssetter);
		this.add(this.fps);
		this.add(this.pps);
	}
}
