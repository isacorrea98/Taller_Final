package Personaje;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;
import taller_2dca_Rojas_Reyes_Santamaria.Bala;
import taller_2dca_Rojas_Reyes_Santamaria.Botiquin;
import taller_2dca_Rojas_Reyes_Santamaria.Elemento;
import taller_2dca_Rojas_Reyes_Santamaria.Logica;

public class EnemigoFinal extends Personaje {

	private Botiquin botiquin;

	public EnemigoFinal(Logica log, float x, float y) {
		super(log, x, y);
		vistas[0] = app.loadImage("liberty.png");
		vistas[1] = app.loadImage("liberty.png");
		vistas[2] = app.loadImage("liberty.png");
		vistas[3] = app.loadImage("liberty.png");
		drogado = app.loadImage("humo.png"); 
		hielo = app.loadImage("hielo.png");
		this.vel = new PVector(6, 7);
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
		app.stroke(255);

		objetivo();

		if (botiquin != null) {
			app.stroke(255);
			app.strokeWeight(5);
			// app.line(pos.x, pos.y, pistola.getX(), pistola.getY());
			app.noStroke();
		}

		if (isAtacable() == false) {
			circulo = app.loadImage("circulo.png");
			app.imageMode(PConstants.CENTER);
			app.image(circulo, pos.x, pos.y);
		}
		nivel(pos.x, pos.y);
	}

	@Override
	public void mover() {
		if (botiquin == null && pos != null && vel != null && vistas[vistaActual] != null) {
			
			if(pos.x +vel.x +(vistas[vistaActual].width/2)> app.width - 340 ) {
				vel.x = -app.abs(vel.x);	
			}
			if(pos.x -vel.x -(vistas[vistaActual].width/2)< 340 ) {
				vel.x = app.abs(vel.x);	
			}
			if(pos.y + vel.y + (vistas[vistaActual].height/2)> app.height - 160 ) {
				vel.y = -app.abs(vel.y);	
			}
			if(pos.y -vel.y -(vistas[vistaActual].height/2)< 160 ) {
				vel.y = app.abs(vel.y);	
			}
			pos.add(vel);
			
			if (log.getElementos() != null) {
				for (int i = 0; i < log.getElementos().size(); i++) {
					Elemento e = log.getElementos().get(i);
					if (e instanceof Botiquin) {
						Botiquin p = (Botiquin) e;
						if (p.isPerseguida() == false) {
							botiquin = p;
							p.setPerseguida(true);
							return;
						}

					}
				}
			}

		} else if (botiquin != null) {
			moverEnemigo(botiquin.getX(), botiquin.getY());
			if (PApplet.dist(pos.x, pos.y, botiquin.getX(), botiquin.getY()) < 30
					|| log.getElementos().indexOf(botiquin) == -1) {
				botiquin = null;
			}
		}

	}

	int counter = 0;

	@Override
	public void atacar() {
		int time = app.millis() / 1000;
		int frecuencia = 3;

		// System.out.println(time);

		if (time % frecuencia == 0 && counter == 0) {
			int tipo = (int) app.random(0, 5);
			for (int i = 0; i < log.getJugador().size(); i++) {
				if (pos.dist(log.getJugador().get(i).getPos()) < 400
						&& log.getJugador().get(i).getNivel() <= getNivel()) {
					Bala b = new Bala(app);
					PVector destino = new PVector(log.getJugador().get(i).getPos().x,
							log.getJugador().get(i).getPos().y);
					b.movimientoBala(destino, pos);
					balas.add(b);
				}
			}
			counter = 1;
		} else if (counter >= 1 && counter < 80) {
			counter++;
		} else {
			counter = 0;
		}
	}

	public void run() {
		while (vivo) {
			try {
				mover();
				recoger();
				contacto();
				atacar();
				sleep(25);
			} catch (Exception e) {
				e.printStackTrace();
				vivo = false;
			}
		}
	}

}
