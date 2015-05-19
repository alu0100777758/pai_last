package es.ull.etsii.pai.practicafinal.editor;

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

import es.ull.etsii.pai.practicafinal.AudioManager;
import es.ull.etsii.pai.practicafinal.BvsR_Map;
import es.ull.etsii.pai.practicafinal.GameFrame;
import es.ull.etsii.pai.practicafinal.GameLoop;
import es.ull.etsii.pai.practicafinal.Player_gauge;
import es.ull.etsii.pai.practicafinal.ScreenManager;

public class EditorFrame extends JFrame implements ActionListener,
		MouseListener, MouseMotionListener, KeyEventDispatcher {
	private static final long serialVersionUID = -5172144293412925652L;
	protected static final String SAVEMAP_SUFFIX = ".rvsbm";
	protected static final String TEMP_FILE_MAP = "map.temp";
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
        ScreenManager.getInstance().reset();
	}

	private void createMenuBar() {

		JMenuBar menubar = new JMenuBar();

		JMenu file = new JMenu("Archivo");
		file.setMnemonic(KeyEvent.VK_A);

		JMenuItem guardar = new JMenuItem("guardar");
		guardar.setMnemonic(KeyEvent.VK_G);
		guardar.setToolTipText("Guardar el nivel");
		guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser c = new JFileChooser(System
						.getProperty("user.dir"));
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

		JMenuItem cargar = new JMenuItem("cargar");
		cargar.setMnemonic(KeyEvent.VK_C);
		cargar.setToolTipText("Guardar el nivel");
		cargar.addActionListener(new ActionListener() {
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

		JMenuItem nuevo = new JMenuItem("nuevo");
		nuevo.setMnemonic(KeyEvent.VK_N);
		nuevo.setToolTipText("Eliminar nivel actual y empezar desde cero");
		nuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetMap();
			}

			private void resetMap() {
				setMap(new BvsR_Map());
				repaint();
			}
		});
		JMenuItem lanzarGameScenario= new JMenuItem("Lanzar partida");
		lanzarGameScenario.setMnemonic(KeyEvent.VK_L);
		lanzarGameScenario.setToolTipText("Lanzar partida");
		lanzarGameScenario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("ill try to launch");
				if(getMap().getGUI().isEmpty()){
					getMap().getGUI().add(new Player_gauge(getMap().getPlayer_one(),0));
					getMap().getGUI().add(new Player_gauge(getMap().getPlayer_two(),Player_gauge.TOP_RIGHT));
				}
				saveMap(TEMP_FILE_MAP);
				GameFrame frame = new GameFrame(TEMP_FILE_MAP);
				frame.setTitle("Red VS Blue");
				frame.setSize(ScreenManager.getInstance().getWindWidth(), ScreenManager.getInstance().getWindHeight());
			//	frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			 	frame.setLocationRelativeTo(null); // Center the frame
			    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				GameLoop.init(frame);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.addWindowListener(new WindowAdapter()
		        {
		            @Override
		            public void windowClosing(WindowEvent e)
		            {
		                AudioManager.stopAll();
		                GameLoop.stepTimer.stop();
		                e.getWindow().dispose();
		                ScreenManager.getInstance().setRate_x(1);
		                ScreenManager.getInstance().setRate_y(1);
		            }
		        });
			}});
		JMenu launch = new JMenu("Lanzar");
		launch.setMnemonic(KeyEvent.VK_L);
		file.add(nuevo);
		file.add(guardar);
		file.add(cargar);
		launch.add(lanzarGameScenario);
		menubar.add(file);
		menubar.add(launch);
		setJMenuBar(menubar);
	}

	public void actionPerformed(ActionEvent e) {

		System.out.println("updating");
		// TODO Auto-generated method stub
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
