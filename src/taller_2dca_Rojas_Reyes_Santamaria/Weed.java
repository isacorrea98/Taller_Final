package taller_2dca_Rojas_Reyes_Santamaria;

import processing.core.PConstants;

public class Weed extends Elemento {

	public Weed(Main app, float x, float y) {
		super(app,x,y);
		this.x = x;
		this.y = y;
		elemento = app.loadImage("weed.png");
	}

	@Override
	public void pintar() {
		app.imageMode(PConstants.CENTER);
		app.image(elemento, x, y);
	}
}
