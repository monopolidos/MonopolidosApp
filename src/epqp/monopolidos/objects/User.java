package epqp.monopolidos.objects;

public class User {

	private String uName;
	private int usuariPK, uPartidesG, uPartidesP;
	
	public String getuName() {
		return uName;
	}
	
	public void setuName(String uName) {
		this.uName = uName;
	}
	
	public int getUsuariPK() {
		return usuariPK;
	}
	
	public void setUsuariPK(int usuariPK) {
		this.usuariPK = usuariPK;
	}
	
	public int getuPartidesG() {
		return uPartidesG;
	}
	
	public void setuPartidesG(int uPartidesG) {
		this.uPartidesG = uPartidesG;
	}
	
	public int getuPartidesP() {
		return uPartidesP;
	}
	
	public void setuPartidesP(int uPartidesP) {
		this.uPartidesP = uPartidesP;
	}
	
}
