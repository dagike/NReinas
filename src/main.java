import java.util.ArrayList;



public class main {
	public static void main(String[] args) {
		ArrayList<Cromosoma> poblacion=new ArrayList<Cromosoma>();
		int numeroReinas=9,tamPoblacion=10, tamElitismo;
//		Numero de reinas = tamaño
//		Crear poblacion 
		for (int i = 0; i < tamPoblacion; i++) {
			poblacion.add(new Cromosoma(numeroReinas));
			System.out.println(poblacion.get(i).toString());
		}
		//Elitismo de 20%
		tamElitismo =(int)((0.2)*(poblacion.size()));
		seleccion(poblacion, tamElitismo);
	}
	
	public static void seleccion(ArrayList<Cromosoma> poblacion, int tamElitismo){
		System.out.println("\nEvaluacion:\n");
		for(int i = 0; i < poblacion.size(); i++){
			poblacion.get(i).evaluarAtaques();
			System.out.println(poblacion.get(i).toString());
		}
		
		int elite1 = 0, elite2 = 1;
		
		for(int i = 2; i < poblacion.size(); i++){
			if(poblacion.get(elite1).getNumeroAtaques()>poblacion.get(i).getNumeroAtaques()){
				elite1 = i;
			}else if(poblacion.get(elite2).getNumeroAtaques()>poblacion.get(i).getNumeroAtaques()){
				elite2 = i;
			}
		}
		poblacion.get(elite1).setElite(1);
		poblacion.get(elite2).setElite(1);
		System.out.println("Elites:\n" + poblacion.get(elite1).toString() + "\n" + poblacion.get(elite2).toString());
	}
}
