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

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import epqp.monopolidos.dialogs.LoadingDialog;
import epqp.monopolidos.objects.Partida;
import epqp.monopolidos.objects.Sessio;
import epqp.monopolidos.objects.User;
import epqp.monopolidos.parser.PartidesParserSAX;
import epqp.monopolidos.views.CarouselPartides;

public class GetPartidesTask extends AsyncTask<String, Void, Integer> {
	
	private ArrayList<Partida> partidesEspera;
	private ArrayList<Partida> partidesTorn;
	private ArrayList<Partida> partidesNoTorn;
	private int userPK;
	private CarouselPartides partides;
	private LoadingDialog dialog;
	
	public GetPartidesTask(CarouselPartides cp){
		Log.d("MONOPOLIDOS", "new GamePartidesTask()");
		partidesEspera = null;
		partidesTorn = null;
		partidesNoTorn = null;
		User u = Sessio.getInstance().getUser();
		userPK = u.getUsuariPK();
		partides = cp;
		dialog = new LoadingDialog((Activity) partides.getContext());
		dialog.show();
	}

	@Override
	protected Integer doInBackground(String... par) {
		Log.d("MONOPOLIDOS", "GetPartidesTask -> doInBackground()");
		HttpClient client = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://www.monopolidos.com/webservice/getpartides.php");
		int codiEstat = -1;
		
		try {
			// Afegir els paràmetres
			List<NameValuePair> parametres = new ArrayList<NameValuePair>(2);
			parametres.add(new BasicNameValuePair("user", userPK + ""));
			httppost.setEntity(new UrlEncodedFormEntity(parametres));
			
			// Executar la petició http
			HttpResponse response = client.execute(httppost);
			StatusLine estat = response.getStatusLine();
			codiEstat = estat.getStatusCode();
			Log.d("MONOPOLIDOS", "GetPartidesTask -> codiEstat -> " + codiEstat);
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
		PartidesParserSAX parser = new PartidesParserSAX(userPK);
		
		// analitzar el fitxer xml
		analitzador.parse(xml, parser);
		
		partidesEspera = parser.getPartidesEspera();
		partidesTorn = parser.getPartidesTorn();
		partidesNoTorn = parser.getPartidesNoTorn();
	}
	
	protected void onPostExecute(Integer estat) {
		Log.d("MONOPOLIDOS", "GetPartidesTask - onPostExecute() - " + estat);
		dialog.hide();
		if(estat == 200){
			partides.refreshData(partidesEspera, partidesTorn, partidesNoTorn);
		}
		else {
			Toast.makeText(partides.getContext(), "NETWORK ERROR", Toast.LENGTH_LONG).show();
			Log.d("MONOPOLIDOS", "ERROR");
		}
	}
}
