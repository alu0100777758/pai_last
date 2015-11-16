package es.ull.etsii.pai.practicafinal.graphics;
/**
 * Progamacion de aplicaciones interactivas.
 * Universidad de La Laguna.
 * 
 * @author Sabato Ceruso sab7093@gmail.com
 * @author Javier Martin Hernandez alu0100777758@ull.edu.es
 */
import es.ull.etsii.pai.common.CircularArrayList;

public class AnimatedGraphicRectangle extends GraphicRectangle {
	private static final long serialVersionUID = -704387007684984716L;
	private CircularArrayList<String> texturePath = new CircularArrayList<String>();
	private int maxDelay = 1;
	private int delay = 1;
	public AnimatedGraphicRectangle(int x1, int y1, int width, int height) {
		super(x1, y1, width, height);
		texturePath.add("");
	}
	@Override
	public String getTexturePath() {
		return this.texturePath.get();
	};
	@Override
	public void setTexturePath(String texturePath) {
		this.texturePath.set(texturePath);
	}
	public void addTexturePath(String texturePath){
		this.texturePath.add(texturePath);
	}
	@Override
	public void paint(java.awt.Graphics g) {
		if(changePict())
			texturePath.forward();
		super.paint(g);
	};
	public boolean changePict(){
		setDelay(getDelay()-1);
		if(getDelay()==0){
			setDelay(getMaxDelay());
			return true;
		}
		return false;
	}
	public int getMaxDelay() {
		return maxDelay;
	}
	public void setMaxDelay(int maxDelay) {
		this.maxDelay = maxDelay;
	}
	public int getDelay() {
		return delay;
	}
	public void setDelay(int delay) {
		this.delay = delay;
	}
	
	
}
