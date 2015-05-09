package es.ull.etsii.pai.practicafinal.editor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JFrame;

import es.ull.etsii.pai.practicafinal.BvsR_Map;
import es.ull.etsii.pai.practicafinal.Player;
import es.ull.etsii.pai.practicafinal.StaticPlatform;
import es.ull.etsii.pai.practicafinal.physics.Physical_passive;
import es.ull.etsii.pai.prct9.geometry.Point2D;

public class EditorFrame extends JFrame implements ActionListener , MouseListener{
	BvsR_Map map = new BvsR_Map(new Player(new Point2D(200, 200)));
	public EditorFrame() {
		
//		setScenarioPanel(new ScenarioPanel());
//		setScenarioPanel(getScenarioPanel());
//		this.add(getScenarioPanel());
		KeyHandler keys = new KeyHandler();
		this.addKeyListener(keys);
		this.addMouseListener(this);
		JButton save = new JButton("save");
		save.addActionListener(this);
//		this.add(save);
//		this.add
	}

	class KeyHandler implements KeyListener {

		@Override
		public void keyPressed(KeyEvent s) {
			saveMap("default");
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {

		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			//System.out.println("typed");
			
		}
		
	}
	public void actionPerformed(ActionEvent e) {
	        Toolkit.getDefaultToolkit().sync();
	        System.out.println("updating");
		// TODO Auto-generated method stub
		
	}
	public void saveMap(String string) {
		try (
			      OutputStream file = new FileOutputStream(string);
			      OutputStream buffer = new BufferedOutputStream(file);
			      ObjectOutput output = new ObjectOutputStream(buffer);
			    ){
			      output.writeObject(map);
			    }  
			    catch(IOException ex){
			    }
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		addRectangle(e.getX(),e.getY());
		// TODO Auto-generated method stub
		
	}
	private void addRectangle(int i, int j) {
		System.out.println("añadiendo");
		map.addActor(new StaticPlatform(i,j, 100, 30),1);
		repaint();
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.fillRect(0, 0, getWidth(), getHeight());
		Graphics2D g2 = (Graphics2D) g.create();
		for (int i = 0; i < map.getStaticMap().size(); i++) {
			((StaticPlatform) map.getStaticMap().get(i)).paint(g.create());
		}
		for (int i = 0; i < map.getActors().size(); i++) {
			map.getActors().get(i).paint(g.create());
		}
	}
}
