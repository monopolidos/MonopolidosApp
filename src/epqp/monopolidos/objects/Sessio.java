package epqp.monopolidos.objects;

import epqp.monopolidos.bd.MonopolidosSQLiteHelper;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Sessio {
	
	private static Sessio INSTANCE;
	
	private MonopolidosSQLiteHelper helper;
	private static User user = null;
	
	public Sessio(MonopolidosSQLiteHelper helper){
		this.helper = helper;
		getSessio();
		INSTANCE = this;
	}
	
	public void getSessio(){
		SQLiteDatabase db = helper.getReadableDatabase();
		
		Cursor c = db.query("Sessio", new String[] {"usuariPK", "uName", "uPartidesG", "uPartidesP"}, null, null, null, null, null);
		
		if(c.getCount() == 1){
			Log.d("MONOPOLIDOS", "There are session");
			c.moveToPosition(0);
			user = new User();
			user.setUsuariPK(c.getInt(0));
			user.setuName(c.getString(1));
			user.setuPartidesG(c.getInt(2));
			user.setuPartidesP(c.getInt(3));
		}
		else {
			Log.d("MONOPOLIDOS", "There are not a session");
		}
		
		c.close();
		db.close();
	}
	
	public void saveSessio(User u){
		long index = -1;
		
		//S'agafa l'objecte base de dades en mode escriptura.
		SQLiteDatabase db = helper.getWritableDatabase();
		
		//Borrem les dades de la taula Sessio.
		db.delete("Sessio", null, null);
		
		//Es crea un objecte de diccionari (clau, valor) per indicar els valors a afegir.
		ContentValues dades = new ContentValues();
		
		dades.put("usuariPK", u.getUsuariPK());
		dades.put("uName", u.getuName());
		dades.put("uPartidesG", u.getuPartidesG());
		dades.put("uPartidesP", u.getuPartidesP());
		
		try {
			index = db.insertOrThrow("Sessio", null, dades);
			
			Log.d("MONOPOLIDOS", "Sessio guardada amb la id: " + index);
			
			user = u;
		}
		catch (Exception e){
			Log.e("DB", e.getMessage());
		}
	}
	
	public void destroy(){
		//S'agafa l'objecte base de dades en mode escriptura.
		SQLiteDatabase db = helper.getWritableDatabase();
		
		//Borrem les dades de la taula Sessio.
		db.delete("Sessio", null, null);
		
		user = null;
	}
	
	public static Sessio getInstance(){
		return INSTANCE;
	}
	
	public User getUser(){
		this.getSessio();
		return user;
	}
	
	public boolean isLogged(){
		Log.d("MONOPOLIDOS", "Sessio.isLogged()");
		
		Log.d("MONOPOLIDOS", "Return -> " + (user == null ?  false : true));
		return user == null ?  false : true;
	}
}
