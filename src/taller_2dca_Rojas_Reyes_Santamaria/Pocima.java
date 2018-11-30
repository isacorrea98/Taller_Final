package taller_2dca_Rojas_Reyes_Santamaria;

import processing.core.PConstants;

public class Pocima extends Elemento {

	public Pocima(Main app, float x, float y) {
		super(app, x, y);
		elemento = app.loadImage("veneno.png");
		elemento.resize(60, 80);
	}

	@Override
	public void pintar() {
		app.imageMode(PConstants.CENTER);
		app.image(elemento, x, y);
	}

}
