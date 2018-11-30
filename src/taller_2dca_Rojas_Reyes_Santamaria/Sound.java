package taller_2dca_Rojas_Reyes_Santamaria;

import java.util.ArrayList;

import ddf.minim.AudioPlayer;
import ddf.minim.AudioSample;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Sound {

	static private Minim minim;
	static private PApplet app;
	static private ArrayList<TipoSound> musicas;

	public Sound(PApplet app) {
		this.app = app;
		minim = new Minim(app);
		musicas = new ArrayList<>();
	}

	static public void addMusica(String musica) {
		AudioPlayer audio = minim.loadFile(musica);
		TipoSound sound = new TipoSound(TipoSound.MUSICA, musica, minim);
		musicas.add(sound);
	}
	
	static public void addMusica(String musica, String name) {
		AudioPlayer audio = audio = minim.loadFile(musica);
		TipoSound sound = new TipoSound(TipoSound.MUSICA, musica, minim, name);
		musicas.add(sound);
	}

	static public void addSound(String sonido) {
		AudioSample audio = minim.loadSample(sonido);
		TipoSound sound = new TipoSound(TipoSound.SONIDO, sonido, minim);
		musicas.add(sound);
	}
	
	static public void addSound(String sonido, String name) {
		AudioSample audio = minim.loadSample(sonido);
		TipoSound sound = new TipoSound(TipoSound.SONIDO, sonido, minim, name);
		musicas.add(sound);
	}

	static public void play(String name) {
		for (int i = 0; i < musicas.size(); i++) {
			TipoSound t = musicas.get(i);
			if(name.equals(t.getNombre())) {
				if(t.getTipo().equals(TipoSound.MUSICA)){
					if(t.getMusica().isPlaying() == false) {
						t.getMusica().play();
					}
				}else if(t.getTipo().equals(TipoSound.SONIDO)){
					t.getSonido().trigger();	
				}
				return;
			}
		}
	}
	
	static public void playContinuo(String name) {
		for (int i = 0; i < musicas.size(); i++) {
			TipoSound t = musicas.get(i);
			if(name.equals(t.getNombre())) {
				if(t.getTipo().equals(TipoSound.MUSICA)){
					if(t.getMusica().isPlaying()) {
						
					}else {
						t.getMusica().loop();
						t.getMusica().play();
					}
					
				}else if(t.getTipo().equals(TipoSound.SONIDO)){
					t.getSonido().trigger();	
				}
				return;
			}
		}
	}
	
	static public void stop(String name) {
		for (int i = 0; i < musicas.size(); i++) {
			TipoSound t = musicas.get(i);
			if(name.equals(t.getNombre())) {
				if(t.getTipo().equals(TipoSound.MUSICA)){
					t.getMusica().pause();
				}else if(t.getTipo().equals(TipoSound.SONIDO)){
					t.getSonido().stop();	
				}
				return;
			}
		}
	}
	
	static public void reset(String name) {
		for (int i = 0; i < musicas.size(); i++) {
			TipoSound t = musicas.get(i);
			if(name.equals(t.getNombre())) {
				if(t.getTipo().equals(TipoSound.MUSICA)){
					t.getMusica().pause();
					t.getMusica().rewind();
				}else if(t.getTipo().equals(TipoSound.SONIDO)){
					t.getSonido().stop();	
				}
				return;
			}
		}
	}
	
	static public void mute(String name) {
		for (int i = 0; i < musicas.size(); i++) {
			TipoSound t = musicas.get(i);
			if(name.equals(t.getNombre())) {
				if(t.getTipo().equals(TipoSound.MUSICA)){
					t.getMusica().mute();
				}else if(t.getTipo().equals(TipoSound.SONIDO)){
					t.getSonido().mute();
				}
				return;
			}
		}
	}
	
	static public void noMute(String name) {
		for (int i = 0; i < musicas.size(); i++) {
			TipoSound t = musicas.get(i);
			if(name.equals(t.getNombre())) {
				if(t.getTipo().equals(TipoSound.MUSICA)){
					t.getMusica().unmute();
				}else if(t.getTipo().equals(TipoSound.SONIDO)){
					t.getSonido().unmute();
				}
				return;
			}
		}
	}

}

class TipoSound {

	static String MUSICA = "musica";
	static String SONIDO = "sonido";
	String nombre;
	String tipo;
	AudioPlayer musica;
	AudioSample sonido;
	static String CARPETA = "data/";

	public TipoSound(String tipo, String musica, Minim min) {
		this.tipo = tipo;
		
		int pos = musica.indexOf(".mp3");
		if (pos != -1) {
			String subUrl = musica.substring(CARPETA.length(), pos);
			this.nombre = subUrl;
		} else if (musica.indexOf(".wav") != -1) {
			pos = musica.indexOf(".wav");
			String subUrl = musica.substring(CARPETA.length(), pos);
			this.nombre = subUrl;
		}
		
		if(tipo.equals(MUSICA)) {
			this.musica = min.loadFile(musica);
		}else if(tipo.equals(SONIDO)) {
			this.sonido = min.loadSample(musica);
		}else {
			System.out.println("Ingrese un tipo valido de archivo");
		}
	}
	
	public TipoSound(String tipo, String musica, Minim min, String name) {
		this.tipo = tipo;
		this.nombre = name;
		
		if(tipo.equals(MUSICA)) {
			this.musica = min.loadFile(musica);
		}else if(tipo.equals(SONIDO)) {
			this.sonido = min.loadSample(musica);
		}else {
			System.out.println("Ingrese un tipo valido de archivo");
		}
	}

	
	
	public String getTipo() {
		return tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public AudioPlayer getMusica() {
		return musica;
	}

	public AudioSample getSonido() {
		return sonido;
	}
	
	

}
