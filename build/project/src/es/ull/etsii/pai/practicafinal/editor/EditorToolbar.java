package es.ull.etsii.pai.practicafinal.editor;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;

import es.ull.etsii.pai.practicafinal.redvsblue.BvsR_Map;




/**
 * clase encargada de almacenar y disponer las herramientas del editor en una
 * barra de herramientas
 *
 */
public class EditorToolbar extends JToolBar implements ActionListener {
	private static final long serialVersionUID = 6391135058169913712L;			//	serial de la ultima version
	public static final EditorTool[] TOOLS = { new DefaultTool(),				//	array con las herramientas disponibles
			new RectangleTool(), new BackgroundTool(),new ForegroundTool(), new PlayerInitTool(),
			new PowerUPTool(), new TextureTool() };
	int selectedToolIndex = 0;													//	indice en el array anterior correspondiente con la herramienta seleccionada
	BvsR_Map workingOn = null;													//	mapa sobre el que est� trabajando el editor actualmente
	
	/**
	 * @param map	mapa a editar
	 * @param frame	Jframe que esta mostrando la "accion" al usuario
	 */
	public EditorToolbar(BvsR_Map map, JFrame frame) {
		setMap(map);
		for (int i = 0; i < TOOLS.length; i++) {
			add(TOOLS[i].getButton());
			TOOLS[i].getButton().addActionListener(this);
			TOOLS[i].setToolid(i);
			TOOLS[i].setFrame(frame);
		}
		setMapToTools(map);
		TOOLS[0].getButton().setEnabled(false);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * 	Actualiza la herramienta seleccionada, as� como su boton correspondiente
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton boton = (JButton) arg0.getSource();
		boton.setEnabled(false);
		TOOLS[selectedToolIndex].getButton().setEnabled(true);
		setSelectedToolIndex(Integer.parseInt(boton.getName()));
	}

	/**
	 * Getters y setters
	 */
	public void setMap(BvsR_Map map) {
		this.workingOn = map;
		setMapToTools(map);

	}
	public int getSelectedToolIndex() {
		return selectedToolIndex;
	}

	public void setSelectedToolIndex(int selectedToolIndex) {
		this.selectedToolIndex = selectedToolIndex;
	}


	public EditorTool getSelectedTool() {
		return TOOLS[selectedToolIndex];
	}

	private void setMapToTools(BvsR_Map map) {
		for (EditorTool tool : TOOLS)
			tool.setMap(workingOn);
	}

}
