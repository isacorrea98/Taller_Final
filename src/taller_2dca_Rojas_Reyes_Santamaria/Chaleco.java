package taller_2dca_Rojas_Reyes_Santamaria;

import processing.core.PConstants;

public class Chaleco extends Elemento {

	public Chaleco(Main app, float x, float y) {
		super(app, x, y);
		this.x = x;
		this.y = y;

		elemento = app.loadImage("chaleco.png");
	}

	@Override
	public void pintar() {
		app.imageMode(PConstants.CENTER);
		app.image(elemento, x, y);
	}

}
