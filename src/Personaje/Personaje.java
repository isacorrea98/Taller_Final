package Personaje;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.Timer;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;
import taller_2dca_Rojas_Reyes_Santamaria.Bala;
import taller_2dca_Rojas_Reyes_Santamaria.Chaleco;
import taller_2dca_Rojas_Reyes_Santamaria.Botiquin;
import taller_2dca_Rojas_Reyes_Santamaria.Chocolate;
import taller_2dca_Rojas_Reyes_Santamaria.Elemento;
import taller_2dca_Rojas_Reyes_Santamaria.Granada;
import taller_2dca_Rojas_Reyes_Santamaria.Logica;
import taller_2dca_Rojas_Reyes_Santamaria.Main;
import taller_2dca_Rojas_Reyes_Santamaria.Paquete;
import taller_2dca_Rojas_Reyes_Santamaria.Pocima;
import taller_2dca_Rojas_Reyes_Santamaria.Weed;

public abstract class Personaje extends Thread {
	protected Main app;
	protected boolean apuntando;
	protected Logica log;
	protected PVector pos; // PVector de la posicion de un objeto tipo Personaje, sea enemigo o jugador.
	protected PVector vel; // PVector de la velocidad de un objeto tipo Personaje, sea enemigo o jugador.
	protected PImage[] vistas;
	protected PImage[] vistasMin;
	protected PImage circulo;
	protected boolean vivo;
	protected boolean drogrado;
	protected boolean envenenado;
	private PImage blanco;
	protected PImage drogado;
	protected PImage hielo;
	protected boolean paralizado;
	protected int vistaActual;

	// se creo para cuando tienes chaleco no lo aaquen
	private boolean atacable;

	public boolean isVivo() {
		return vivo;
	}

	private ArrayList<Elemento> vida;
	protected ArrayList<Bala> balas;

	public Personaje(Logica log, float x, float y) {
		this.app = log.getApp();

		// cuando empieza el juego se puede atacar
		atacable = true;
		blanco = app.loadImage("tiro.png");
		this.log = log;
		this.pos = new PVector(x, y);
		this.vel = new PVector(5, 3);
		// this.vel = new PVector(1, 1);
		vistas = new PImage[4];
		vistasMin = new PImage[4];
		vivo = true;
		vida = new ArrayList<Elemento>();
		apuntando = false;

		for (int i = 0; i < 5; i++) {
			vida.add(new Botiquin(log.getApp(), 0, 0));
		}
		balas = new ArrayList<Bala>();

	}

	public abstract void pintar();

	public abstract void mover();

	int counter = 0;

	public void atacar() {
		int time = app.millis() / 1000;
		int frecuencia = 3;
		if (time % frecuencia == 0 && counter == 0) {
			int tipo = (int) app.random(0, 5);

			for (int i = 0; i < log.getEnemigos().size(); i++) {
				if (log.getEnemigos().get(i).getPos().dist(pos) < 200) {
					Bala b = new Bala(app);
					b.movimientoBala(
							new PVector(log.getEnemigos().get(i).getPos().x, log.getEnemigos().get(i).getPos().y), pos);
					balas.add(b);
				}
			}

			for (int i = 0; i < log.getJugador().size(); i++) {
				Personaje p = log.getJugador().get(i);
				if (p != this) {
					if (p.getPos().dist(pos) < 200) {
						Bala b = new Bala(app);
						b.movimientoBala(new PVector(p.getPos().x, p.getPos().y), pos);
						balas.add(b);
					}
				}
			}
			counter = 1;
		} 
	}

	public void apuntar() {
		if (counter >= 1 && counter < 10) {
			counter++;
		} else {
			counter = 0;

		}
		
		for (int i = 0; i < log.getEnemigos().size(); i++) {
			if (log.getEnemigos().get(i).getPos().dist(pos) < 200) {

				log.getEnemigos().get(i).apuntando = true;
			} else {
				log.getEnemigos().get(i).apuntando = false;
			}
		}

		for (int i = 0; i < log.getJugador().size(); i++) {
			Personaje p = log.getJugador().get(i);
			if (p != this) {
				if (p.getPos().dist(pos) < 200) {
					p.apuntando = true;
				} else {
					p.apuntando = false;
				}
			}

		}

	}

	public void objetivo() {
		if (apuntando) {
			app.imageMode(PConstants.CENTER);
			app.image(blanco, pos.x, pos.y);
		}

	}

	public void contacto() {
		for (int i = 0; i < balas.size(); i++) {
			PVector copi = balas.get(i).getPos().copy();
			for (int j = 0; j < log.getJugador().size(); j++) {
				if (log.getJugador().get(j) != this && log.getJugador().get(j).isAtacable()) {
					if (PApplet.dist(copi.x, copi.y, log.getJugador().get(j).getPos().x,
							log.getJugador().get(j).getPos().y) < 20) {
						balas.remove(i);
						log.getJugador().get(j).restarVida();
						return;
					}
				}
			}

			for (int j = 0; j < log.getEnemigos().size(); j++) {
				if (log.getEnemigos().get(j) != this) {
					if ((PApplet.dist(copi.x, copi.y, log.getEnemigos().get(j).getPos().x,
							log.getEnemigos().get(j).getPos().y) < 40)
							&& log.getEnemigos().get(j).isAtacable() != false) {
						balas.remove(i);
						log.getEnemigos().get(j).restarVida();

						return;
					}
				}
			}

		}
	}

	public void pintarBalas() {
		for (int i = 0; i < balas.size(); i++) {
			if (balas.get(i) != null) {
				PVector copi = balas.get(i).getPos().copy();
				balas.get(i).pintar();
			}
		}
	}

