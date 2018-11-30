package Personaje;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;
import taller_2dca_Rojas_Reyes_Santamaria.Bala;
import taller_2dca_Rojas_Reyes_Santamaria.Logica;

public class Saini extends Personaje {
	private boolean der;
	private boolean izq;
	private boolean arriba;
	private boolean abajo;
	private boolean atacar;

	public Saini(Logica log, float x, float y) {
		super(log, x, y);
		vistas[0] = app.loadImage("saini1.png");
		vistas[1] = app.loadImage("sainider.png");
		vistas[2] = app.loadImage("saini-atras.png");
		vistas[3] = app.loadImage("saini-izq.png");
		drogado = app.loadImage("humo.png"); 
		hielo = app.loadImage("hielo.png");

	}

	@Override
	public void pintar() {

		app.imageMode(PConstants.CENTER);
		if (minimizado == false)
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

		if (isAtacable() == false) {
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
				vistaActual = 3;
				pos.x += vel.x;
			} else if (izq) {
				vistaActual = 1;
				pos.x -= vel.x;
			} else if (abajo) {
				vistaActual = 0;
				pos.y += vel.y;
			} else if (arriba) {
				vistaActual = 2;
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

		if (app.key == 'd' || app.key == 'D') {
			der = true;

		} else if (app.key == 'a' || app.key == 'A') {
			izq = true;
		} else if (app.key == 's' || app.key == 'S') {
			abajo = true;
		} else if (app.key == 'w' || app.key == 'W') {
			arriba = true;
		} else if (app.key == 't' || app.key == 'T' && atacar == false) {
			atacar = true;
			atacar();
		}

	}

	public void keyReleased() {
		if (app.key == 'd' || app.key == 'D') {
			der = false;

		} else if (app.key == 'a' || app.key == 'A') {
			izq = false;
		} else if (app.key == 's' || app.key == 'S') {
			abajo = false;
		} else if (app.key == 'w' || app.key == 'W') {
			arriba = false;
		} else if (app.key == 't' || app.key == 'T') {
			atacar = false;

		}

	}

	public void setVivo(boolean vivo) {
		this.vivo = vivo;

	}

}