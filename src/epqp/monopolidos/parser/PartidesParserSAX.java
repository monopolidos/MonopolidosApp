package epqp.monopolidos.parser;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

import epqp.monopolidos.objects.Partida;
import epqp.monopolidos.objects.Player;

public class PartidesParserSAX extends DefaultHandler {

	private String cadena;
	private ArrayList<Partida> partidesEspera;
	private ArrayList<Partida> partidesTorn;
	private ArrayList<Partida> partidesNoTorn;
	private Partida partida;
	private ArrayList<Player> players;
	private Player player;
	private int userPK;
	 
    public PartidesParserSAX(int userPK) {
    	Log.d("MONOPOLIDOS", "new PartidesParserSAX()");
    	partidesEspera = new ArrayList<Partida>();
    	partidesTorn = new ArrayList<Partida>();
    	partidesNoTorn = new ArrayList<Partida>();
    	this.userPK = userPK;
    }
 
    /**
     * Inici d'un tag
     */
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    	if(qName.equalsIgnoreCase("partida")){
    		partida = new Partida();
		}
    	if(qName.equalsIgnoreCase("players")){
    		players = new ArrayList<Player>();
    	}
    	if(qName.equalsIgnoreCase("player")){
    		player = new Player();
    	}
    }
 
    /**
     * Fi d'un tag
     */
    public void endElement(String uri, String localName, String qName) throws SAXException {
		if(qName.equalsIgnoreCase("partidapk")){
			partida.setPartidaPK(Integer.parseInt(cadena));
		}
		if(qName.equalsIgnoreCase("partidadatainici")){
			partida.setpDataInici(cadena);
		}
		if(qName.equalsIgnoreCase("partidaestat")){
			partida.setpEstat(Integer.parseInt(cadena));
		}
		if(qName.equalsIgnoreCase("partidatorn")){
			partida.setpTorn(Integer.parseInt(cadena));
		}
		if(qName.equalsIgnoreCase("partidaname")){
			partida.setpName(cadena);
		}
		if(qName.equalsIgnoreCase("partida")){
			if(partida.getpEstat() == 0){
				partidesEspera.add(partida);
			}
			else {
				if(partida.getpTorn() == buscarTornJugador(partida.getpPlayers(), userPK)){
					partidesTorn.add(partida);
				}
				else {
					partidesNoTorn.add(partida);
				}
			}
		}
		if(qName.equalsIgnoreCase("playerpk")){
			player.setPlayerPK(Integer.parseInt(cadena));
		}
		if(qName.equalsIgnoreCase("playername")){
			player.setpName(cadena);
		}
		if(qName.equalsIgnoreCase("playerestat")){
			player.setpEstat(Integer.parseInt(cadena));
		}
		if(qName.equalsIgnoreCase("playermoney")){
			player.setpMoney(Integer.parseInt(cadena));
		}
		if(qName.equalsIgnoreCase("playertorn")){
			player.setpTorn(Integer.parseInt(cadena));
		}
		if(qName.equalsIgnoreCase("playerposition")){
			player.setpPosition(Integer.parseInt(cadena));
		}
		if(qName.equalsIgnoreCase("playertir")){
			player.setpTir(Integer.parseInt(cadena));
		}
		if(qName.equalsIgnoreCase("player")){
			players.add(player);
		}
		if(qName.equalsIgnoreCase("players")){
			partida.setpPlayers(players);
		}
		
		cadena = "";
    }
    
    private int buscarTornJugador(ArrayList<Player> p, int pk){
    	boolean trobat = false;
    	int i = 0;
    	int torn = -1;
    	while(!trobat && i < p.size()){
    		Player player = p.get(i);
    		if(player.getPlayerPK() == pk){
    			torn = player.getpTorn();
    			trobat = true;
    		}
    		i++;
    	}
    	
    	return torn;
    }
 
    /**
     * Contingut d'un tag
     */
    public void characters(char ch[], int start, int length) throws SAXException {
		super.characters(ch, start, length);
		cadena = new String(ch, start, length);
    }

	public ArrayList<Partida> getPartidesEspera() {
		return partidesEspera;
	}

	public ArrayList<Partida> getPartidesTorn() {
		return partidesTorn;
	}

	public ArrayList<Partida> getPartidesNoTorn() {
		return partidesNoTorn;
	}
	
}

