package es.ull.etsii.pai.practicafinal.main;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 *
 */
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

import es.ull.etsii.pai.practicafinal.editor.Editor;
import es.ull.etsii.pai.practicafinal.redvsblue.ScenarioPanel;
import es.ull.etsii.pai.practicafinal.redvsblue.ScreenManager;

public class Main {
		public static void main(String[] args) {
			Editor.checkFileSystem();
			//System.setProperty("sun.java2d.opengl","True");
			SceneManager frame = new SceneManager();		
			frame.switchScenario(new RvsB_Menu());
			frame.setTitle("Red VS Blue Editor");
		 	frame.setLocationRelativeTo(null); 
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    // TODO refactorizar 
		    class MyAdapter extends ComponentAdapter {
				@Override
				public void componentResized(ComponentEvent e) {
					ScreenManager screen = ScreenManager.getInstance();
					screen.setRate_x((double)frame.getWidth()/screen.getWindWidth());
					screen.setRate_y((double)frame.getHeight()/screen.getWindHeight());
					frame.notify_resize();
				}
			}
		    frame.addComponentListener(new MyAdapter());
		    frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		    frame.setUndecorated(true);
		    frame.setVisible(true);
			frame.setFocusable(true);
			try {
				Thread.sleep(250);
				frame.validate();
				frame.requestFocus();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
} 
