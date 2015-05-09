package es.ull.etsii.pai.practicafinal.editor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class EditorToolbar extends JToolBar {
	EditorTool [] tools = {new DefaultTool()};
	int selectedToolIndex = 0;
	public EditorToolbar() {
		add(tools[0].getButton());
		add(new JButton(new ImageIcon("rectangleTool.png")));
		add(new JButton(new ImageIcon("PlayerInitTool.png")));
	}

}
