import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
	}
	
	
	public void seleccion(Cromosoma poblacion){
		poblacion.evaluarAtaques();
//		Collections.sort(poblacion,new Comparator(){
//
//			@Override
//			public int compare(Cromosoma c1, Cromosoma c2) {
//				return new Integer(c2.getNumeroAtaques()).compareTo(new Integer(c1.getNumeroAtaques()));
//			}
//			
//		});
	}
}
