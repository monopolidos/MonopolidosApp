package epqp.monopolidos.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class NewGameParserSAX extends DefaultHandler {

	private String success = null;
	private String cadena;
	private String message = null;
	 
    public NewGameParserSAX() {
    	success = null;
    	message = null;
    }
 
    /**
     * Inici d'un tag
     */
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    }
 
    /**
     * Fi d'un tag
     */
    public void endElement(String uri, String localName, String qName) throws SAXException {
		Log.d("MONOPOLIDOS", "</" + qName + "> -> "+cadena);
		if(qName.equalsIgnoreCase("success")){
			success=cadena;
		}
		if(qName.equalsIgnoreCase("message")){
			message = cadena;
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
    
    public boolean getSuccess(){
    	Log.d("MONOPOLIDOS", "getSuccess-> " + success.equalsIgnoreCase("1"));
    	return success.equalsIgnoreCase("1") ? true : false;
    }
    
    public String getMessage(){
    	Log.d("MONOPOLIDOS", "getMessage-> " + message);
    	return message;
    }
	
}
