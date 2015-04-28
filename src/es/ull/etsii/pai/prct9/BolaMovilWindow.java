package es.ull.etsii.pai.prct9;

import javax.swing.BoxLayout;
import javax.swing.JFrame;



public class BolaMovilWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	BolaMovilWindow(){
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setTitle("Bola Movil");
		setSize(750, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void validate() {
		super.validate();
		GameControl.getInstance().validateAfterEvent();
	}
}
