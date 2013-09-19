package epqp.monopolidos.asynctasks;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.xml.sax.SAXException;

import epqp.monopolidos.dialogs.DaiceDialog;
import epqp.monopolidos.game.GameActivity;
import epqp.monopolidos.objects.Partida;
import epqp.monopolidos.objects.Sessio;
import epqp.monopolidos.parser.NewGameParserSAX;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class DaiceTask extends AsyncTask<String, Void, Integer> {

	private DaiceDialog dialog;
	private Random aleatori;
	private boolean isRodant;
	private int numActual;
	private Partida partida;
	private boolean success;
	private String message;
	private GameActivity context;
	
	public DaiceTask(DaiceDialog dialog, Partida partida, GameActivity context){
		this.dialog = dialog;
		this.partida = partida;
		this.context = context;
		isRodant = true;
		aleatori = new Random();
		numActual = aleatori.nextInt(6) + 1;
		dialog.setDaiceImage(numActual);
		success = false;
		Log.d("MONOPOLIDOS", "new DaiceTask()");
	}
	
	public void changeIsRodant(){
		isRodant = isRodant ? false : true;
	}
	
	private void tractar(InputStream xml) throws SAXException, IOException, ParserConfigurationException {
		SAXParserFactory generadorSAX = SAXParserFactory.newInstance();
		SAXParser analitzador = generadorSAX.newSAXParser();
		NewGameParserSAX parser = new NewGameParserSAX();
		
		// analitzar el fitxer xml
		analitzador.parse(xml, parser);
		
		success = parser.getSuccess();
		Log.d("MONOPOLIDOS", "parser.getSuccess() -> " + parser.getSuccess());
		message = parser.getMessage();
	}
	
	@Override
	protected Integer doInBackground(String... params) {
		Log.d("MONOPOLIDOS", "DaiceTask -> doInBackground()");
		
		while(isRodant){
			if (isCancelled()) break;
			int aux = numActual;
			while(aux == numActual)
				numActual = aleatori.nextInt(6)+1;
			
			dialog.setDaiceImage(numActual);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		try {
			Thread.sleep(900);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		int codiEstat = -1;
		
		if(!isCancelled()){
			Log.d("MONOPOLIDOS", "DaiceTask -> startPeticioChangeTir()");
			
			HttpClient client = new DefaultHttpClient();
			HttpPost httppost = new HttpPost("http://www.monopolidos.com/webservice/changetir.php");
			
			try {
				// Afegir els paràmetres
				List<NameValuePair> parametres = new ArrayList<NameValuePair>(2);
				parametres.add(new BasicNameValuePair("user", Sessio.getInstance().getUser().getUsuariPK() + ""));
				parametres.add(new BasicNameValuePair("partida", partida.getPartidaPK() + ""));
				parametres.add(new BasicNameValuePair("tir", numActual + ""));
				httppost.setEntity(new UrlEncodedFormEntity(parametres));
				Log.d("MONOPOLIDOS", "User -> " + Sessio.getInstance().getUser().getUsuariPK() + " / Partida -> " + partida.getPartidaPK() + " / Tir -> " + numActual);
				
				// Executar la petició http
				HttpResponse response = client.execute(httppost);
				StatusLine estat = response.getStatusLine();
				codiEstat = estat.getStatusCode();
				Log.d("MONOPOLIDOS", "codiEstat -> " + codiEstat);
				if (codiEstat == 200) {
					InputStream contingut = response.getEntity().getContent();
					tractar(contingut);
				}
			} catch (ClientProtocolException e) {
			} catch (IOException e) {
			} catch (SAXException e) {
			} catch (ParserConfigurationException e) {
			} 
		}
		return codiEstat;
	}

	protected void onPostExecute(Integer codiEstat) {
		Log.d("MONOPOLIDOS", "DaiceTask - onPostExecute() - " + codiEstat);
		/*if(codiEstat == 200){
			if(success){*/
				dialog.hide();
				context.setTirada(numActual);
			/*}
			else {
				dialog.hide();
				Toast.makeText(context, message, Toast.LENGTH_LONG).show();
			}
		}
		else {
			if(!isCancelled())
				Toast.makeText(context, "NETWORK ERROR", Toast.LENGTH_LONG).show();
		}*/
	}
	
	public boolean DaiceIsRodant(){
		return isRodant;
	}
	
	public int getNumActual(){
		return numActual;
	}
}
