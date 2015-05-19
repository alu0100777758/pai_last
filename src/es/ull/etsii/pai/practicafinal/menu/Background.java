package es.ull.etsii.pai.practicafinal.menu;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Background extends JPanel{

	public Background(String s){ }

    public void paint(Graphics g){
        ImageIcon imagenFondo = new ImageIcon(getClass().getResource("Consola-Arcade.gif"));
        super.paintComponent(g);
        g.drawImage(imagenFondo.getImage(),0,0, 500, 500, null);       
//        setOpaque(false);
}

}