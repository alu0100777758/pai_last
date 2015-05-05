package es.ull.etsii.pai.prct9;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class DPad extends JPanel {
	private static final long serialVersionUID = 1L;
	private ClassLoader cl = this.getClass().getClassLoader();
	private static final String UP_PATH = "up.png";
	private static final String LEFT_PATH = "left.png";
	private static final String RIGHT_PATH = "right.png";
	private static final String DOWN_PATH = "down.png";
	public static final String UP = "N";
	public static final String LEFT = "O";
	public static final String RIGHT = "E";
	public static final String DOWN = "S";
	public static final String UP_LEFT = "NO";
	public static final String DOWN_LEFT = "SO";
	public static final String UP_RIGHT = "NE";
	public static final String DOWN_RIGHT = "SE";
	private JButton up;
	private JButton left;
	private JButton right;
	private JButton down;
	private JButton upleft;
	private JButton downleft;
	private JButton upright;
	private JButton downright;
	DPad(){
		setBorder(BorderFactory.createLineBorder(Color.black));
		buildButtons();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel tempPanel = new JPanel();
		tempPanel.add(upleft);
		tempPanel.add(up);
		tempPanel.add(upright);
		add(tempPanel);
		tempPanel = new JPanel();
		tempPanel.add(left);
		tempPanel.add(right);
		add(tempPanel);
		tempPanel = new JPanel();
		tempPanel.add(downleft);
		tempPanel.add(down);
		tempPanel.add(downright);
		add(tempPanel);
	}
	private void buildButtons(){
		up = new JButton(new ImageIcon(cl.getResource(UP_PATH)));
		left = new JButton(new ImageIcon(cl.getResource(LEFT_PATH)));
		right = new JButton(new ImageIcon(cl.getResource(RIGHT_PATH)));
		down = new JButton(new ImageIcon(cl.getResource(DOWN_PATH)));
		downleft = new JButton();
		upleft = new JButton();
		downright = new JButton();
		upright = new JButton();
		
		upleft.setText(UP_LEFT);
		up.setText(UP);
		upright.setText(UP_RIGHT);
		left.setText(LEFT);
		right.setText(RIGHT);
		downleft.setText(DOWN_LEFT);
		down.setText(DOWN);
		downright.setText(DOWN_RIGHT);
		
		up.addActionListener(GameControl.getInstance());
		left.addActionListener(GameControl.getInstance());
		right.addActionListener(GameControl.getInstance());
		down.addActionListener(GameControl.getInstance());
		
		upleft.addActionListener(GameControl.getInstance());
		downleft.addActionListener(GameControl.getInstance());
		upright.addActionListener(GameControl.getInstance());
		downright.addActionListener(GameControl.getInstance());
	}
}
