package es.ull.etsii.pai.practicafinal.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicButtonUI;

import es.ull.etsii.pai.practicafinal.redvsblue.ResourceManager;

public class MenuUI extends BasicButtonUI {
	public static final String BACKGROUND_TEXTURE = "Recursos\\textures\\menu_item_background.png";

	private final static MenuUI m_buttonUI = new MenuUI();

	protected Border m_borderRaised = BorderFactory.createCompoundBorder();

	protected Border m_borderLowered = UIManager
			.getBorder("Button.borderPressed");

	protected Color selected = new Color((float)1, (float)1, (float)1, (float)0.3);

	protected Color unselected = new Color((float)1, (float)1, (float)1, (float)0.0);

	protected Color m_focusBorder = Color.BLUE;

	private Font font = new Font("Impact", Font.PLAIN, 72);
	private Color fontColor = new Color(0,92,40);

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public static ComponentUI createUI(JComponent c) {
		return m_buttonUI;
	}

	public void paint(Graphics g, JComponent c) {
		AbstractButton b = (AbstractButton) c;
		Dimension d = b.getSize();

		g.setFont(getFont());
		FontMetrics fm = g.getFontMetrics();
		g.drawImage(
				ResourceManager.getInstance().getBufferedImage(
						BACKGROUND_TEXTURE), 0, 0, c.getWidth(), c.getHeight(),
				null);
		g.setColor(fontColor);
		String caption = b.getText();
		int x = (d.width - fm.stringWidth(caption)) / 2;
		int y = (d.height + fm.getAscent()) / 2;
		g.drawString(caption, x, y);

	}

	public Dimension getPreferredSize(JComponent c) {
		Dimension d = super.getPreferredSize(c);
		if (m_borderRaised != null) {
			Insets ins = m_borderRaised.getBorderInsets(c);
			d.setSize(d.width + ins.left + ins.right, d.height + ins.top
					+ ins.bottom);
		}
		return d;
	}

	public void mouseClicked(MouseEvent e) {
	}

//	public void mousePressed(MouseEvent e) {
//		JComponent c = (JComponent) e.getComponent();
//		c.setBorder(m_borderLowered);
//		c.setBackground(m_backgroundPressed);
//	}
//
//	public void mouseReleased(MouseEvent e) {
//		JComponent c = (JComponent) e.getComponent();
//		c.setBorder(m_borderRaised);
//		c.setBackground(m_backgroundNormal);
//	}

	public void mouseEntered(MouseEvent e) {
		JComponent c = (JComponent) e.getComponent();
		c.setBackground(selected);
		c.repaint();
	}

	public void mouseExited(MouseEvent e) {
		JComponent c = (JComponent) e.getComponent();
		c.setBackground(unselected);
		c.repaint();
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
			JComponent c = (JComponent) e.getComponent();
			c.setBorder(m_borderLowered);
		}
	}

	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
			JComponent c = (JComponent) e.getComponent();
			c.setBorder(m_borderRaised);
		}
	}
}
