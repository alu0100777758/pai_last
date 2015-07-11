package es.ull.etsii.pai.practicafinal.main;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 *
 */
import java.awt.Dimension;

import javax.swing.JFrame;

import es.ull.etsii.pai.practicafinal.editor.Editor;
import es.ull.etsii.pai.practicafinal.editor.EditorFrame;
import es.ull.etsii.pai.practicafinal.redvsblue.ScreenManager;

public class Main {
		public static void main(String[] args) {
			Editor.checkFileSystem();
			SceneManager frame = new SceneManager();
			frame.switchScenario(new RvsB_Menu());
			frame.setTitle("Red VS Blue Editor");
		 	frame.setLocationRelativeTo(null); 
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.setSize(new Dimension(ScreenManager.getInstance().getWindWidth(),ScreenManager.getInstance().getWindHeight()));
		    frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
			frame.setUndecorated(true);
		    frame.setVisible(true);
			frame.setFocusable(true);
		}
} 
