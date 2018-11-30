package taller_2dca_Rojas_Reyes_Santamaria;
import taller_2dca_Rojas_Reyes_Santamaria.LeapMotionS;
import taller_2dca_Rojas_Reyes_Santamaria.LeapMotionS.Gestos;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.plaf.SliderUI;

import Personaje.Candy;
import Personaje.Enemigo;
import Personaje.EnemigoFinal;
import Personaje.Infinity;
import Personaje.Saini;
import Personaje.Little;
import Personaje.Personaje;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PFont;
import processing.core.PImage;
import processing.core.PVector;

public class Logica implements Gestos{
	private Main app;
	static Logica log; // Esta variable permite crear un objeto de tipo logica para pasarselo como
						// parametro a otras clases
	private PImage fondo, puntaje, jugar, villanos, heroes, instrucciones, ganaste, perdiste, per1, per2, per3, per4,
			escenario2, escenario3, ganaste2, cursor;
	private ArrayList<Personaje> enemigos;
	private ArrayList<Elemento> elementos;
	private ArrayList<Personaje> jugadores;
	private int counter;
	private String pantallas;
	private PFont fuente;
	private boolean iniciarJuego;
	private Tiempo tiempo;
	static int EnemigosNormales = 40;
	static int Jefe = 20;
	private float movimiento;
	private PImage[] fondo3;
	private LeapMotionS leap;

	Sound sonido;

	public Logica(Main app) {
		this.app = app;
		log = this;
		 //en esta parte del codigo cargo todas las imagenes
		fondo = app.loadImage("ESCENARIO-fusion.jpg");
		jugar = app.loadImage("juego1.png");
		villanos = app.loadImage("juego2.png");
		heroes = app.loadImage("juego3.png");
		instrucciones = app.loadImage("instrucciones.png");
		ganaste = app.loadImage("ganarVillanos.png");
		ganaste2 = app.loadImage("ganarHeroes.png");
		perdiste = app.loadImage("ganarAutos.png");
		puntaje = app.loadImage("puntaje.png");
		per1 = app.loadImage("little.png");
		per2 = app.loadImage("candy.png");
		per3 = app.loadImage("infinity.png");
		per4 = app.loadImage("saini.png");
		escenario2 = app.loadImage("escenario.png");
		escenario3 = app.loadImage("escenario3.jpg");
		fuente = app.createFont("digital-7.ttf", 50);
		pantallas = "inicio";
		// cargo todos los sonidos
		sonido = new Sound(app);
		sonido.addSound("data/fondoGanar.mp3");
		sonido.addSound("data/fondoPerder.mp3");
		sonido.addSound("data/alarma.mp3");
		sonido.addMusica("data/fondoJuego.mp3");
		sonido.addMusica("data/fondoInicioEInstrucc.mp3");
		
		leap = new LeapMotionS(app);
		leap.setClassGestos(this);
		cursor = app.loadImage("click.png");
		fuente = app.createFont("digital-7.ttf", 50);
		pantallas = "inicio";
		// cargo todos los sonidos
		sonido = new Sound(app);
		sonido.addSound("data/fondoGanar.mp3");
		sonido.addSound("data/fondoPerder.mp3");
		sonido.addSound("data/alarma.mp3");
		sonido.addMusica("data/fondoJuego.mp3");
		sonido.addMusica("data/fondoInicioEInstrucc.mp3");

		jugadores = new ArrayList<>();
		tiempo = new Tiempo(0, 60);}
		
	/*	fondo3 = new PImage [10];
		for (int i = 0; i < fondo3.length; i++) {
			int mov = 180/10;
			float rot = (app.sin(app.degrees(mov))) * 0.02f;
			PImage fondo3
		}

	}*/

