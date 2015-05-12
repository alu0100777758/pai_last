package es.ull.etsii.pai.practicafinal.menu;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javazoom.jl.player.Player;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import es.ull.etsii.pai.practicafinal.GameFrame;
import es.ull.etsii.pai.practicafinal.GameLoop;

@SuppressWarnings("serial")
class PantallaInicial extends JPanel implements ActionListener, MouseListener {
	
	/** VARIABLES */
	private JButton jbtIniciar = new JButton("Iniciar Batalla");
	private JButton jbtRecords = new JButton("Editar");
	private JButton jbtOpciones = new JButton("Opciones");
	private JButton jbtCreditos = new JButton("Créditos");
	
	boolean PInicial = true;	
	boolean PEditor = false;
	
	PantallaInicial()
    {
		this.addMouseListener(this);
		jbtIniciar.addActionListener(this);
    }
	
	 


	/** Mï¿½TODOS */
	protected void paintComponent(Graphics g) {
		if (PInicial) {	  // Pantalla inicial
			JLabel imageLabel = new JLabel();
			ImageIcon ii = new ImageIcon(this.getClass().getResource("imagen.gif"));
			imageLabel.setIcon(ii);
			
			g.drawImage (ii.getImage(), 0, 0, this.getWidth(), this.getHeight(), this.getBackground(), this);
			setOpaque(false);
	        super.paintComponent(g);
	        
			jbtIniciar.setBounds(new Rectangle(170, 100, 150, 20));
			add(jbtIniciar);
			jbtRecords.setBounds(new Rectangle(170, 130, 150, 20));
			add(jbtRecords);
			jbtOpciones.setBounds(new Rectangle(170, 160, 150, 20));
			add(jbtOpciones);
			jbtCreditos.setBounds(new Rectangle(170, 190, 150, 20));
			add(jbtCreditos);
		}
		else if (PEditor) {  // Pantalla Edición
			this.removeAll();
			JLabel imageLabel = new JLabel();
			ImageIcon ii = new ImageIcon(this.getClass().getResource("imagen2.gif"));
			imageLabel.setIcon(ii);
			super.paintComponent(g);
			g.drawImage (ii.getImage(), 0, 0, this.getWidth(), this.getHeight(), this.getBackground(), this);
			setOpaque(false);
	        super.paintComponent(g);
		}
//		else if (POpciones) {  // Pantalla Edición
//			this.removeAll();
//			JLabel imageLabel = new JLabel();
//			ImageIcon ii = new ImageIcon(this.getClass().getResource("imagen2.gif"));
//			imageLabel.setIcon(ii);
//			super.paintComponent(g);
//			g.drawImage (ii.getImage(), 0, 0, this.getWidth(), this.getHeight(), this.getBackground(), this);
//			setOpaque(false);
//	        super.paintComponent(g);
//		}
		
	
	}

//	
////	/** LISTENERS */
//	/** Créditos */
//	jbtIniciar.addActionListener(new ActionListener() {
//		public void actionPerformed(ActionEvent e) {
////			saveMap("test1.rvsbm");
//			System.out.println("pulsando");
//			GameFrame frame = new GameFrame("test1.rvsbm");
//			frame.setTitle("Red VS Blue");
//			frame.setSize(1200, 800);
//		//	frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//		 	frame.setLocationRelativeTo(null); // Center the frame
//		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//			frame.setVisible(true);
//			GameLoop.init(frame);
//		}

//	public void actionPerformed1(ActionEvent e) {
//        if (e.getSource()==jbtIniciar) {
//        	System.out.println("pulsando");
//			GameFrame frame = new GameFrame("test1.rvsbm");
//			frame.setTitle("Red VS Blue");
//			frame.setSize(1200, 800);
//		//	frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//		 	frame.setLocationRelativeTo(null); // Center the frame
//		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//			frame.setVisible(true);
//			GameLoop.init(frame);
//        }
//	}
//    
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mouseClicked");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		 System.out.println("mouseEntered");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mouseExited");
		Sonidos reproducir = new Sonidos("sonido.mp3");
        reproducir.start();
        System.out.println("Reproduciendo");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mousePressed");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mouseReleased");
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		 if (arg0.getSource()==jbtIniciar) {
	        	System.out.println("pulsando");
				GameFrame frame = new GameFrame("test1.rvsbm");
				frame.setTitle("Red VS Blue");
				frame.setSize(1200, 800);
			//	frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			 	frame.setLocationRelativeTo(null); // Center the frame
			    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				GameLoop.init(frame);
		 }
	} 
	
	
}