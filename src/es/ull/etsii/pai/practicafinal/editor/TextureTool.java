package es.ull.etsii.pai.practicafinal.editor;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class TextureTool extends DefaultTool {
	public TextureTool() {
		setButton(new JButton(new ImageIcon(getClass().getResource("/icons/textureTool.png"))));
	}
}