	private void startGame() {

		/*
		 * Se agregan los primeros enemigos que apareceran en pantalla por defecto.
		 */
		elementos = new ArrayList<Elemento>();
		enemigos = new ArrayList<>();

		for (int i = 0; i < 2; i++) {
			enemigos.add(new Enemigo(this, app.random(150, 1200), app.random(50, 650)));
		}
//se agrega el jefe final
		enemigos.add(new EnemigoFinal(this, app.random(150, 1200), app.random(50, 650)));
		/*
		 * Se agregan los primeros elementos que apareceran en pantalla por defecto.
		 */
		for (int i = 0; i < 2; i++) {
			elementos.add(new Chaleco(app, app.random(340, 1720), app.random(160, 1020)));
			elementos.add(new Granada(app, app.random(340, 1720), app.random(160, 1020)));
			elementos.add(new Botiquin(app, app.random(340, 1720), app.random(160, 1020)));
		}
	}

	/*
	 * En este metodo se pintan imagenes como el escenario del juego y la barra
	 * donde se presenta el nombre, nivel y el tiempo del jugadores.get(i). Además
	 * del jugadores.get(i), los enemigos y las balas.
	 */

	public void pintar() {
		leap.procesosEnEjecucion(10);
		switch (pantallas) {
		case "inicio":
			app.imageMode(PConstants.CORNER);
			app.image(jugar, 0, 0);
			app.image(cursor, leap.getHandX(), leap.getHandY());
			sonido.play("fondoInicioEInstrucc");

			break;

		case "villanos":
			app.imageMode(PConstants.CORNER);
			app.image(villanos, 0, 0);
			if (leap.getHandX() > 158 && leap.getHandX() < 460 && leap.getHandY() > 309 && leap.getHandY() < 783) {
				app.imageMode(PConstants.CORNER);
				app.image(per1, 158, 288);
			}
			if (leap.getHandX() > 523 && leap.getHandX() < 700 && leap.getHandY() > 309 && leap.getHandY() < 783) {
				app.imageMode(PConstants.CORNER);
				app.image(per2, 510, 288);
			}
	
			app.image(cursor, leap.getHandX(), leap.getHandY());
			break;

		case "heroes":
			app.imageMode(PConstants.CORNER);
			app.image(heroes, 0, 0);

			if (leap.getHandX() > 1118 && leap.getHandX() < 1400 && leap.getHandY() > 302 && leap.getHandY() < 789) {
				app.imageMode(PConstants.CORNER);
				app.image(per4, 1100, 288);
			}
			if (leap.getHandX() > 1450 && leap.getHandX() < 1800 && leap.getHandY() > 302 && leap.getHandY() < 789) {
				app.imageMode(PConstants.CORNER);
				app.image(per3, 1450, 288);
			}

			app.image(cursor, leap.getHandX(), leap.getHandY());
			break;

		case "instrucciones":
			app.imageMode(PConstants.CORNER);
			app.image(instrucciones, 0, 0);

			app.image(cursor, leap.getHandX(), leap.getHandY());
			
			break;
			
		case "juego":

			pantallaJuego();
			sonido.stop("fondoInicioEInstrucc");
			sonido.play("fondoJuego");
			break;

		case "ganaste":
			app.imageMode(PConstants.CORNER);
			app.image(ganaste, 0, 0);
			sonido.stop("fondoJuego");
			sonido.play("fondoGanar");

			break;

		case "perdiste":
			app.imageMode(PConstants.CORNER);
			app.image(perdiste, 0, 0);

			sonido.stop("fondoJuego");
			sonido.play("fondoPerder");
			break;

		case "ganaste2":
			app.imageMode(PConstants.CORNER);
			app.image(ganaste2, 0, 0);

			sonido.stop("fondoJuego");
			sonido.play("fondoGanar");

			break;

		}
		interaccionleap();
	}

	public PImage getPerdiste() {
		return perdiste;
	}

	public void setPerdiste(PImage perdiste) {
		this.perdiste = perdiste;
	}

	public String getPantallas() {
		return pantallas;
	}

	public void setPantallas(String pantallas) {
		this.pantallas = pantallas;
	}

// este metodo es encargado de cargar la pantalla del juego dependiendo de cada escenario, elementos, balas, etc.
	int black = 0;

