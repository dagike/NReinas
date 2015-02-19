import java.util.ArrayList;
import java.util.Random;

public class Cromosoma {
	private ArrayList<reina> elementos;
	private int numeroAtaques; 
	private int elite;			//0 = No Elite, 1 = Elite
	
	public Cromosoma(int tamaño) {
		super();
		ArrayList<Integer> filas =new ArrayList<Integer>();
		ArrayList<Integer> columnas =new ArrayList<Integer>();
		this.elementos=new ArrayList<reina>();
		Random rand= new Random();
		int x,y;
		for (int i = 0; i < tamaño; i++) {
			filas.add(i);
			columnas.add(i);
		}
		for (int i = 0; i < tamaño; i++) {
			x=rand.nextInt(filas.size());
			y=rand.nextInt(columnas.size());
			//System.out.println(x+","+y);
			elementos.add( new reina(filas.get(x),columnas.get(y)));
			filas.remove(x);
			columnas.remove(y);
			System.out.println(filas.toString());
			System.out.println(columnas.toString());
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
		return "cromosoma=" + elementos ;
	}
	public ArrayList<reina> getElementos() {
		return elementos;
	}
	public void setElementos(ArrayList<reina> elementos) {
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
				if(elementos.get(i).getPosicionX() == elementos.get(j).getPosicionY() && 
				   elementos.get(i).getPosicionY() == elementos.get(j).getPosicionX()){
					numeroAtaques++;
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