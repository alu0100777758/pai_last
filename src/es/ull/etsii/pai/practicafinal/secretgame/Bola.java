package es.ull.etsii.pai.practicafinal.secretgame;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Bola {

	public int x, y, width = 10, height = 10;   	// Posición, altura y anchura de la bola.
	private int movimientoX, movimientoY;			// MovimientoX y movimientoY
	private Random random;
	private int amountOfHits;
	
	
	/** Constructor */
	public Bola() {
		this.random = new Random();
	}

	public void update(Raqueta raqueta1, Raqueta raqueta2, int anchura, int altura) {
		int speed = 10;

		this.x += movimientoX * speed;
		this.y += movimientoY * speed;

		if (this.y + height - movimientoY > altura || this.y + movimientoY < 0) {
			if (this.movimientoY < 0) {
				this.y = 0;
				this.movimientoY = random.nextInt(4);

				if (movimientoY == 0) {
					movimientoY = 1;
				}
			} else {
				this.movimientoY = -random.nextInt(4);
				this.y = altura - height;

				if (movimientoY == 0) {
					movimientoY = -1;
				}
			}
		}

		if (checkCollision(raqueta1) == 1) {
			this.movimientoX = 1 + (amountOfHits / 5);
			this.movimientoY = -2 + random.nextInt(4);

			if (movimientoY == 0) {
				movimientoY = 1;
			}

			amountOfHits++;
		} else if (checkCollision(raqueta2) == 1) {
			this.movimientoX = -1 - (amountOfHits / 5);
			this.movimientoY = -2 + random.nextInt(4);

			if (movimientoY == 0) {
				movimientoY = 1;
			}
			amountOfHits++;
		}

		if (checkCollision(raqueta1) == 2) {
			System.out.println("punto");
			raqueta2.setScore(raqueta2.getScore() + 1);
			spawn(anchura, altura);
		} else if (checkCollision(raqueta2) == 2) {
			System.out.println("punto2");
			raqueta1.setScore(raqueta1.getScore()+1);
			spawn(anchura, altura);
		}
	}

	public void spawn(int anchura, int altura) {
		this.amountOfHits = 0;
		this.x = anchura / 2 - this.width / 2;
		this.y = altura / 2 - this.height / 2;

		this.movimientoY = -2 + random.nextInt(4);

		if (movimientoY == 0) {
			movimientoY = 1;
		}

		if (random.nextBoolean()) {
			movimientoX = 1;
		} else {
			movimientoX = -1;
		}
	}

	public int checkCollision(Raqueta raqueta) {
		if (this.x < raqueta.getX() + raqueta.getWidth() && this.x + width > raqueta.getX() && this.y < raqueta.getY() + raqueta.getHeight() && this.y + height > raqueta.getY()) {
			return 1; // bounce
		} else if ((raqueta.getX() > x && raqueta.getNumeroRaqueta() == 1) || (raqueta.getX() < x - width && raqueta.getNumeroRaqueta() == 2)) {
			return 2; // score
		}

		return 0; // nothing
	}

	public void dibujar(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(x, y, width, height);
	}

}