	public void run() {
		while (vivo) {
			try {
				mover();
				apuntar();
				contacto();
				recoger();
				sleep(25);

			} catch (Exception e) {
				e.printStackTrace();
				vivo = false;

			}
		}
	}

	public void moverEnemigo(float x, float y) {

		if (pos.x < x) {
			pos.x += PApplet.abs(vel.x);
		}
		if (pos.y < y) {
			pos.y += PApplet.abs(vel.y);
		}
		if (pos.x > x) {
			pos.x -= PApplet.abs(vel.x);
		}
		if (pos.y > y) {
			pos.y -= PApplet.abs(vel.y);
		}

		if (pos.x - vistas[vistaActual].width / 2 <= 340 || pos.x + vistas[vistaActual].width / 2 >= app.width - 340) {
			vel.x *= -1;
			pos.x += vel.x * 2;
		}
		if (pos.y - vistas[vistaActual].height / 2 <= 160
				|| pos.y + vistas[vistaActual].height / 2 >= app.height - 30) {
			vel.y *= -1;
			pos.y += vel.y * 2;
		}

	}

	public void recoger() {
		if (log.getElementos() != null) {
			for (int i = 0; i < log.getElementos().size(); i++) {
				Elemento e = log.getElementos().get(i);
				if (validar(e)) {
					e.setRecogido(true);

					if (e instanceof Chocolate) {
						vel.x += 3;
						vel.y += 3;
						System.out.println("Chocolate");
					}
					if (e instanceof Granada) {

						try {
							paralizado = true;
							Thread.sleep(3000);
							paralizado = false;
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						System.out.println("Granada");

					}

					if (e instanceof Chaleco) {

						// si cogio el chaleco es inmune por 3 segundos
						atacable = false;
						// el efecto dura 3 segundos, el timer cuenta los 3 segundos y despues vuelve
						// atacable el personaje nuevamente
						Timer timer = new Timer(3000, new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent arg0) {
								atacable = true;

							}
						});
						timer.start();

					}
					if (e instanceof Paquete) {
						if (vida.size() < 10) {
							vida.add((Paquete) e);

						}

					}
					if (e instanceof Weed) {
						drogrado = !drogrado;
					}

					if (e instanceof Botiquin) {
						if (vida.size() < 10)
							vida.add((Botiquin) e);
					}

					if (e instanceof Pocima) {
						envenenado = !envenenado;
						if (envenenado) {
							encojer();
						} else {
							imgNormal();
						}
					}
				}
			}
		}
	}

	/*
	 * Este metodo se encarga de validar la distancia entre un personaje y un
	 * elemento recogible, para validar si fue recogido o no.
	 */

	public boolean validar(Elemento elementos) {
		return PApplet.dist(pos.x, pos.y, elementos.getX(), elementos.getY()) < 85;
	}

	/*
	 * Se pintan las barras de nivel para los personajes, dependiendo del numero de
	 * vida recogidas por cada uno.
	 */
	public void nivel(float x, float y) {
		int tamano = 30;
		app.fill(255);
		app.rectMode(PConstants.CORNER);

		/*
		 * NIVEL JUGADOR: Estas barras se pintan en el extremo superior izquierdo. El
		 * maximo de nivel es 10.
		 */

		/*
		 * NIVEL ENEMIGO: Funciona de la misma manera que el nivel del jugador, pero se
		 * pinta bajo cada enemigo.
		 */
		if (this instanceof Little || this instanceof Candy) {
			for (int i = 0; i < vida.size(); i++) {
				app.rect(x + 30 + (i * 22), y + 10, tamano / 3, tamano, 2);

			}

		} else if (this instanceof Saini || this instanceof Infinity) {
			for (int i = 0; i < vida.size(); i++) {
				app.rect(x + 800 + (i * 22), y + 10, tamano / 3, tamano, 2);

			}

		} else {
			app.rectMode(PConstants.CENTER);
			for (int i = 0; i < vida.size(); i++) {
				app.rect(x + (i * 22) - ((vida.size() - 1) * tamano / 3), y + (vistas[vistaActual].height / 2) + 20,
						tamano / 3, tamano, 2);

			}

		}
	}

	public void restarVida() {
		if (vida.size() > 0)
			vida.remove(0);
		if (vida.size() == 0) {
			vivo = false;
		}

		// app.filter(app.INVERT);
	}

	boolean ini = false;

	@Override
	public synchronized void start() {
		if (ini == false) {
			super.start();
			ini = true;
		}
	}

	public void keyPressed() {
	}

	public void keyReleased() {
	}

	public int getNivel() {
		return vida.size();
	}

	public PVector getPos() {
		return pos;
	}

	public PVector getVel() {
		return vel;
	}

	public int getTamanoVida() {
		return vida.size();
	}

	public boolean isAtacable() {
		return atacable;
	}

	public void setAtacable(boolean atacable) {
		this.atacable = atacable;
	}

	public boolean minimizado = false;

	public void encojer() {
		minimizado = true;
		for (int i = 0; i < vistas.length; i++) {
			PImage img = vistas[i].copy();
			vistasMin[i] = img;
		}

		for (int i = 0; i < vistas.length; i++) {
			int x = vistas[i].width / 2;
			int y = vistas[i].height / 2;
			vistas[i].resize(x, y);
		}
		minimizado = false;
	}

	public void imgNormal() {
		for (int i = 0; i < vistas.length; i++) {
			vistas[i] = vistasMin[i].copy();
		}
	}
}
