
public class reina {
	private int posicionX;
	private int posicionY;
	
	public reina(int posicionX, int posicionY) {
		super();
		this.posicionX = posicionX;
		this.posicionY = posicionY;
	}
	public int getPosicionX() {
		return posicionX;
	}
	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}
	public int getPosicionY() {
		return posicionY;
	}
	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}
	@Override
	public String toString() {
		return "(" + posicionX + "," + posicionY
				+ ")";
	}
	
}
