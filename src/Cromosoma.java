import java.util.ArrayList;
import java.util.Random;

public class Cromosoma {
	private ArrayList<Reina> elementos;
	private int numeroAtaques; 
	private int elite;			//0 = No Elite, 1 = Elite
	
	public Cromosoma(int tamano) {
		super();
		ArrayList<Integer> filas =new ArrayList<Integer>();
		ArrayList<Integer> columnas =new ArrayList<Integer>();
		this.elementos=new ArrayList<Reina>();
		Random rand= new Random();
		int x,y;
		for (int i = 0; i < tamano; i++) {
			filas.add(i);
			columnas.add(i);
		}
		for (int i = 0; i < tamano; i++) {
			x=rand.nextInt(filas.size());
			y=rand.nextInt(columnas.size());
			//System.out.println(x+","+y);
			elementos.add( new Reina(filas.get(x),columnas.get(y)));
			filas.remove(x);
			columnas.remove(y);
			//System.out.println(filas.toString());
			//System.out.println(columnas.toString());
		}
		numeroAtaques = -1;
		setElite(0);			
	}
	public int getNumeroAtaques() {
		return numeroAtaques;
	}
	public void setNumeroAtaques(int numeroAtaques) {
		this.numeroAtaques = numeroAtaques;
	}
	public String toString() {
		return "cromosoma=" + elementos + "Numero de Ataques = " + numeroAtaques;
	}
	public ArrayList<Reina> getElementos() {
		return elementos;
	}
	public void setElementos(ArrayList<Reina> elementos) {
		this.elementos = elementos;
	}
	public void evaluarAtaques() {
		int numeroAtaques = 0;
		for (int i = 0; i < elementos.size(); i++) {
			for(int j = i + 1 ; j < elementos.size(); j++){
				if(elementos.get(i).getPosicionX() == elementos.get(j).getPosicionX()){
					numeroAtaques++;
				}
				if(elementos.get(i).getPosicionY() == elementos.get(j).getPosicionY()){
					numeroAtaques++;
				}
				for (int x = 0; x < elementos.size(); x++){
					if( (elementos.get(i).getPosicionY() - elementos.get(j).getPosicionY()) == 
					    (elementos.get(i).getPosicionX() - elementos.get(j).getPosicionX()) ){
						numeroAtaques++;
					}
				}
			}
			setNumeroAtaques(numeroAtaques);
		}
	}
	public int getElite() {
		return elite;
	}
	public void setElite(int elite) {
		this.elite = elite;
	}

}