package epqp.monopolidos.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import epqp.monopolidos.objects.User;

import android.util.Log;

public class LoginParserSAX extends DefaultHandler {
    /* atributs per saber quin tag estem tractant */
    private String cadena = "";
 
    private User u = null;
    private String success = null;
 
    public LoginParserSAX() {
    	u = null;
    	success = null;
    }
 
    /**
     * Inici d'un tag
     */
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    	if (qName.equalsIgnoreCase("user")) {
		    u = new User();
		}
    }
 
    /**
     * Fi d'un tag
     */
    public void endElement(String uri, String localName, String qName) throws SAXException {
 
		if (qName.equalsIgnoreCase("pk")) {
			u.setUsuariPK(Integer.parseInt(cadena));
			Log.d("MONOPOLIDOS", u.getUsuariPK() + "");
		}
		
		if (qName.equalsIgnoreCase("name")) {
			u.setuName(cadena);
		}
		
		if (qName.equalsIgnoreCase("partidesg")) {
			u.setuPartidesG(Integer.parseInt(cadena));
		}
		
		if (qName.equalsIgnoreCase("partidesp")) {
			u.setuPartidesP(Integer.parseInt(cadena));
		}
		
		if(qName.equalsIgnoreCase("success")){
			success=cadena;
		}
		
		cadena = "";
    }
 
    /**
     * Contingut d'un tag
     */
    public void characters(char ch[], int start, int length) throws SAXException {
		super.characters(ch, start, length);
		cadena = new String(ch, start, length);
    }
    
    public User getUser(){
    	return u;
    }
    
    public boolean getSuccess(){
    	return success.equalsIgnoreCase("1") ? true : false;
    }
}
