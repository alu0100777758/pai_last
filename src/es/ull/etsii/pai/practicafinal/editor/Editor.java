package es.ull.etsii.pai.practicafinal.editor;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 */
import java.io.File;

import javax.swing.JFrame;

public class Editor{
	public static void main(String[] args) {
		checkFileSystem();
		EditorFrame frame = new EditorFrame();
		frame.setTitle("Red VS Blue Editor");
	 	frame.setLocationRelativeTo(null); // Center the frame
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setFocusable(true);
	}
	public static void checkFileSystem(){
		// directorio texturas 
		File dir = new File(System.getProperty("user.dir")+ System.getProperty("file.separator")+ "textures");
		dir.mkdir();
		// directorio de mapas
		dir = new File(System.getProperty("user.dir")+ System.getProperty("file.separator")+ "maps");
		dir.mkdir();
	}
}