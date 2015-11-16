package es.ull.etsii.pai.practicafinal.main;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.metal.MetalButtonUI;

import es.ull.etsii.pai.practicafinal.editor.MapPainter;
import es.ull.etsii.pai.practicafinal.redvsblue.BvsR_Map;

public class MapPreview extends JButton {
	private static final int MARGIN = 10;
	private String name;
	private BvsR_Map mapa = null;
	private BufferedImage snapshot = null;
	private boolean selected;
	


	public MapPreview(String mapname) {
		this.name = mapname;
//		add(new JLabel(getName()));
//		System.out.println("path: " + name);
//		if(mapname != "void"){
		try {
			setMapa(BvsR_Map.load(name));
		} catch (FileNotFoundException e) { 
			System.out.println("file not found");
		} catch (ClassNotFoundException e) {
			System.out.println("class not found");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setSnapshot(MapPainter.getPict(getMapa()));
//		}
		setActionCommand(getName());
		setUI(new previewUi());
		
	}

	// @Override
	// public void paintComponent(Graphics g) {
	// super.paintComponent(g);
	// g.drawImage(getSnapshot(), (int) getLocation().getX(),
	// (int) getLocation().getY(), getWidth(), getHeight(), null);
	// }
//	@Override
//	protected void paintBorder(Graphics g){
//		Graphics2D g2 = (Graphics2D)g.create();
//	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//			RenderingHints.VALUE_ANTIALIAS_ON);
//	g2.setColor(Color.RED);
//	g2.drawRoundRect((int)getLocation().x,(int) getLocation().y, getWidth(),getHeight(), MARGIN, MARGIN);
//	}
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public BvsR_Map getMapa() {
		return mapa;
	}

	public void setMapa(BvsR_Map mapa) {
		this.mapa = mapa;
	}

	public BufferedImage getSnapshot() {
		return snapshot;
	}

	public void setSnapshot(BufferedImage snapshot) {
		this.snapshot = snapshot;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	class previewUi extends MetalButtonUI {

//		@Override
//		public void paintBorder(Component c, Graphics g, int x, int y,
//				int width, int height) {
//			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//					RenderingHints.VALUE_ANTIALIAS_ON);
//			g.setColor(Color.RED);
//			g.drawRoundRect(x, y, width, height, MARGIN, MARGIN);
//		}

		@Override
		public void paint(Graphics g, JComponent c) {
			MapPreview c2 = (MapPreview) c;
			c2.setBackground(Color.ORANGE);
			if (isSelected())
				g.drawImage(c2.getSnapshot(), MARGIN, MARGIN, getWidth() - 2 * MARGIN, getHeight() - 2 * MARGIN, null);
			else 
				g.drawImage(c2.getSnapshot(), 0, 0, getWidth(), getHeight(), null);
		}

//		@Override
//		public void paintButtonPressed(Graphics g, AbstractButton b) {
//			Color color = new Color(255, 100, 100, 100);
//			paintText(g, b, b.getBounds(), b.getText());
//			g.setColor(Color.red.brighter());
//			g.fillRect(0, 0, b.getSize().width, b.getSize().height);
//		}
		// @Override
		// protected void paintFocus(Graphics g, AbstractButton b,
		// java.awt.Rectangle viewRect, java.awt.Rectangle textRect,
		// java.awt.Rectangle iconRect) {
		// super.paintFocus(g, b, viewRect, textRect, iconRect);
		// Color color = new Color(255, 100, 100, 100);
		// paintText(g, b, b.getBounds(), b.getText());
		// g.setColor(Color.red.brighter());
		// g.fillRect(0, 0, b.getSize().width, b.getSize().height);
		// }
	}

}
