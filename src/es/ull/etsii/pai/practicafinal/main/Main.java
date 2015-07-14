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
			SceneManager frame = new SceneManager();		
			frame.switchScenario(new RvsB_Menu());
			frame.setTitle("Red VS Blue Editor");
		 	frame.setLocationRelativeTo(null); 
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    class MyAdapter extends ComponentAdapter {
				@Override
				public void componentResized(ComponentEvent e) {
					ScreenManager screen = ScreenManager.getInstance();
					screen.setRate_x((double)frame.getWidth()/screen.getWindWidth());
					screen.setRate_y((double)frame.getHeight()/screen.getWindHeight());
					// notificar al manager del redimensionamiento
					//((ScenarioPanel)getScenarioPanel()).getScenario().getMapData().markForTexture();
				}
			}
		    frame.addComponentListener(new MyAdapter());
		   // frame.setSize(new Dimension(ScreenManager.getInstance().getWindWidth(),ScreenManager.getInstance().getWindHeight()));
		    frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		    frame.setUndecorated(true);
		    frame.setVisible(true);
			frame.setFocusable(true);

			//***** Esto no funciona!! ***/
//			ScreenManager.getInstance().setRate_x(frame.getWidth()/ScreenManager.getInstance().getWindWidth());
//			ScreenManager.getInstance().setRate_y(frame.getHeight()/ScreenManager.getInstance().getWindHeight());
			System.out.println(frame.getWidth());
			System.out.println(frame.getHeight());
	
		}
} 

/** Seria bueno contar como funcionan las clases singleton para los mas "avanzados"
  * 
  * Cuidado en el setUpdater del gameLoop!!! no se si esta del todo bien y si hace lo que creo 
  * que se supone que hace.
  *	
  *	Ahora que miro el GameLoop, creo que seria bueno tener un metodo para pararlo cuando tenemos escenarios
  * estaticos como la pantalla de ganador.
  *
  * Averiguar en que momento se para el gameloop al setearse a true el atributo ended del escenario de juego.
  *
  * Glitch en el screen manager, si se explicita el ancho y alto de la ventana a la resolucion del monitor
  * todo correcto, si se cambia durante la ejecucion como se esta haciendo en el main falla.
  * Ademas, cambiar el x rate, e y rate a valores distintos de 1 da comportamientos extraños tanto en el selector
  * como en la pantalla de ganador. (Estas cosas pasan en mi portatil porque tiene resolucion 1366x768, en el 
  * sobremesa no pasa).
**/
