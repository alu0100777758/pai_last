package es.ull.etsii.pai.practicafinal.editor;

import java.awt.event.MouseEvent;
import java.nio.channels.SelectionKey;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class TextureTool extends DefaultTool {
	public TextureTool() {
		setButton(new JButton(new ImageIcon(getClass().getResource("/icons/textureTool.png"))));
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON3){
			if(getSelectedActor() != null)
				SetTexture();
		}
		setModified(true);
	}
	private void SetTexture() {
		
	}
}