	public void pantallaJuego() {
		app.imageMode(app.CORNER);
		if (tiempo.getSegundos() < 60 && tiempo.getSegundos() >= 40) {
			app.image(fondo, 0, 0);

			app.image(puntaje, 0, 0);
		} else if (tiempo.getSegundos() <= 39 && tiempo.getSegundos() > 20) {

			black++;
			if (black % 2 == 0) {

				app.image(escenario2, 0, 0);
			}
			app.imageMode(PConstants.CORNER);

			app.image(puntaje, 0, 0);
		} else {
			app.pushMatrix();
			app.imageMode(PConstants.CENTER);
			app.translate(app.width / 2, app.height / 2);
			float rot = (app.sin(app.degrees(movimiento))) * 0.02f;
			movimiento += 4;
			app.rotate(rot);

			app.image(escenario3, 0, 0);
			app.popMatrix();
			app.imageMode(PConstants.CORNER);

			app.image(puntaje, 0, 0);

		}

		for (int i = 0; i < jugadores.size(); i++) {
			if (jugadores.get(i) != null) {
				jugadores.get(i).pintar();
				jugadores.get(i).pintarBalas();
			}

		}

		app.textFont(fuente);
		app.fill(255);
		app.text(tiempo.getSegundos(), 1800, 60);
		tiempo.start();

		pintarElementos();
		if ((app.millis() / 1000) > 6)
			agregarElemento();
		if (tiempo.getSegundos() < EnemigosNormales) {
			for (int i = 0; i < enemigos.size(); i++) {
				if (enemigos.get(i) instanceof Enemigo) {
					enemigos.get(i).pintar();
					enemigos.get(i).start();
					enemigos.get(i).pintarBalas();

				}
			}
		}

		if (tiempo.getSegundos() < Jefe) {
			for (int i = 0; i < enemigos.size(); i++) {
				if (enemigos.get(i) instanceof EnemigoFinal) {
					enemigos.get(i).pintar();
					enemigos.get(i).start();
					enemigos.get(i).pintarBalas();

				}
			}
		}

		remover();
		calculos();

	}

//este metodo se encarga de realizar las validaciones correspondientes para saber si gano el bando de los enemigos, el bando
//	de los villanos o el bando de los automatas
	public void validacionFinal() {
		int nunHeroes = 0;
		int nunVillanos = 0;
		int nunEnemigos = 0;

		for (int i = 0; i < this.enemigos.size(); i++) {
			Personaje p = enemigos.get(i);
			if (p.getTamanoVida() > nunVillanos) {
				nunEnemigos = p.getTamanoVida();
			}
		}

		for (int i = 0; i < this.jugadores.size(); i++) {
			Personaje p = jugadores.get(i);
			if (p instanceof Little || p instanceof Candy) {
				nunVillanos = p.getTamanoVida();
			} else {
				nunHeroes = p.getTamanoVida();
			}
		}

		if (nunHeroes > nunVillanos && nunHeroes > nunEnemigos) {
			pantallas = "ganaste";
		}
		if (nunVillanos > nunHeroes && nunVillanos > nunEnemigos) {
			pantallas = "ganaste2";
		}
		if (nunEnemigos > nunHeroes && nunEnemigos > nunVillanos) {
			pantallas = "perdiste";
		}

	}

//este metodo se encarga de hacer todos los calculos del tiempo del juego. 
	public void calculos() {
		if (pantallas.equals("juego")) {

			if (enemigos.size() < 1 && tiempo.isTermino() == false) {
				if (jugadores.size() == 1) {
					Personaje p = jugadores.get(0);
					if (p instanceof Little || p instanceof Candy) {
						pantallas = "ganaste";
					} else {

						pantallas = "ganaste2";

					}

					for (int j = 0; j < enemigos.size(); j++) {
						enemigos.get(j).interrupt();
					}
					enemigos.clear();
					for (int i = 0; i < jugadores.size(); i++) {
						jugadores.get(i).interrupt();
						jugadores.clear();

					}
				}
			} else if (jugadores.size() == 0) {

				pantallas = "perdiste";

				for (int j = 0; j < enemigos.size(); j++) {
					enemigos.get(j).interrupt();
				}
				enemigos.clear();
				for (int i = 0; i < jugadores.size(); i++) {
					jugadores.get(i).interrupt();
					jugadores.clear();

				}
			}
			int contTemp = 0;
			for (int j = 0; j < enemigos.size(); j++) {
				contTemp += enemigos.get(j).getTamanoVida();
			}

			if (tiempo.isTermino()) {

				validacionFinal();

				for (int j = 0; j < enemigos.size(); j++) {
					enemigos.get(j).interrupt();
				}
				enemigos.clear();
				for (int i = 0; i < jugadores.size(); i++) {
					jugadores.get(i).interrupt();
				}
				jugadores.clear();

			}
		}
	}

