package es.ull.etsii.pai.practicafinal.main;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 *
 */
import javax.swing.JFrame;

import es.ull.etsii.pai.practicafinal.editor.Editor;
import es.ull.etsii.pai.practicafinal.editor.EditorFrame;

public class Main {
		public static void main(String[] args) {
			Editor.checkFileSystem();
			EditorFrame frame = new EditorFrame();
			frame.setTitle("Red VS Blue Editor");
		 	frame.setLocationRelativeTo(null); 
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			frame.setFocusable(true);
		}
} 
