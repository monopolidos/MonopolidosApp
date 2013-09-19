package epqp.monopolidos.objects;

import java.io.Serializable;

public class Player implements Serializable {

	private int playerPK, pTorn, pEstat, pMoney, pPosition, pTir;
	private String pName;
	
	public int getPlayerPK() {
		return playerPK;
	}
	
	public void setPlayerPK(int playerPK) {
		this.playerPK = playerPK;
	}
	
	public int getpTorn() {
		return pTorn;
	}
	
	public void setpTorn(int pTorn) {
		this.pTorn = pTorn;
	}
	
	public int getpEstat() {
		return pEstat;
	}
	
	public void setpEstat(int pEstat) {
		this.pEstat = pEstat;
	}
	
	public int getpMoney() {
		return pMoney;
	}
	
	public void setpMoney(int pMoney) {
		this.pMoney = pMoney;
	}
	
	public int getpPosition() {
		return pPosition;
	}
	
	public void setpPosition(int pPosition) {
		this.pPosition = pPosition;
	}
	
	public String getpName() {
		return pName;
	}
	
	public void setpName(String pName) {
		this.pName = pName;
	}

	public int getpTir() {
		return pTir;
	}

	public void setpTir(int pTir) {
		this.pTir = pTir;
	}
	
}