	public void pintarElementos() {
		for (int i = 0; i < elementos.size(); i++) {
			elementos.get(i).pintar();
		}
	}

	/*
	 * Para remover del ArrayList de Elementos aquellos elementos para los que el
	 * boolean recogido sea true, para que dejen de existir y no se puedan recoger
	 * de nuevo.
	 */
	public void remover() {
		if (pantallas.equals("juego")) {
			for (int i = 0; i < elementos.size(); i++) {
				Elemento e = elementos.get(i);
				if (e.getRecogido()) {
					elementos.remove(e);
				}
			}

			for (int i = 0; i < enemigos.size(); i++) {
				Personaje e = enemigos.get(i);
				if (e.isVivo() == false) {

					enemigos.remove(e);
				}
			}

			for (int i = 0; i < jugadores.size(); i++) {
				Personaje e = jugadores.get(i);
				if (e.isVivo() == false) {

					jugadores.remove(e);
				}
			}
		}
	}

	/*
	 * Con este metodo se agregan mas elementos al ArrayList elementos de manera
	 * aletoria cada 5 segundos.
	 */
	public void agregarElemento() {
		if (pantallas.equals("juego")) {
			int time = app.millis() / 1000;
			int frecuencia = 3;

			if (time % frecuencia == 0 && counter == 0) {
				int tipo = 0;
				if (tiempo.getSegundos() < 60) {
					tipo = (int) app.random(0, 1);
				}
				if (tiempo.getSegundos() < EnemigosNormales) {
					tipo = (int) app.random(2, 3);
				}
				if (tiempo.getSegundos() < Jefe) {
					tipo = (int) app.random(4, 5);
				}
				/*
				 * Para que no se creen siempre los mismos elementos y asegurar que se creen
				 * siempre mas Botiquins, pues de estas depende el nivel y son mas importantes.
				 */
				switch (tipo) {
				case 0:
					elementos.add(new Chaleco(app, app.random(340, 1720), app.random(160, 1020)));
					elementos.add(new Chaleco(app, app.random(340, 1720), app.random(160, 1020)));
					elementos.add(new Granada(app, app.random(340, 1720), app.random(160, 1020)));
					elementos.add(new Botiquin(app, app.random(340, 1720), app.random(160, 1020)));
					elementos.add(new Botiquin(app, app.random(340, 1720), app.random(160, 1020)));

					break;

				case 1:
					elementos.add(new Chaleco(app, app.random(340, 1720), app.random(160, 1020)));
					elementos.add(new Granada(app, app.random(340, 1720), app.random(160, 1020)));
					elementos.add(new Botiquin(app, app.random(340, 1720), app.random(160, 1020)));
					elementos.add(new Botiquin(app, app.random(340, 1720), app.random(160, 1020)));

					break;

				case 2:
					elementos.add(new Chocolate(app, app.random(340, 1720), app.random(160, 1020)));
					elementos.add(new Chocolate(app, app.random(340, 1720), app.random(160, 1020)));
					elementos.add(new Pocima(app, app.random(340, 1720), app.random(160, 1020)));
					elementos.add(new Botiquin(app, app.random(340, 1720), app.random(160, 1020)));
					elementos.add(new Botiquin(app, app.random(340, 1720), app.random(160, 1020)));
					break;

				case 3:
					elementos.add(new Botiquin(app, app.random(340, 1720), app.random(160, 1020)));
					elementos.add(new Chocolate(app, app.random(340, 1720), app.random(160, 1020)));
					elementos.add(new Pocima(app, app.random(340, 1720), app.random(160, 1020)));
					elementos.add(new Pocima(app, app.random(340, 1720), app.random(160, 1020)));
					elementos.add(new Pocima(app, app.random(340, 1720), app.random(160, 1020)));
					elementos.add(new Botiquin(app, app.random(340, 1720), app.random(160, 1020)));
					break;

				case 4:
					elementos.add(new Weed(app, app.random(340, 1720), app.random(160, 1020)));
					elementos.add(new Weed(app, app.random(340, 1720), app.random(160, 1020)));
					elementos.add(new Paquete(app, app.random(340, 1720), app.random(160, 1020)));
					elementos.add(new Botiquin(app, app.random(340, 1720), app.random(160, 1020)));
					elementos.add(new Botiquin(app, app.random(340, 1720), app.random(160, 1020)));
					break;

				case 5:
					elementos.add(new Weed(app, app.random(340, 1720), app.random(160, 1020)));
					elementos.add(new Paquete(app, app.random(340, 1720), app.random(160, 1020)));
					elementos.add(new Paquete(app, app.random(340, 1720), app.random(160, 1020)));
					elementos.add(new Botiquin(app, app.random(340, 1720), app.random(160, 1020)));
					elementos.add(new Botiquin(app, app.random(340, 1720), app.random(160, 1020)));
					break;

				default:
					break;
				}

				counter = 1;

			} else if (counter >= 1 && counter < 80) {
				counter++;
			} else {
				counter = 0;
			}
		}
	}

