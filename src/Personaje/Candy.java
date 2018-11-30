package Personaje;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;
import taller_2dca_Rojas_Reyes_Santamaria.Bala;
import taller_2dca_Rojas_Reyes_Santamaria.Logica;

public class Candy extends Personaje {
	private boolean der;
	private boolean izq;
	private boolean arriba;
	private boolean abajo;
	private boolean atacar;

	public Candy(Logica log, float x, float y) {
		super(log, x, y);
		vistas[0] = app.loadImage("enemigo.png");
		vistas[1] = app.loadImage("enemigo.png");
		vistas[2] = app.loadImage("enemigo.png");
		vistas[3] = app.loadImage("enemigo.png");
		drogado = app.loadImage("humo.png"); 
		hielo = app.loadImage("hielo.png");

	}

	@Override
	public void pintar() {
		app.imageMode(PConstants.CENTER);
		if(minimizado == false)
		app.image(vistas[vistaActual], pos.x, pos.y);
		if (drogrado)
		app.image(drogado, pos.x, pos.y);
		if (paralizado)
		app.image(hielo, pos.x, pos.y);
		
		app.noFill();
		// app.stroke(255);
		// app.ellipse(pos.x, pos.y, personaje.height, personaje.height);
		nivel(100, 20);
		objetivo();
		
		if(isAtacable() == false) {
			circulo = app.loadImage("circulo.png");
			app.imageMode(PConstants.CENTER);
			app.image(circulo, pos.x, pos.y);
		}
	}

	/*
	 * 
	 */
	@Override
	public void mover() {
		if (drogrado == false) {

			if (der) {
				vistaActual = 1;
				pos.x += vel.x;
			} else if (izq) {
				vistaActual = 2;
				pos.x -= vel.x;
			} else if (abajo) {
				vistaActual = 0;
				pos.y += vel.y;
			} else if (arriba) {
				vistaActual = 3;
				pos.y -= vel.y;
			}
		} else {

			if (der) {
				vistaActual = 1;
				pos.x -= vel.x;
			} else if (izq) {
				vistaActual = 3;
				pos.x += vel.x;
			} else if (abajo) {
				vistaActual = 0;
				pos.y -= vel.y;
			} else if (arriba) {
				vistaActual = 2;
				pos.y += vel.y;
			}

		}
	}

	public void keyPressed() {

		if (app.keyCode == app.RIGHT) {
			der = true;

		} else if (app.keyCode == app.LEFT) {
			izq = true;
		} else if (app.keyCode == app.DOWN) {
			abajo = true;
		} else if (app.keyCode == app.UP) {
			arriba = true;
		} else if (app.key == ' ' && atacar == false) {
			atacar = true;
			atacar();
		}

	}

	public void keyReleased() {
		if (app.keyCode == app.RIGHT) {
			der = false;

		} else if (app.keyCode == app.LEFT) {
			izq = false;
		} else if (app.keyCode == app.DOWN) {
			abajo = false;
		} else if (app.keyCode == app.UP) {
			arriba = false;
		} else if (app.key == ' ') {
			atacar = false;
		}

	}



	public void setVivo(boolean vivo) {
		this.vivo = vivo;

	}

	

}