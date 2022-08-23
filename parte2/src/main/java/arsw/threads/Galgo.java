package arsw.threads;

/**
 * Un galgo que puede correr en un carril
 * 
 * @author rlopez
 * 
 */
public class Galgo extends Thread {
	private int paso;
	private boolean pause;
	private Carril carril;
	RegistroLlegada regl;

	public Galgo(Carril carril, String name, RegistroLlegada reg) {
		super(name);
		this.carril = carril;
		paso = 0;
		this.regl=reg;
		this.pause=false;
	}

	public void corra() throws InterruptedException {
		while (paso < carril.size()) {
			if(!pause) {
				Thread.sleep(100);
				carril.setPasoOn(paso++);
				carril.displayPasos(paso);

				if (paso == carril.size()) {
					carril.finish();
					synchronized (regl) {
						int ubicacion = regl.getUltimaPosicionAlcanzada();
						regl.setUltimaPosicionAlcanzada(ubicacion + 1);
						System.out.println("El galgo " + this.getName() + " llego en la posicion " + ubicacion);
						if (ubicacion == 1) {
							regl.setGanador(this.getName());
						}

					}


				}
			}
			else {
				pause();

			}
		}
	}

    public void pause(){
		synchronized (MainCanodromo.getReg()){
			try{
				MainCanodromo.getReg().wait();

			}
			catch (InterruptedException e){
				e.printStackTrace();
			}
		}
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	@Override
	public void run() {
		
		try {
			corra();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