	/*
	 * Aqui comienza el hilo del juagador y permite que este se mueva con la accion
	 * del click izquierdo. Haciendo un set a su posicion y conviertiendola en la
	 * posicion del mouse.
	 */

	public void resetJuego() {
		enemigos.clear();
		counter = 0;
		pantallas = "juego";
		iniciarJuego = false;
		tiempo = new Tiempo(0, 60);

		System.out.println("Reseteado");
	}

//con este metodo se puede pasar de pantalla a pantalla
	public void click() {
		System.out.println("mouseX:" + app.mouseX + "         mouseY:" + app.mouseY);

		if (pantallas.equals("inicio") && app.mouseX > 709 && app.mouseX < 1193 && app.mouseY > 784
				&& app.mouseY < 880) {
			pantallas = "villanos";
		}

		else if (pantallas.equals("villanos")) {

			if (app.mouseX > 158 && app.mouseX < 444 && app.mouseY > 309 && app.mouseY < 783) {
				jugadores.add(new Little(this, app.width / 2 + app.width / 4, app.height / 2));
				pantallas = "heroes";

			}
			if (app.mouseX > 523 && app.mouseX < 700 && app.mouseY > 309 && app.mouseY < 783) {
				jugadores.add(new Candy(this, app.width / 2 + app.width / 4, app.height / 2));
				pantallas = "heroes";

			}
		}

		else if (pantallas.equals("heroes")) {

			if (app.mouseX > 1118 && app.mouseX < 1390 && app.mouseY > 302 && app.mouseY < 789) {
				jugadores.add(new Saini(this, app.width / 4, app.height / 2));
				pantallas = "instrucciones";

			}
			if (app.mouseX > 1450 && app.mouseX < 1800 && app.mouseY > 302 && app.mouseY < 789) {
				jugadores.add(new Infinity(this, app.width / 4, app.height / 2));
				pantallas = "instrucciones";
			}
		}

		if (pantallas.equals("instrucciones") && app.mouseX > 1314 && app.mouseX < 1900 && app.mouseY > 882
				&& app.mouseY < 980) {
			startGame();
			pantallas = "juego";

			for (int i = 0; i < jugadores.size(); i++) {
				jugadores.get(i).start();
			}
		}

		if (pantallas.equals("ganaste") && app.mouseX > 1200 && app.mouseX < 1600 && app.mouseY > 800
				&& app.mouseY < 980) {
			pantallas = "juego";
			resetJuego();
		}

		if (pantallas.equals("perdiste") && app.mouseX > 1200 && app.mouseX < 1600 && app.mouseY > 800
				&& app.mouseY < 1000) {
			pantallas = "juego";
			resetJuego();
		}

	}

//este metodo evalua la distancia de la colision de la vala, la cual permite restar vidas. 
	public boolean colision(float x, float y) {
		for (int i = 0; i < jugadores.size(); i++) {
			if (PApplet.dist(x, y, jugadores.get(i).getPos().x, jugadores.get(i).getPos().y) < 20) {
				jugadores.get(i).restarVida();
				return true;
			}
		}
		return false;
	}

