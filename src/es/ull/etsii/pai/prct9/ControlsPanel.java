package es.ull.etsii.pai.prct9;

import javax.swing.JPanel;

public class ControlsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private DPad dpad;
	public ControlsPanel() {
		dpad = new DPad();
//		setLayout(new GridLayout(1, 3));
		add(dpad);
		// TODO Auto-generated constructor stub
	}
}
