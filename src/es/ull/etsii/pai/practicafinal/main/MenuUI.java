package es.ull.etsii.pai.practicafinal.main;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 */
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
	private static final int SELECTION_GAP = 10;

	public static final String BACKGROUND_TEXTURE = "textures/menu_item_background.png";

	private final static MenuUI m_buttonUI = new MenuUI();

	protected Border m_borderRaised = BorderFactory.createCompoundBorder();

	protected Border m_borderLowered = UIManager
			.getBorder("Button.borderPressed");

	protected Color selected = new Color((float) 1, (float) 1, (float) 1,
			(float) 0.3);

	protected Color unselected = new Color((float) 1, (float) 1, (float) 1,
			(float) 0.0);

	protected Color m_focusBorder = Color.BLUE;

	private Font font = new Font("Impact", Font.PLAIN, 72);
	private Color fontColor = new Color(0, 92, 40);
	private Font selectionFont = new Font("Impact", Font.PLAIN, 62);
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
		if(c==RvsB_Menu.getSelection()){
			g.drawImage(
					ResourceManager.getInstance().getBufferedImage(
							BACKGROUND_TEXTURE), SELECTION_GAP, SELECTION_GAP, c.getWidth()- ( 2 * SELECTION_GAP) , c.getHeight()-(2 * SELECTION_GAP),null);
		g.setFont(getSelectionFont());
		}
		else {
		g.drawImage(
				ResourceManager.getInstance().getBufferedImage(
						BACKGROUND_TEXTURE), 0, 0, c.getWidth(), c.getHeight(),
				null);
		g.setFont(getFont());
		}
		FontMetrics fm = g.getFontMetrics();
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

	// public void mousePressed(MouseEvent e) {
	// JComponent c = (JComponent) e.getComponent();
	// c.setBorder(m_borderLowered);
	// c.setBackground(m_backgroundPressed);
	// }
	//
	// public void mouseReleased(MouseEvent e) {
	// JComponent c = (JComponent) e.getComponent();
	// c.setBorder(m_borderRaised);
	// c.setBackground(m_backgroundNormal);
	// }

	public void mouseEntered(MouseEvent e) {
		RvsB_Menu.menuButton c = (RvsB_Menu.menuButton) e.getComponent();
		c.select();
	}

	public Font getSelectionFont() {
		return selectionFont;
	}

	public void setSelectionFont(Font selectionFont) {
		this.selectionFont = selectionFont;
	}
	
}
