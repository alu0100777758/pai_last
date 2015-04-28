package es.ull.etsii.pai.prct9;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
/**
 * @author Javier Martín Hernández
 *	Clase encargada de procesar los eventos de timpo temporizador.
 */
public class TimerEventManager implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().getClass().equals(Timer.class)) {
			Timer timerLaunched = (Timer) e.getSource();
			if (timerLaunched.equals(GameControl.getInstance().getStepTimer())) {
				GameControl.getInstance().randomMove();
			}
		}
	}

}
