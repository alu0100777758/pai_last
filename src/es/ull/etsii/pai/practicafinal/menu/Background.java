package es.ull.etsii.pai.practicafinal.menu;

import java.awt.*;
import java.awt.image.*;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Background extends JPanel {

	private Image image;

	private double x;
	private double y;
	private double dx;
	private double dy;

	private double moveScale;

	public Background(String s, double ms) {
		try {
			ImageIcon icon = new ImageIcon(ImageIO.read(new File(s)));
			image = icon.getImage();
			moveScale = ms;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setPosition(double x, double y) {
		this.x = (x * moveScale) % GamePanel.WIDTH;
		this.y = (y * moveScale) % GamePanel.HEIGHT;
	}

	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public void update() {
		x += dx;
		y += dy;
		// repaint();
	}

	public void draw(Graphics2D g) {
//		super.paint(g);

//		g.drawImage(image, 0, 0, (ImageObserver) this);
		// JLabel imageLabel = new JLabel();
		// imageLabel.setIcon(icon);
		// add(p1Image);
		// setOpaque(false);

		 g.drawImage(image, (int)x, (int)y, null);
		//
		if (x < 0) {
			g.drawImage(image, (int) x + GamePanel.WIDTH, (int) y, null);
		}
		if (x > 0) {
			g.drawImage(image, (int) x - GamePanel.WIDTH, (int) y, null);
		}
	}

}
