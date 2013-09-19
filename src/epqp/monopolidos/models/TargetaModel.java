package epqp.monopolidos.models;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import epqp.monopolidos.bd.MonopolidosSQLiteHelper;
import epqp.monopolidos.objects.Targeta;

@SuppressLint("NewApi")
public class TargetaModel {

	MonopolidosSQLiteHelper mHelper;
	
	public TargetaModel(MonopolidosSQLiteHelper mHelper){
		this.mHelper = mHelper;
	}
	
	public Targeta[] getAllTargets(){
		SQLiteDatabase db = mHelper.getReadableDatabase();
		Targeta[] targetes = null;
		
		if(db != null){		
			Cursor c = db.query("Targeta", new String[] {"tPK", "tTipus", "tPosicio", "tImatge", "tCarrerNom", "tCarrerColor", "tCarrerPreuBasic", "tCarrerPreuCasa", "tCarrerPreuCobrarB", "tCarrerPreuCobrar1", "tCarrerPreuCobrar2", "tCarrerPreuCobrar3", "tCarrerPreuCobrar4", "tCarrerPreuHipotecat"}, null, null, null, null, "tPosicio ASC");
			
			targetes = new Targeta[c.getCount()];
			
			for(int i = 0; i < c.getCount(); i++){
				if(c.moveToPosition(i)){
					Targeta t = new Targeta();
					t.settPK(c.getInt(0));
					t.settTipus(c.getInt(1));
					t.settPosicio(c.getInt(2));
					t.settImatge(c.getString(3));
					t.settCarrerNom(c.getString(4));
					t.settCarrerColor(c.getString(5));
					t.settCarrerPreuBasic(c.getInt(6));
					t.settCarrerPreuCasa(c.getInt(7));
					t.settCarrerPreuCobrarB(c.getInt(8));
					t.settCarrerPreuCobrar1(c.getInt(9));
					t.settCarrerPreuCobrar2(c.getInt(10));
					t.settCarrerPreuCobrar3(c.getInt(11));
					t.settCarrerPreuCobrar4(c.getInt(12));
					t.settCarrerPreuHipotecat(c.getInt(13));
					
					targetes[i] = t;
				}
			}
		}
		
		return targetes;
	}
	
}
