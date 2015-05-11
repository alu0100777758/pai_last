package es.ull.etsii.pai.practicafinal.editor;

import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;

import es.ull.etsii.pai.practicafinal.BvsR_Map;

public abstract class EditorTool implements ActionListener, MouseListener,
		MouseMotionListener, KeyListener {

	private JButton button;
	int toolid = 0;
	BvsR_Map map = null;
	boolean modified = false;
	public EditorTool(){
	}

	public boolean isModified() {
		return modified;
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}

	public BvsR_Map getMap() {
		return map;
	}

	public void setMap(BvsR_Map map) {
		this.map = map;
	}

	public int getToolid() {
		return toolid;
	}

	public void setToolid(int toolid) {
		this.toolid = toolid;
		getButton().setName("" + getToolid());
	}

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}

	public void paint(Graphics g) {
	}

}
