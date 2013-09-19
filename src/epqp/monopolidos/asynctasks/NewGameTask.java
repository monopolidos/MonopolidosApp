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
import epqp.monopolidos.dialogs.NewGameDialog;
import epqp.monopolidos.game.PartidesActivity;
import epqp.monopolidos.objects.Sessio;
import epqp.monopolidos.parser.NewGameParserSAX;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class NewGameTask extends AsyncTask<String, Void, Integer>{

	private boolean successNewGame;
	private String message;
	private PartidesActivity context;
	private NewGameDialog dialog;
	private LoadingDialog loading;
	
	public NewGameTask(PartidesActivity c, NewGameDialog d){
		successNewGame = false;
		message = "";
		context = c;
		dialog = d;
		loading = new LoadingDialog(c);
		loading.show();
	}

	@Override
	protected Integer doInBackground(String... par) {
		Log.d("MONOPOLIDOS", "NewGameTask -> doInBackground()");
		HttpClient client = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://www.monopolidos.com/webservice/newgame.php");
		int codiEstat = -1;
		
		try {
			// Afegir els paràmetres
			List<NameValuePair> parametres = new ArrayList<NameValuePair>(2);

			parametres.add(new BasicNameValuePair("p1", par[0]));
			parametres.add(new BasicNameValuePair("p2", par[1]));
			parametres.add(new BasicNameValuePair("p3", par[2]));
			parametres.add(new BasicNameValuePair("p4", par[3]));
			parametres.add(new BasicNameValuePair("p5", par[4]));
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
		
		successNewGame = parser.getSuccess();
		message = parser.getMessage();
	}
	
	protected void onPostExecute(Integer estat) {
		Log.d("MONOPOLIDOS", "onPostExecute() - " + estat);
		loading.hide();
		if(estat == 200){
			if(successNewGame){
				Log.d("MONOPOLIDOS", "New game created");
				dialog.hide();
				new GetPartidesTask(context.getCarouselPartides()).execute(Sessio.getInstance().getUser().getUsuariPK()+"");
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
