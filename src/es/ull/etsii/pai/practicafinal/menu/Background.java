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

	private BufferedImage img;

    public Background(String s){
        try{
        	img = ImageIO.read(this.getClass().getResource(s));
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    public void paint(Graphics g){
        ImageIcon imagenFondo = new ImageIcon(getClass().getResource("Consola-Arcade.gif"));
        super.paintComponent(g);
//        g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), null);
        g.drawImage(imagenFondo.getImage(),0,0, 500, 500, null);       
        setOpaque(false);
//        super.paintComponent(g);
}

}