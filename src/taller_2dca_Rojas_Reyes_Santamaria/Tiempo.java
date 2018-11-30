package taller_2dca_Rojas_Reyes_Santamaria;

public class Tiempo extends Thread {

	private boolean termino;
	private boolean starTime;
	private int minutos;
	private int segundos;


	public boolean isStarTime() {
		return starTime;
	}

	public void setStarTime(boolean starTime) {
		this.starTime = starTime;
	}

	public int getMinutos() {
		return minutos;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}

	public int getSegundos() {
		return segundos;
	}

	public void setSegundos(int segundos) {
		this.segundos = segundos;
	}

	public Tiempo(int minutos, int segundos) {
		this.minutos = minutos;
		this.segundos = segundos;
	}

	public boolean isTermino() {
		return termino;
	}

	public void setTermino(boolean termino) {
		this.termino = termino;
	}

	@Override
	public void run() {
		while (termino == false) {
			try {
				segundos--;
				if (segundos < 0) {
					segundos = 59;
					minutos -= 1;
					if (minutos <= -1) {
						termino = true;
					}
				}

				

				Thread.sleep(1000);

			} catch (InterruptedException e) {

			}
		}
	}


	@Override
	public synchronized void start() {
		if (starTime == false) {
			super.start();
			starTime = true;
		}

	}
}
