package es.ull.etsii.pai.practicafinal.menu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import es.ull.etsii.pai.practicafinal.GameFrame;
import es.ull.etsii.pai.practicafinal.GameLoop;

import java.util.ArrayList;
import java.util.Random;

@SuppressWarnings("serial")
public class GUIQH extends JFrame {

	/** Objetos */

	PantallaInicial qhull = new PantallaInicial();
	int numberOfPoints = 10;
	private ArrayList<Point> SubQHull = new ArrayList<Point>();
	int sub = 0;

	/** Timer */
	private Timer timer;
	int delay = 2000; // milliseconds

	/** CONSTRUCTOR */
	public GUIQH(final int numberPoints, final int speed) {
		delay = speed;
		
		PantallaInicial pInicial = new PantallaInicial(); 
		
		add(pInicial);

		setTitle("Red and Blue");
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
