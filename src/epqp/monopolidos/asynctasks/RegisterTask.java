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

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import epqp.monopolidos.dialogs.LoadingDialog;
import epqp.monopolidos.game.PartidesActivity;
import epqp.monopolidos.game.RegisterActivity;
import epqp.monopolidos.objects.Sessio;
import epqp.monopolidos.objects.User;
import epqp.monopolidos.parser.LoginParserSAX;

public class RegisterTask extends AsyncTask<String, Void, Integer>{

	private RegisterActivity context;
	private User user;
	private boolean successRegister;
	private LoadingDialog loading;
	
	public RegisterTask(RegisterActivity context){
		this.context = context;
		user = null;	
		successRegister = false;
		loading = new LoadingDialog(context);
		loading.show();
	}
	
	@Override
	protected Integer doInBackground(String... par) {
		Log.d("MONOPOLIDOS", "doInBackground()");
		HttpClient client = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://www.monopolidos.com/webservice/register.php");
		int codiEstat = -1;
		
		try {
			// Afegir els paràmetres
			List<NameValuePair> parametres = new ArrayList<NameValuePair>(2);
			Log.d("MONOPOLIDOS", "Username -> " + par[0] + " Password -> " + par[1]);
			parametres.add(new BasicNameValuePair("username", par[0]));
			parametres.add(new BasicNameValuePair("password", par[1]));
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
	
	protected void onPostExecute(Integer estat) {
		Log.d("MONOPOLIDOS", "onPostExecute() - " + estat);
		loading.hide();
		if(estat == 200){
			if(successRegister){
				//Toast.makeText(RegisterActivity.this, "Register success user " + user.getuName(), Toast.LENGTH_LONG).show();
				Sessio.getInstance().saveSessio(user);
				Intent i = new Intent(context, PartidesActivity.class);
				context.startActivity(i);
				context.finish();
			}
			else {
				Toast.makeText(context, "REGISTER NOT SUCCESS", Toast.LENGTH_LONG).show();
			}
		}
		else {
			Toast.makeText(context, "NETWORK ERROR", Toast.LENGTH_LONG).show();
			Log.d("MONOPOLIDOS", "ERROR");
		}
	}
	
	private void tractar(InputStream xml) throws SAXException, IOException, ParserConfigurationException {
		SAXParserFactory generadorSAX = SAXParserFactory.newInstance();
		SAXParser analitzador = generadorSAX.newSAXParser();
		LoginParserSAX parser = new LoginParserSAX();
		
		// analitzar el fitxer xml
		analitzador.parse(xml, parser);
		
		user = parser.getUser();
		
		successRegister = parser.getSuccess();
	}
	
}
