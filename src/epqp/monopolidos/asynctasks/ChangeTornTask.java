package epqp.monopolidos.asynctasks;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

import epqp.monopolidos.dialogs.LoadingDialog;
import epqp.monopolidos.game.GameActivity;
import epqp.monopolidos.parser.NewGameParserSAX;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class ChangeTornTask extends AsyncTask<String, Void, Integer>{

	private boolean success;
	private String message;
	private GameActivity context;
	private LoadingDialog loading;
	
	public ChangeTornTask(GameActivity c){
		success = false;
		message = "";
		context = c;
		loading = new LoadingDialog(c);
		//loading.show();
	}

	@Override
	protected Integer doInBackground(String... par) {
		Log.d("MONOPOLIDOS", "ChangeEstatPartidaTask -> doInBackground()");
		HttpClient client = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://www.monopolidos.com/webservice/tira.php");
		int codiEstat = -1;
		
		try {
			// Afegir els paràmetres
			List<NameValuePair> parametres = new ArrayList<NameValuePair>(2);

			parametres.add(new BasicNameValuePair("user", par[0]));
			parametres.add(new BasicNameValuePair("partida", par[1]));
			parametres.add(new BasicNameValuePair("posicio", par[2]));
			httppost.setEntity(new UrlEncodedFormEntity(parametres));
			
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
		return codiEstat;
	}
	
	private void tractar(InputStream xml) throws SAXException, IOException, ParserConfigurationException {
		SAXParserFactory generadorSAX = SAXParserFactory.newInstance();
		SAXParser analitzador = generadorSAX.newSAXParser();
		NewGameParserSAX parser = new NewGameParserSAX();
		
		// analitzar el fitxer xml
		analitzador.parse(xml, parser);
		
		success = parser.getSuccess();
		message = parser.getMessage();
	}
	
	protected void onPostExecute(Integer estat) {
		Log.d("MONOPOLIDOS", "onPostExecute() - " + estat);
		loading.hide();
		if(estat == 200){
			if(success){
				Log.d("MONOPOLIDOS", "Change torn successful");
			}
			else {
				Log.d("MONOPOLIDOS", message);
				Toast.makeText(context, message, Toast.LENGTH_LONG).show();
			}
		}
		else {
			Log.d("MONOPOLIDOS", "NETWORK ERROR");
			Toast.makeText(context, "NETWORK ERROR", Toast.LENGTH_LONG).show();
		}
	}
	
}
