package es.ull.etsii.pai.practicafinal.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import es.ull.etsii.pai.practicafinal.editor.EditorFrame;
import es.ull.etsii.pai.practicafinal.redvsblue.ScenarioPanel;
import es.ull.etsii.pai.practicafinal.redvsblue.ScreenManager;

public class RvsB_Menu extends ScenarioPanel implements ActionListener {
	public RvsB_Menu() {
//		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel mainMenu = new JPanel();
//		mainMenu.setBackground(Color.GREEN);
		mainMenu.setLayout(new BoxLayout(mainMenu, BoxLayout.Y_AXIS));
		mainMenu.add(Box.createVerticalStrut(200));
		JButton button = new JButton("jugar");
		button.setActionCommand("play");
		button.addActionListener(this);
		mainMenu.add(button);
		mainMenu.add(Box.createVerticalStrut(20));
		JButton button2 = new JButton("editor");
		button2.setActionCommand("edit");
		button2.addActionListener(this);
		mainMenu.add(button2);
		add(mainMenu,SwingConstants.CENTER);
		setSize(getMaximumSize());
//		setBackground(Color.RED);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch (arg0.getActionCommand()) {
		case "play":
			getSceneManager().switchScenario(new MapSelector());
			break;
		case "edit":
			EditorFrame frame1 = new EditorFrame();
			frame1.setTitle("Red VS Blue Editor");
		 	frame1.setLocationRelativeTo(null); // Center the frame
		    frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame1.setVisible(true);
			frame1.setFocusable(true);
			frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			break;
		default:
			break;
		}
	}
	

}
