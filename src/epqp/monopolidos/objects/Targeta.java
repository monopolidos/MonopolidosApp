package epqp.monopolidos.objects;

public class Targeta {
	private int tPK, tTipus, tPosicio;
	private String tCarrerNom, tCarrerColor, tImatge;
	private int tCarrerPreuBasic, tCarrerPreuCasa, tCarrerPreuCobrarB, tCarrerPreuCobrar1, tCarrerPreuCobrar2, tCarrerPreuCobrar3, tCarrerPreuCobrar4, tCarrerPreuHipotecat;
	
	public Targeta(){
		
	}
	
	public Targeta(int tPK, int tTipus, int tPosicio, String tCarrerNom, String tCarrerColor, String tImatge, int tCarrerPreuBasic,
	int tCarrerPreuCasa, int tCarrerPreuCobrarB, int tCarrerPreuCobrar1, int tCarrerPreuCobrar2, int tCarrerPreuCobrar3, int tCarrerPreuCobrar4,
	int tCarrerPreuHipotecat) {
		this.tPK = tPK;
		this.tTipus = tTipus;
		this.tPosicio = tPosicio;
		this.tCarrerNom = tCarrerNom;
		this.tCarrerColor = tCarrerColor;
		this.tImatge = tImatge;
		this.tCarrerPreuBasic = tCarrerPreuBasic;
		this.tCarrerPreuCasa = tCarrerPreuCasa;
		this.tCarrerPreuCobrarB = tCarrerPreuCobrarB;
		this.tCarrerPreuCobrar1 = tCarrerPreuCobrar1;
		this.tCarrerPreuCobrar2 = tCarrerPreuCobrar2;
		this.tCarrerPreuCobrar3 = tCarrerPreuCobrar3;
		this.tCarrerPreuCobrar4 = tCarrerPreuCobrar4;
		this.tCarrerPreuHipotecat = tCarrerPreuHipotecat;
	}

	public int gettPK() {
		return tPK;
	}

	public void settPK(int tPK) {
		this.tPK = tPK;
	}

	public int gettTipus() {
		return tTipus;
	}

	public void settTipus(int tTipus) {
		this.tTipus = tTipus;
	}

	public int gettPosicio() {
		return tPosicio;
	}

	public void settPosicio(int tPosicio) {
		this.tPosicio = tPosicio;
	}

	public String gettCarrerNom() {
		return tCarrerNom;
	}

	public void settCarrerNom(String tCarrerNom) {
		this.tCarrerNom = tCarrerNom;
	}

	public String gettCarrerColor() {
		return tCarrerColor;
	}

	public void settCarrerColor(String tCarrerColor) {
		this.tCarrerColor = tCarrerColor;
	}

	public String gettImatge() {
		return tImatge;
	}

	public void settImatge(String tImatge) {
		this.tImatge = tImatge;
	}

	public int gettCarrerPreuBasic() {
		return tCarrerPreuBasic;
	}

	public void settCarrerPreuBasic(int tCarrerPreuBasic) {
		this.tCarrerPreuBasic = tCarrerPreuBasic;
	}

	public int gettCarrerPreuCasa() {
		return tCarrerPreuCasa;
	}

	public void settCarrerPreuCasa(int tCarrerPreuCasa) {
		this.tCarrerPreuCasa = tCarrerPreuCasa;
	}

	public int gettCarrerPreuCobrarB() {
		return tCarrerPreuCobrarB;
	}

	public void settCarrerPreuCobrarB(int tCarrerPreuCobrarB) {
		this.tCarrerPreuCobrarB = tCarrerPreuCobrarB;
	}

	public int gettCarrerPreuCobrar1() {
		return tCarrerPreuCobrar1;
	}

	public void settCarrerPreuCobrar1(int tCarrerPreuCobrar1) {
		this.tCarrerPreuCobrar1 = tCarrerPreuCobrar1;
	}

	public int gettCarrerPreuCobrar2() {
		return tCarrerPreuCobrar2;
	}

	public void settCarrerPreuCobrar2(int tCarrerPreuCobrar2) {
		this.tCarrerPreuCobrar2 = tCarrerPreuCobrar2;
	}

	public int gettCarrerPreuCobrar3() {
		return tCarrerPreuCobrar3;
	}

	public void settCarrerPreuCobrar3(int tCarrerPreuCobrar3) {
		this.tCarrerPreuCobrar3 = tCarrerPreuCobrar3;
	}

	public int gettCarrerPreuCobrar4() {
		return tCarrerPreuCobrar4;
	}

	public void settCarrerPreuCobrar4(int tCarrerPreuCobrar4) {
		this.tCarrerPreuCobrar4 = tCarrerPreuCobrar4;
	}

	public int gettCarrerPreuHipotecat() {
		return tCarrerPreuHipotecat;
	}

	public void settCarrerPreuHipotecat(int tCarrerPreuHipotecat) {
		this.tCarrerPreuHipotecat = tCarrerPreuHipotecat;
	}
}
