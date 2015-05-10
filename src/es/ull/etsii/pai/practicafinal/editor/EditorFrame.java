package es.ull.etsii.pai.practicafinal.editor;

import java.awt.BorderLayout;
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
import java.awt.event.MouseMotionListener;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import es.ull.etsii.pai.practicafinal.BvsR_Map;
import es.ull.etsii.pai.practicafinal.Player;
import es.ull.etsii.pai.practicafinal.RelativeLayout;
import es.ull.etsii.pai.practicafinal.StaticPlatform;
import es.ull.etsii.pai.practicafinal.physics.Physical_passive;
import es.ull.etsii.pai.prct9.geometry.Point2D;

public class EditorFrame extends JFrame implements ActionListener , MouseListener, MouseMotionListener{
	protected static final String SAVEMAP_SUFFIX = ".rvsbm";
	BvsR_Map map;
	EditorToolbar toolbar;
	TopPanel toppanel = new TopPanel();
	MapPainter bottomPanel;
	public BvsR_Map getMap() {
		return map;
	}
	public void setMap(BvsR_Map map) {
		this.map = map;
		this.toolbar.setMap(map);
		this.bottomPanel.setMap(map);
		
	}
	public EditorFrame() {
		map = new BvsR_Map(new Player(new Point2D(200, 200)));
		toolbar = new EditorToolbar(map);
		setLayout(new BorderLayout());
		KeyHandler keys = new KeyHandler();
		this.addKeyListener(keys);
		toppanel.add(toolbar);
		this.add(toppanel,BorderLayout.NORTH);
		bottomPanel = new MapPainter(map);
		bottomPanel.addMouseListener(this);
		bottomPanel.addMouseMotionListener(this);
		this.add(bottomPanel, BorderLayout.CENTER);
		createMenuBar();
	}
	private void createMenuBar() {

        JMenuBar menubar = new JMenuBar();

        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);

        JMenuItem guardar = new JMenuItem("guardar");
        guardar.setMnemonic(KeyEvent.VK_S);
        guardar.setToolTipText("Guardar el nivel");
        guardar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	      JFileChooser c = new JFileChooser(System.getProperty("user.dir"));
        	      int rVal = c.showOpenDialog(EditorFrame.this);
				if (rVal == JFileChooser.APPROVE_OPTION) {
        	        saveMap(c.getCurrentDirectory().toString()+System.getProperty("file.separator")+c.getSelectedFile().getName()+SAVEMAP_SUFFIX);
        	      }
        	      if (rVal == JFileChooser.CANCEL_OPTION) {
        	      }
        	    }
        
        });
        
        JMenuItem cargar = new JMenuItem("cargar");
        cargar.setMnemonic(KeyEvent.VK_C);
        cargar.setToolTipText("Guardar el nivel");
        cargar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	      JFileChooser c = new JFileChooser(System.getProperty("user.dir"));
        	      int rVal = c.showOpenDialog(EditorFrame.this);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					try {
						setMap(BvsR_Map.load(c.getCurrentDirectory().toString()+System.getProperty("file.separator")+c.getSelectedFile().getName()));
					} catch (ClassNotFoundException | IOException e1) {
						e1.printStackTrace();
					}
        	        System.out.println("load: "+ c.getCurrentDirectory().toString() + System.getProperty("file.separator")+c.getSelectedFile().getName());
        	      }
        	      if (rVal == JFileChooser.CANCEL_OPTION) {
        	      }
        	    }
        
        });
        
        JMenuItem nuevo = new JMenuItem("nuevo");
        nuevo.setMnemonic(KeyEvent.VK_C);
        nuevo.setToolTipText("Eliminar nivel actual y empezar desde cero");
        nuevo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		resetMap();
        	}

			private void resetMap() {
				setMap(new BvsR_Map(new Player(new Point2D(200, 200))));	
			}
        });
        file.add(nuevo);
        file.add(guardar);
        file.add(cargar);
        menubar.add(file);

        setJMenuBar(menubar);
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
			    	System.out.println("error");
			    	ex.printStackTrace();
			    }
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		toolbar.getSelectedTool().mouseClicked(e);
//		addRectangle(e.getX(),e.getY());
		repaint();
		// TODO Auto-generated method stub
		
	}
//	private void addRectangle(int i, int j) {
//		System.out.println("añadiendo");
//		map.addActor(new StaticPlatform(i-5,j, 100, 30),1);
//		repaint();
//	}
	@Override
	public void mousePressed(MouseEvent e) {
		toolbar.getSelectedTool().mousePressed(e);
		repaint();
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		toolbar.getSelectedTool().mouseReleased(e);
		repaint();
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		toolbar.getSelectedTool().mouseEntered(e);
		repaint();
	}
	@Override
	public void mouseExited(MouseEvent e) {
		toolbar.getSelectedTool().mouseExited(e);
		repaint();
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		toolbar.getSelectedTool().paint(bottomPanel.getGraphics());
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		toolbar.getSelectedTool().mouseDragged(arg0);
		repaint();
		
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		toolbar.getSelectedTool().mouseMoved(arg0);
		repaint();
		
	}
}
