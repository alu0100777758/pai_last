package es.ull.etsii.pai.practicafinal.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import es.ull.etsii.pai.practicafinal.BvsR_Map;

public class EditorToolbar extends JToolBar implements ActionListener {
	EditorTool [] tools = {new DefaultTool(), new RectangleTool(), new PlayerInitTool()};
	int selectedToolIndex = 0;
	BvsR_Map workingOn = null;
	public int getSelectedToolIndex() {
		return selectedToolIndex;
	}
	public void setSelectedToolIndex(int selectedToolIndex) {
		this.selectedToolIndex = selectedToolIndex;
	}
	public EditorToolbar(BvsR_Map map) {
		setMap(map);
		for(int i = 0; i < tools.length; i++){
			add(tools[i].getButton());
			tools[i].getButton().addActionListener(this);
			tools[i].setToolid(i);
		}
		setMapToTools(map);
		tools[0].getButton().setEnabled(false);
	}
	public EditorTool getSelectedTool(){
		return tools[selectedToolIndex];
	}
	private void setMapToTools(BvsR_Map map ){
		for(EditorTool tool : tools)
			tool.setMap(workingOn);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton boton = (JButton)arg0.getSource();
		boton.setEnabled(false);
		tools[selectedToolIndex].getButton().setEnabled(true);
		setSelectedToolIndex(Integer.parseInt(boton.getName()));
		
	}
	public void setMap(BvsR_Map map) {
		this.workingOn = map;
		setMapToTools(map);
		
	}

}