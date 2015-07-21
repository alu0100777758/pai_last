package es.ull.etsii.pai.practicafinal.editor;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 */
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import es.ull.etsii.pai.practicafinal.main.SceneManager;
import es.ull.etsii.pai.practicafinal.redvsblue.AudioManager;
import es.ull.etsii.pai.practicafinal.redvsblue.BvsR_Map;
import es.ull.etsii.pai.practicafinal.redvsblue.GameScenario;
import es.ull.etsii.pai.practicafinal.redvsblue.ResourceManager;
import es.ull.etsii.pai.practicafinal.redvsblue.ScreenManager;




/**
 * Ventana del editor
 */
public class EditorFrame extends JFrame implements ActionListener,
		MouseListener, MouseMotionListener, KeyEventDispatcher {
	private static final String SAVE_TOOLTIP = "Guardar el nivel";
	private static final String MENU_SAVE_STRING = "guardar";
	private static final long serialVersionUID = -5172144293412925652L;
	public static final String SAVEMAP_SUFFIX = ".rvsbm";
	public static final String TEMP_FILE_MAP = "map.temp";
	public static final String MENU_FILE_STRING = "Archivo";
	BvsR_Map map;
	EditorToolbar toolbar;
	JPanel toppanel = new JPanel();
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
		map = new BvsR_Map();
		toolbar = new EditorToolbar(map,this);
		setLayout(new BorderLayout());
		toppanel.add(toolbar);
		this.add(toppanel, BorderLayout.NORTH);
		bottomPanel = new MapPainter(map);
		bottomPanel.setGuiActive(false);
		bottomPanel.addMouseListener(this);
		bottomPanel.addMouseMotionListener(this);
		this.add(bottomPanel, BorderLayout.CENTER);
		createMenuBar();
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(this);    
        pack();
        setResizable(false);
        ScreenManager.getInstance().reset();
	}

	private void createMenuBar() {

		JMenuBar menubar = new JMenuBar();

		JMenu file = new JMenu(MENU_FILE_STRING);
		file.setMnemonic(KeyEvent.VK_A);

		JMenuItem save = new JMenuItem(MENU_SAVE_STRING);
		save.setMnemonic(KeyEvent.VK_G);
		save.setToolTipText(SAVE_TOOLTIP);
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser c = new JFileChooser(ResourceManager.getUserDir());
				int rVal = c.showOpenDialog(EditorFrame.this);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					String path = c.getCurrentDirectory().toString()
							+ System.getProperty("file.separator")
							+ c.getSelectedFile().getName();
					if (!path.substring(
							path.length() - SAVEMAP_SUFFIX.length(),
							path.length()).equals(SAVEMAP_SUFFIX))
						path += SAVEMAP_SUFFIX;
					saveMap(path);
				}
				if (rVal == JFileChooser.CANCEL_OPTION) {
				}
			}

		});

		JMenuItem load = new JMenuItem("cargar");
		load.setMnemonic(KeyEvent.VK_C);
		load.setToolTipText(SAVE_TOOLTIP);
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser c = new JFileChooser(System
						.getProperty("user.dir"));
				int rVal = c.showOpenDialog(EditorFrame.this);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					try {
						setMap(BvsR_Map.load(c.getCurrentDirectory().toString()
								+ System.getProperty("file.separator")
								+ c.getSelectedFile().getName()));
						repaint();
					} catch (ClassNotFoundException | IOException e1) {
						e1.printStackTrace();
					}
					System.out.println("load: "
							+ c.getCurrentDirectory().toString()
							+ System.getProperty("file.separator")
							+ c.getSelectedFile().getName());
				}
				if (rVal == JFileChooser.CANCEL_OPTION) {
				}
			}

		});

		JMenuItem newFile = new JMenuItem("nuevo");
		newFile.setMnemonic(KeyEvent.VK_N);
		newFile.setToolTipText("Eliminar nivel actual y empezar desde cero");
		newFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetMap();
			}

			private void resetMap() {
				setMap(new BvsR_Map());
				repaint();
			}
		});
		JMenuItem launchGameScenario= new JMenuItem("Lanzar partida");
		launchGameScenario.setMnemonic(KeyEvent.VK_L);
		launchGameScenario.setToolTipText("Lanzar partida");
		launchGameScenario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				if(getMap().getGUI().isEmpty()){
//					getMap().getGUI().add(new Player_gauge(getMap().getPlayer_one(),0));
//					getMap().getGUI().add(new Player_gauge(getMap().getPlayer_two(),Player_gauge.TOP_RIGHT));
//				}
				saveMap(TEMP_FILE_MAP);
//				GameFrame frame = new GameFrame(TEMP_FILE_MAP);
				SceneManager frame = new SceneManager();
				frame.setTitle("Red VS Blue");
				frame.setSize(ScreenManager.getInstance().getWindWidth(), ScreenManager.getInstance().getWindHeight());
			 	frame.setLocationRelativeTo(null); 
			 	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			 	frame.setVisible(true);
			 	frame.switchScenario(
						new GameScenario(TEMP_FILE_MAP,new SceneManager()));
			 	frame.setResizable(false);
				frame.addWindowListener(new WindowAdapter()
		        {
		            @Override
		            public void windowClosing(WindowEvent e)
		            {
		                AudioManager.stopAll();
//		                GameLoop.stepTimer.stop();
		                e.getWindow().dispose();
		                ScreenManager.getInstance().setRate_x(1);
		                ScreenManager.getInstance().setRate_y(1);
		            }
		        });
			}});
		JMenu launch = new JMenu("Lanzar");
		launch.setMnemonic(KeyEvent.VK_L);
		file.add(newFile);
		file.add(save);
		file.add(load);
		launch.add(launchGameScenario);
		menubar.add(file);
		menubar.add(launch);
		setJMenuBar(menubar);
	}

	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	public void saveMap(String string) {
		getMap().markForTexture();
		try (OutputStream file = new FileOutputStream(string);
				OutputStream buffer = new BufferedOutputStream(file);
				ObjectOutput output = new ObjectOutputStream(buffer);) {
			output.writeObject(map);
		} catch (IOException ex) {
			System.out.println("error");
			ex.printStackTrace();
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		toolbar.getSelectedTool().mouseClicked(e);
		repaint();
	}

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

	@Override
	public void repaint() {
		if (toolbar.getSelectedTool().isModified()) {
			super.repaint();
			toolbar.getSelectedTool().setModified(false);
		}
	}
	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		if (e.getID() == KeyEvent.KEY_PRESSED) {
			toolbar.getSelectedTool().keyPressed(e);
		} else if (e.getID() == KeyEvent.KEY_RELEASED) {
			toolbar.getSelectedTool().keyReleased(e);
		} else if (e.getID() == KeyEvent.KEY_TYPED) {
			toolbar.getSelectedTool().keyTyped(e);
		}
		repaint();
		return false;
	}
}