	public Main getApp() {
		return app;
	}

	public ArrayList<Elemento> getElementos() {
		return elementos;
	}

	public ArrayList<Personaje> getJugador() {
		return jugadores;
	}

	public ArrayList<Personaje> getEnemigos() {
		return enemigos;
	}

//este metodo permite el movimiento de los jugadores con el teclado 
	public void keyPressed() {
		for (int i = 0; i < jugadores.size(); i++) {
			if (jugadores.get(i) != null) {
				jugadores.get(i).keyPressed();

			}
		}
	}

	// este metodo permite el movimiento de los jugadores con el teclado
	public void keyReleased() {
		for (int i = 0; i < jugadores.size(); i++) {
			if (jugadores.get(i) != null) {

				jugadores.get(i).keyReleased();
			}
		}
	}

	public Tiempo getTiempo() {
		return tiempo;
	}

	public void setTiempo(Tiempo tiempo) {
		this.tiempo = tiempo;
	}

	

	public void interaccionleap() {
		if (pantallas.equals("inicio") && leap.getHandX() > 709 && leap.getHandX() < 1193 && leap.getHandY() > 784
				&& leap.getHandY() < 880) {
			System.out.println("puto el que lo lea");
			if(leap.getAgarro() == true) {
			pantallas = "villanos";
			System.out.println("puto el que lo lea");
		}
		}
		else if (pantallas.equals("villanos")) {

			if (leap.getHandX() > 158 && leap.getHandX() < 444 && leap.getHandY() > 309 && leap.getHandY() < 783) {
				jugadores.add(new Little(this, app.width / 2 + app.width / 4, app.height / 2));
				if(leap.getAgarro()==true) {
				pantallas = "heroes";
}
			}
			if (leap.getHandX() > 523 && leap.getHandX() < 700 && leap.getHandY() > 309 && leap.getHandY() < 783) {
				jugadores.add(new Candy(this, app.width / 2 + app.width / 4, app.height / 2));
				if(leap.getAgarro()==true) {
				pantallas = "heroes";

			}
			}
		}

		else if (pantallas.equals("heroes")) {

			if (leap.getHandX() > 1118 && leap.getHandX() < 1390 && leap.getHandY() > 302 && leap.getHandY() < 789) {
				jugadores.add(new Saini(this, app.width / 4, app.height / 2));
				if(leap.getAgarro()==true) {
				pantallas = "instrucciones";

			}
			}
			if (leap.getHandX() > 1450 && leap.getHandX() < 1800 && leap.getHandY() > 302 && leap.getHandY() < 789) {
				jugadores.add(new Infinity(this, app.width / 4, app.height / 2));
				if(leap.getAgarro()==true) {
				pantallas = "instrucciones";
			}
			}
	} 	if (pantallas.equals("instrucciones") && leap.getHandX() > 1314 && leap.getHandX() < 1900 && leap.getHandY() > 882
			&& leap.getHandY() < 980) {
		if (leap.getAgarro() == true) {
		startGame();
		pantallas = "juego";
		for (int i = 0; i < jugadores.size(); i++) {
			jugadores.get(i).start();
		}
	}
	}
	}
	

	@Override
	public void horizontal() {
		// TODO Auto-generated method stub


	}

	@Override
	public void enCirculo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void enCirculoDerecha() {
		// TODO Auto-generated method stub
	}

	@Override
	public void enCirculoIzquierda() {
		// TODO Auto-generated method stub
	}

	@Override
	public void abajo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void zigzag() {
		// TODO Auto-generated method stub
	}

	public LeapMotionS getLeap() {
		return leap;
	}



	
}
