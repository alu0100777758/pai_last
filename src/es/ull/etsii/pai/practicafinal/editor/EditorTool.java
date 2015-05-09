package es.ull.etsii.pai.practicafinal.editor;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public abstract class EditorTool implements  ActionListener , MouseListener{
	JButton button;

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}
	
}
