import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class main {
	public static void main(String[] args) {
		ArrayList<Cromosoma> poblacion = new ArrayList<Cromosoma>();
		int numeroReinas = 9, tamPoblacion = 10, tamElitismo;
		// Numero de reinas = tamaño
		// Crear poblacion
		for (int i = 0; i < tamPoblacion; i++) {
			poblacion.add(new Cromosoma(numeroReinas));
			System.out.println(poblacion.get(i).toString());
		}
		// Elitismo de 20%
		tamElitismo = (int) ((0.2) * (poblacion.size()));
		seleccion(poblacion, tamElitismo);
	}

	public static void seleccion(ArrayList<Cromosoma> poblacion, int tamElitismo) {
		System.out.println("\nEvaluacion:\n");
		for (int i = 0; i < poblacion.size(); i++) {
			poblacion.get(i).evaluarAtaques();
			System.out.println(poblacion.get(i).toString());
		}

		int elite1 = 0, elite2 = 1;

		for (int i = 2; i < poblacion.size(); i++) {
			if (poblacion.get(elite1).getNumeroAtaques() > poblacion.get(i)
					.getNumeroAtaques()) {
				elite1 = i;
			} else if (poblacion.get(elite2).getNumeroAtaques() > poblacion
					.get(i).getNumeroAtaques()) {
				elite2 = i;
			}
		}
		poblacion.get(elite1).setElite(1);
		poblacion.get(elite2).setElite(1);
		System.out.println("Elites:\n" + poblacion.get(elite1).toString()
				+ "\n" + poblacion.get(elite2).toString());
	}

	public static ArrayList<Cromosoma> cruza(int numeroReinas, ArrayList<Cromosoma> poblacionCruza) {
		// Cruza
		// 80% de probabilidad de cruza
		// Puntos de cruza en 40%||30%||30%
		ArrayList<Cromosoma> poblacionFinal = new ArrayList<Cromosoma>();
		ArrayList<Reina> temp1 = new ArrayList<Reina>();
		ArrayList<Reina> temp2 = new ArrayList<Reina>();
		int punto1Cruza, punto2Cruza, band = 0, band2 = 0;
		// Probabilidad de cruza del 80%
		int numeroCruzas = (int) ((0.8) * (poblacionCruza.size()));
		Collections.shuffle(poblacionCruza);
		punto1Cruza = (int) ((0.4) * (numeroReinas));
		punto2Cruza = (int) ((0.6) * (numeroReinas));
		for (int i = 0; i < poblacionCruza.size(); i++) {
			System.out.println(poblacionCruza.get(i).toString());
		}
		for (int i = 0; i < numeroReinas; i++) {
			temp1.add(new Reina(-1, -1));
			temp2.add(new Reina(-1, -1));
		}
		for (int i = 0; i < poblacionCruza.size(); i = i + 2) {
			for (int j = punto1Cruza; j < punto2Cruza; j++) {
				temp1.set(j, poblacionCruza.get(i + 1).getElementos().get(j));
				temp2.set(j, poblacionCruza.get(i).getElementos().get(j));
			}
			for (int j = 0; j < punto1Cruza; j++) {
				for (int j2 = punto1Cruza; j2 < punto2Cruza; j2++) {
					// System.out.println(poblacionCruza.get(i).getElementos().get(j).getPosicionX()
					// + " != "+ temp1.get(j2).getPosicionX());
					// System.out.println(poblacionCruza.get(i).getElementos().get(j).getPosicionY()
					// + " != "+ temp1.get(j2).getPosicionY());
					if ((poblacionCruza.get(i).getElementos().get(j)
							.getPosicionX() != temp1.get(j2).getPosicionX())
							&& (poblacionCruza.get(i).getElementos().get(j)
									.getPosicionY() != temp1.get(j2)
									.getPosicionY())) {
						band++;
					}
					if ((poblacionCruza.get(i + 1).getElementos().get(j)
							.getPosicionX() != temp2.get(j2).getPosicionX())
							&& (poblacionCruza.get(i + 1).getElementos().get(j)
									.getPosicionY() != temp2.get(j2)
									.getPosicionY())) {
						band2++;
					}
				}
				if (band == (punto2Cruza - punto1Cruza)) {
					temp1.set(j, poblacionCruza.get(i).getElementos().get(j));
				}
				band = 0;
				if (band2 == (punto2Cruza - punto1Cruza)) {
					temp2.set(j, poblacionCruza.get(i + 1).getElementos()
							.get(j));
				}
				band2 = 0;
			}
			for (int j = punto2Cruza; j < numeroReinas; j++) {
				for (int j2 = punto1Cruza; j2 < punto2Cruza; j2++) {
					// System.out.println(poblacionCruza.get(i).getElementos().get(j).getPosicionX()
					// + " != "+ temp1.get(j2).getPosicionX());
					// System.out.println(poblacionCruza.get(i).getElementos().get(j).getPosicionY()
					// + " != "+ temp1.get(j2).getPosicionY());
					if ((poblacionCruza.get(i).getElementos().get(j)
							.getPosicionX() != temp1.get(j2).getPosicionX())
							&& (poblacionCruza.get(i).getElementos().get(j)
									.getPosicionY() != temp1.get(j2)
									.getPosicionY())) {
						band++;
					}
					if ((poblacionCruza.get(i + 1).getElementos().get(j)
							.getPosicionX() != temp2.get(j2).getPosicionX())
							&& (poblacionCruza.get(i + 1).getElementos().get(j)
									.getPosicionY() != temp2.get(j2)
									.getPosicionY())) {
						band2++;
					}
				}
				if (band == (punto2Cruza - punto1Cruza)) {
					temp1.set(j, poblacionCruza.get(i).getElementos().get(j));
				}
				band = 0;
				if (band2 == (punto2Cruza - punto1Cruza)) {
					temp2.set(j, poblacionCruza.get(i + 1).getElementos()
							.get(j));
				}
				band2 = 0;
			}

			System.out.println(" hijo1 " + temp1.toString());
			System.out.println(" hijo2 " + temp2.toString());
			System.out.println();

		}

		return poblacionFinal;
	}
	public static ArrayList<Cromosoma> Mutacion(ArrayList <Cromosoma> poblacionFinal, int numReinas){
		int numIteracion=1,aux,aux1,numMutacion,r1,r2,r3;
		
		numMutacion=(int)(poblacionFinal.size()*(.1));
		while (numIteracion<=numMutacion){
			Collections.shuffle(poblacionFinal);
			Random rand= new Random();
			r1=rand.nextInt(numReinas);
			r2=rand.nextInt(2);
			r3=rand.nextInt(numReinas);
			if(r3==r1){
				while(r3==r1){
					r3=rand.nextInt(numReinas);
				}
			}
				
			if(r2==1){
				aux=poblacionFinal.get(0).getElementos().get(r1).getPosicionX();
				aux1=poblacionFinal.get(0).getElementos().get(r3).getPosicionX();
				poblacionFinal.get(0).getElementos().get(r1).setPosicionX(aux1);
				poblacionFinal.get(0).getElementos().get(r3).setPosicionX(aux);
			}
			if(r2==2){
				aux=poblacionFinal.get(0).getElementos().get(r1).getPosicionY();
				aux1=poblacionFinal.get(0).getElementos().get(r3).getPosicionY();
				poblacionFinal.get(0).getElementos().get(r1).setPosicionY(aux1);
				poblacionFinal.get(0).getElementos().get(r3).setPosicionY(aux);
			}
		}
		return poblacionFinal;
	}
}
