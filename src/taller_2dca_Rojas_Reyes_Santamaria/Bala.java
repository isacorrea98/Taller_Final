package taller_2dca_Rojas_Reyes_Santamaria;

import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

public class Bala extends Thread {
	protected Main app;
	private PVector pos;
	private PVector vel;
	private boolean vivo;
	protected PImage bala;
	private PVector angulo;

	public Bala(Main app) {
		this.app = app;
		this.pos = new PVector();
		this.vel = new PVector(5, 5);
		this.angulo = new PVector(3, 10);
		bala = app.loadImage("bala.png");
		vivo = true;
		start();
	}

	public void pintar() {
		app.imageMode(PConstants.CENTER);
		app.image(bala, pos.x, pos.y);
	}

	public void mover() {
		pos.add(angulo);
	}

	public void movimientoBala(PVector p, PVector origen) {
		/*pos = new PVector(origen.x, origen.y);
		PVector dir = new PVector(p.x - origen.x, p.y - origen.y);
		angulo = dir.normalize();
		angulo.mult(15);*/
		pos = origen.copy();
		PVector dir = PVector.sub(p,pos);
		angulo = dir.normalize();
		angulo.mult(15);
	}

	public void run() {
		while (vivo) {
			try {
				mover();
				sleep(25);
			} catch (Exception e) {
				e.printStackTrace();
				vivo = false;
			}
		}
	}
	
	public PVector getPos() {
		return pos;
	}

}
