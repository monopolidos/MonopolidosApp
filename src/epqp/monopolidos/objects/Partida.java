package epqp.monopolidos.objects;

import java.io.Serializable;
import java.util.ArrayList;

import android.util.Log;

public class Partida implements Serializable {

	private int partidaPK, pEstat, pTorn;
	private String pDataInici, pName;
	private ArrayList<Player> pPlayers;
	
	public int getPartidaPK() {
		return partidaPK;
	}
	
	public void setPartidaPK(int partidaPK) {
		this.partidaPK = partidaPK;
	}
	
	public int getpEstat() {
		return pEstat;
	}
	
	public void setpEstat(int pEstat) {
		this.pEstat = pEstat;
	}
	
	public int getpTorn() {
		return pTorn;
	}
	
	public void setpTorn(int pTorn) {
		this.pTorn = pTorn;
	}
	
	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpDataInici() {
		return pDataInici;
	}
	
	public void setpDataInici(String pDataInici) {
		String[] s = pDataInici.split(" ");
		this.pDataInici = s[0].trim();
	}

	public ArrayList<Player> getpPlayers() {
		return pPlayers;
	}

	public void setpPlayers(ArrayList<Player> pPlayers) {
		this.pPlayers = pPlayers;
	}
	
	public String getpPlayersToString(){
		String retorn = "";
		
		for(int i = 0; i < pPlayers.size(); i++){
			if(i != 0){
				retorn += ", " + pPlayers.get(i).getpName();
			}
			else {
				retorn += pPlayers.get(i).getpName();
			}
		}
		
		return retorn;
	}
	
	public String getpPlayersNotConfirmedToString(){
		String retorn = "";
		int cont = 0;
		
		for(int i = 0; i < pPlayers.size(); i++){
			if(pPlayers.get(i).getpEstat() == 0){
				if(cont != 0){
					retorn += ", " + pPlayers.get(i).getpName();
				}
				else {
					retorn += pPlayers.get(i).getpName();
					cont++;
				}
			}
		}
		
		return retorn;
	}
	
	public String getPlayerTornName(){
		boolean trobat = false;
		int i = 0;
		String name = "";
		while(i < pPlayers.size() && !trobat){
			if(pPlayers.get(i).getpTorn() == pTorn)
				name = pPlayers.get(i).getpName();
			
			i++;
		}
		
		return name;
	}
	
	public Player GetPlayerTorn(){
		boolean trobat = false;
		int i = 0;
		Player p = null;
		while(i < pPlayers.size() && !trobat){
			if(pPlayers.get(i).getpTorn() == pTorn){
				p = pPlayers.get(i);
			}
			
			i++;
		}
		
		return p;
	}
	
	public boolean isPlayerTorn(){
		boolean trobat = false;
		int i = 0;
		int pk = Sessio.getInstance().getUser().getUsuariPK();

		while(i < pPlayers.size() && !trobat){
			if(pPlayers.get(i).getpTorn() == pTorn){
				if(pPlayers.get(i).getPlayerPK() == pk){
					trobat = true;
				}
			}				
			
			i++;
		}
		
		return trobat;
	}
	
	public int GetPlayerTir(){
		boolean trobat = false;
		int i = 0;
		int pk = Sessio.getInstance().getUser().getUsuariPK();
		int tir = -1;

		while(i < pPlayers.size() && !trobat){
			if(pPlayers.get(i).getpTorn() == pTorn){
				if(pPlayers.get(i).getPlayerPK() == pk){
					trobat =  true;
					tir = pPlayers.get(i).getpTir();
				}
			}				
			
			i++;
		}
		
		return tir;
	}
	
	public boolean PlayerHasRolled(){
		boolean trobat = false;
		int i = 0;
		int pk = Sessio.getInstance().getUser().getUsuariPK();

		while(i < pPlayers.size() && !trobat){
			if(pPlayers.get(i).getpTorn() == pTorn){
				if(pPlayers.get(i).getPlayerPK() == pk){
					if(pPlayers.get(i).getpTir() != 0){
						trobat = true;
					}
				}
			}				
			
			i++;
		}
		
		return trobat;
	}
	
	public void SaveTirDice(int numDau){
		boolean trobat = false;
		int i = 0;
		int pk = Sessio.getInstance().getUser().getUsuariPK();

		while(i < pPlayers.size() && !trobat){
			if(pPlayers.get(i).getpTorn() == pTorn){
				if(pPlayers.get(i).getPlayerPK() == pk){
					pPlayers.get(i).setpTir(numDau);
					trobat = true;
				}
			}				
			
			i++;
		}
	}
	
	public int GetPlayerPosition(){
		boolean trobat = false;
		int i = 0;
		int pk = Sessio.getInstance().getUser().getUsuariPK();
		int tir = -1;

		while(i < pPlayers.size() && !trobat){
			if(pPlayers.get(i).getPlayerPK() == pk){
				tir = pPlayers.get(i).getpPosition();
				trobat = true;
			}
			
			i++;
		}
		
		return tir;
	}
	
	public void SetPlayerPosition(int position){
		boolean trobat = false;
		int i = 0;
		int pk = Sessio.getInstance().getUser().getUsuariPK();

		while(i < pPlayers.size() && !trobat){
			if(pPlayers.get(i).getPlayerPK() == pk){
				pPlayers.get(i).setpPosition(position);
				trobat = true;
			}
			
			i++;
		}
	}
	
	public boolean userIsConfirmed(int userPK){
		boolean trobat = false;
		int i = 0;
		
		while(i < pPlayers.size() && !trobat){
			Player p = pPlayers.get(i);
			if(p.getpEstat() == 1 && p.getPlayerPK() == userPK){
				trobat = true;
			}
			i++;
		}
		
		return trobat;
	}
	
}
