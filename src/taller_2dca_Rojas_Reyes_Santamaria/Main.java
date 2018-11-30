package taller_2dca_Rojas_Reyes_Santamaria;

import processing.core.PApplet;
import de.voidplus.leapmotion.CircleGesture;
import de.voidplus.leapmotion.KeyTapGesture;
import de.voidplus.leapmotion.ScreenTapGesture;
import de.voidplus.leapmotion.SwipeGesture;

public class Main extends PApplet {
	private Logica logica;
	

	public static void main(String[] args) {
		PApplet.main("taller_2dca_Rojas_Reyes_Santamaria.Main");
	}

	public void settings() {
		size(1920, 1080);
		//fullScreen();
	}

	public void setup() {
		logica = new Logica(this);
	}

	public void draw() {
		background(45);
		logica.pintar();
	}

	public void keyPressed() {
		logica.keyPressed();
	}

	public void keyReleased() {
		logica.keyReleased();
	}
	
	public void mousePressed() {
		logica.click();
	}

	public void mouseDragged() {

	}

	public void mouseReleased() {
	}


	public void leapOnSwipeGesture(SwipeGesture g, int state) {
		logica.getLeap().leapOnSwipeGesture(g, state);
	}

	public void leapOnCircleGesture(CircleGesture g, int state) {
		logica.getLeap().leapOnCircleGesture(g, state);

	}

	public void leapOnScreenTapGesture(ScreenTapGesture g) {
		logica.getLeap().leapOnScreenTapGesture(g);
	}

	public void leapOnKeyTapGesture(KeyTapGesture g) {
		logica.getLeap().leapOnKeyTapGesture(g);
	}
	
}
