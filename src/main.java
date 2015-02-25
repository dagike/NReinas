import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class main {
	public static void main(String[] args) {
		ArrayList<Cromosoma> poblacion = new ArrayList<Cromosoma>();
		int numeroReinas = 9, tamPoblacion = 10, tamElitismo, generacion = 0;
		// Numero de reinas = tamaño
		// Crear poblacion
		for (int i = 0; i < tamPoblacion; i++) {
			poblacion.add(new Cromosoma(numeroReinas));
			System.out.println(poblacion.get(i).toString());
		}
		// Elitismo de 20%
		while(generacion<5){
			tamElitismo = (int) ((0.2) * (poblacion.size()));
			seleccion(poblacion, tamElitismo);
			for (int i = 0; i < tamElitismo; i++) {
				if(poblacion.get(i).getElite() == 1){
					System.out.println("Elite\n" + poblacion.get(i).toString());
				}
			}
			System.out.println("\nCRUZA\n");
			poblacion = cruza(numeroReinas, poblacion);
			System.out.println("\nMUTACION\n");
			poblacion = Mutacion(poblacion, numeroReinas);
			System.out.println("\nNUEVA GENERACION\n");
			generacion++;
		}
	}

	public static void seleccion(ArrayList<Cromosoma> poblacion, int tamElitismo) {
		System.out.println("\nEvaluacion:\n");
		for (int i = 0; i < poblacion.size(); i++) {
			poblacion.get(i).evaluarAtaques();
			System.out.println(poblacion.get(i).toString());
		}
		Collections.sort(poblacion, new Comparator<Cromosoma>() {
	        @Override
	        public int compare(Cromosoma  cromosoma1, Cromosoma  cromosoma2)
	        {

	            return  Integer.valueOf(cromosoma1.getNumeroAtaques()).compareTo(cromosoma2.getNumeroAtaques());
	        }
	    });
		
		for (int i = 0; i < tamElitismo; i++) {
			poblacion.get(i).setElite(1);
		}
	}

	public static ArrayList<Cromosoma> cruza(int numeroReinas,
			ArrayList<Cromosoma> poblacionCruza) {
		// Cruza
		// 80% de probabilidad de cruza
		// Puntos de cruza en 40%||30%||30%
		ArrayList<Reina> temp1 = new ArrayList<Reina>();
		ArrayList<Reina> temp2 = new ArrayList<Reina>();

		ArrayList<Integer> filas_temp1 = new ArrayList<Integer>();
		ArrayList<Integer> columnas_temp1 = new ArrayList<Integer>();
		ArrayList<Integer> filas_temp2 = new ArrayList<Integer>();
		ArrayList<Integer> columnas_temp2 = new ArrayList<Integer>();
		int punto1Cruza, punto2Cruza, band = 0, band2 = 0, band3 = 0, band4 = 0, contFilas = 0, contColumnas = 0;
		// Probabilidad de cruza del 80%
		int numeroCruzas = (int) ((0.8) * (poblacionCruza.size()));
		Collections.shuffle(poblacionCruza);
		punto1Cruza = (int) ((0.4) * (numeroReinas));
		punto2Cruza = (int) ((0.6) * (numeroReinas));
		// for (int i = 0; i < poblacionCruza.size(); i++) {
		// System.out.println(poblacionCruza.get(i).toString());
		// }
		for (int i = 0; i < numeroCruzas; i = i + 2) {
			for (int i2 = 0; i2 < numeroReinas; i2++) {
				temp1.add(new Reina(-1, -1));
				temp2.add(new Reina(-1, -1));
			}
			for (int j = punto1Cruza; j < punto2Cruza; j++) {
				temp1.set(j, poblacionCruza.get(i + 1).getElementos().get(j));
				temp2.set(j, poblacionCruza.get(i).getElementos().get(j));
			}
			for (int j = 0; j < punto1Cruza; j++) {
				for (int j2 = punto1Cruza; j2 < punto2Cruza; j2++) {
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
			for (int j = 0; j < numeroReinas; j++) {
				for (int j2 = 0; j2 < numeroReinas; j2++) {
					if (j == temp1.get(j2).getPosicionX()) {
						band3 = 1;
					}
					if (j == temp1.get(j2).getPosicionY()) {
						band4 = 1;
					}
				}
				if (band3 == 0) {
					filas_temp1.add(j);
				}
				if (band4 == 0) {
					columnas_temp1.add(j);
				}
				band3 = 0;
				band4 = 0;
			}
			for (int j = 0; j < numeroReinas; j++) {
				if (temp1.get(j).getPosicionX() == -1) {
					temp1.get(j).setPosicionX(filas_temp1.get(contFilas));
					contFilas++;
				}
				if (temp1.get(j).getPosicionY() == -1) {
					temp1.get(j).setPosicionY(columnas_temp1.get(contColumnas));
					contColumnas++;
				}
			}
			contFilas = 0;
			contColumnas = 0;
			// System.out.println(filas_temp1.toString());
			// System.out.println(columnas_temp1.toString());
			filas_temp1.clear();
			columnas_temp1.clear();
			// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			// ++++++++++++++++++++++++++++++++Hijo 2
			for (int j = 0; j < numeroReinas; j++) {
				for (int j2 = 0; j2 < numeroReinas; j2++) {
					if (j == temp2.get(j2).getPosicionX()) {
						band3 = 1;
					}
					if (j == temp2.get(j2).getPosicionY()) {
						band4 = 1;
					}
				}
				if (band3 == 0) {
					filas_temp2.add(j);
				}
				if (band4 == 0) {
					columnas_temp2.add(j);
				}
				band3 = 0;
				band4 = 0;
			}
			for (int j = 0; j < numeroReinas; j++) {
				if (temp2.get(j).getPosicionX() == -1) {
					temp2.get(j).setPosicionX(filas_temp2.get(contFilas));
					contFilas++;
				}
				if (temp2.get(j).getPosicionY() == -1) {
					temp2.get(j).setPosicionY(columnas_temp2.get(contColumnas));
					contColumnas++;
				}
			}
			contFilas = 0;
			contColumnas = 0;
			// System.out.println(filas_temp2.toString());
			// System.out.println(columnas_temp2.toString());
			filas_temp2.clear();
			columnas_temp2.clear();
			System.out.println();
			System.out.println(poblacionCruza.get(i).toString());
			System.out.println(poblacionCruza.get(i + 1).toString());

			poblacionCruza.get(i)
					.setElementos((ArrayList<Reina>) temp1.clone());
			poblacionCruza.get(i + 1).setElementos(
					(ArrayList<Reina>) temp2.clone());
			// System.out.println();
			System.out.println("hijo1" + poblacionCruza.get(i).toString());
			System.out.println("hijo2" + poblacionCruza.get(i + 1).toString());

			temp1.clear();
			temp2.clear();

		}
		return poblacionCruza;
	}

	public static ArrayList<Cromosoma> Mutacion(
			ArrayList<Cromosoma> poblacionFinal, int numReinas) {
		int numIteracion = 1, aux, aux1, numMutacion, r1, r2, r3, numElementos, numIteracion2 = 1,i;
		numMutacion = (int) (poblacionFinal.size() * (.2));
		numElementos = (int) (numReinas * (.1));
		numIteracion=1;
		while (numIteracion <= numMutacion) {
			Collections.shuffle(poblacionFinal);
			System.out.println("Poblacion a Mutar" + poblacionFinal.get(0).toString());
			numElementos = (int) (numReinas * (.1));
			Random rand = new Random();
			numIteracion2 = 1;
			if(numElementos==0){
				numElementos=1;
			}
			numIteracion2=1;
			while (numIteracion2 <= numElementos) {
				r1 = rand.nextInt(numReinas);
				r2 = rand.nextInt(3);
				while(r2==0){
					r2=rand.nextInt(3);
				}
				r3 = rand.nextInt(numReinas);
				if (r3 == r1) {
					while (r3 == r1) {
						r3 = rand.nextInt(numReinas);
					}
				}
				if (r2 == 1) {
					aux = poblacionFinal.get(0).getElementos().get(r1)
							.getPosicionX();
					aux1 = poblacionFinal.get(0).getElementos().get(r3)
							.getPosicionX();
					System.out.println("Primer elemento a mutar en X:"+aux);
					System.out.println("Segundo elemento a mutar en X:"+aux1);
					poblacionFinal.get(0).getElementos().get(r1)
							.setPosicionX(aux1);
					poblacionFinal.get(0).getElementos().get(r3)
							.setPosicionX(aux);
				}
				if (r2 == 2) {
					aux = poblacionFinal.get(0).getElementos().get(r1)
							.getPosicionY();
					aux1 = poblacionFinal.get(0).getElementos().get(r3)
							.getPosicionY();
					System.out.println("Primer elemento a mutar en Y:"+aux);
					System.out.println("Segundo elemento a mutar en Y:"+aux1);
					poblacionFinal.get(0).getElementos().get(r1)
							.setPosicionY(aux1);
					poblacionFinal.get(0).getElementos().get(r3)
							.setPosicionY(aux);
				}
				numIteracion=numIteracion+1;
				numIteracion2=numIteracion2+1;
			}
			System.out.println("PoblacionMutada" + poblacionFinal.get(0).toString());
		}
		
		return poblacionFinal;
	}
}
