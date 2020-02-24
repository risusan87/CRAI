package jp.risu.CRGK.GUI.component.button;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import jp.risu.CRGK.GUI.component.ComponentManager;

@SuppressWarnings("serial")
public abstract class TextButton extends JLabel implements MouseListener {
	private ImageIcon imgico;
	public TextButton(ImageIcon img, String str) {
		super(img, JLabel.HORIZONTAL);
		this.imgico = img;
		this.setText(str);
		this.addMouseListener(this);
	}
	
	public TextButton(String str) {
		super();
		this.setText(str);
	}
	
	public TextButton() {
		super();
	}
	
	public void setSize() {
		this.setFont(new Font("Arial", Font.PLAIN, 40));
		this.imgico = new ImageIcon(this.imgico.getImage().getScaledInstance((int) (this.imgico.getIconWidth() * 0.2), -1, Image.SCALE_SMOOTH));
		this.setIcon(this.imgico);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("SHIT");
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("SHIT2");
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
