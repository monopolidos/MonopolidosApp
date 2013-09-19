package epqp.monopolidos.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MonopolidosSQLiteHelper extends SQLiteOpenHelper {

	private final String SQL_CREATE_TARGETA = "CREATE TABLE Targeta(" +
			"	tPK INTEGER PRIMARY KEY, " +
			"	tCarrerNom TEXT, " +
			"	tTipus INTEGER, " +
			"	tPosicio INTEGER, " +
			"	tCarrerCiutat TEXT, " +
			"	tCarrerColor TEXT, " +
			"	tCarrerPreuBasic INTEGER, " +
			"	tCarrerPreuCasa INTEGER, " +
			"	tCarrerPreuCobrarB INTEGER, " +
			"	tCarrerPreuCobrar1 INTEGER, " +
			"	tCarrerPreuCobrar2 INTEGER, " +
			"	tCarrerPreuCobrar3 INTEGER, " +
			"	tCarrerPreuCobrar4 INTEGER, " +
			"	tCarrerPreuHipotecat INTEGER, " +
			"   tImatge TEXT)"; 

	private final String SQL_CREATE_TSORT = "CREATE TABLE TargetaSort(" +
			"	tSortPK INTEGER PRIMARY KEY, " +
			"	tSortDescripcio TEXT , " +
			"	tSortTipus INTEGER, " +
			"   tSortValor INTEGER)";
	
	private final String SQL_CREATE_VIATGE = "CREATE TABLE Viatge(" +
			"	vPK INTEGER PRIMARY KEY, " +
			"	vTargetaPK INTEGER , " +
			"   vPreu INTEGER)";
	
	private final String SQL_CREATE_SESSIO = "CREATE TABLE Sessio(" + 
			"   usuariPK INTEGER PRIMARY KEY, " + 
			"   uName TEXT, " + 
			"   uPartidesG INTEGER, " + 
			"   uPartidesP INTEGER)";


	public MonopolidosSQLiteHelper(Context context, String nom, CursorFactory factory, int versio) {
		super(context, nom, factory, versio);
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d("MONOPOLIDOS", "DB -> onCreate()");
		createTargetes(db);
		db.execSQL(SQL_CREATE_SESSIO);
		//db.execSQL(SQL_CREATE_TSORT);
		//db.execSQL(SQL_CREATE_VIATGE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int versioAnterior, int versioNova) {
	}
	
	private void createTargetes(SQLiteDatabase db){
		db.execSQL(SQL_CREATE_TARGETA);
		
		db.execSQL("INSERT INTO Targeta (tPK, tTipus, tPosicio, tImatge) VALUES  (1,3,1,'others/start.png')");
		
		db.execSQL("INSERT INTO Targeta VALUES  (2,'Fifth Avenue',1,2,'New York','',250,140,30,125,375,625,1000,125,'ny/nyfifth.png')");
		db.execSQL("INSERT INTO Targeta VALUES  (3,'Central Park',1,3,'New York','',250,140,30,125,375,625,1000,125,'ny/nycentralpark.png')");
		db.execSQL("INSERT INTO Targeta VALUES  (4,'Statue Of Liberty',1,4,'New York','',280,140,35,140,420,700,1120,140,'ny/nystatue.png')");
		
		db.execSQL("INSERT INTO Targeta (tPK, tTipus, tPosicio, tImatge) VALUES  (5,2,5,'suerte/suerte1.png')");
		
		db.execSQL("INSERT INTO Targeta VALUES  (6,'8 Mile Road',1,6,'Detroit','',70,60,8,35,105,175,280,35,'detroit/detroitmile.png')");
		db.execSQL("INSERT INTO Targeta VALUES  (7,'Ambassador Bridge',1,7,'Detroit','',120,60,15,60,180,300,480,60,'detroit/detroitambassador.png')");
		
		db.execSQL("INSERT INTO Targeta (tPK, tTipus, tPosicio, tImatge, tCarrerNom) VALUES  (8,8,8,'avio/atlanta.png','Atlanta')");
		
		db.execSQL("INSERT INTO Targeta (tPK, tTipus, tPosicio, tImatge, tCarrerNom, tCarrerPreuBasic, tCarrerPreuCobrarB, tCarrerPreuHipotecat) VALUES  (9,9,9,'deserts/arizona.png','Arizona',300,75,150)");
		
		db.execSQL("INSERT INTO Targeta VALUES  (10,'Las Vegas Strip',1,10,'Las Vegas','',150,90,18,75,225,375,600,75,'lasvegas/lasvegasstrip.png')");
		db.execSQL("INSERT INTO Targeta VALUES  (11,'Bellagio',1,11,'Las Vegas','',180,90,22,90,270,450,720,90,'lasvegas/lasvegasbellagio.png')");
		
		db.execSQL("INSERT INTO Targeta (tPK, tTipus, tPosicio, tImatge) VALUES  (12,2,12,'suerte/suerte1.png')");
	
		db.execSQL("INSERT INTO Targeta VALUES  (13,'Broadway',1,13,'Los Angeles','',210,120,36,105,315,525,840,105,'losangeles/broadway.png')");
		db.execSQL("INSERT INTO Targeta VALUES  (14,'Hollywood Boulevard',1,14,'Los Angeles','',210,120,36,105,315,525,840,105,'losangeles/hollywood.png')");
		db.execSQL("INSERT INTO Targeta VALUES  (15,'Beverly Hills',1,15,'Los Angeles','',240,120,30,120,360,600,960,120,'losangeles/beverlly.png')");
		
		db.execSQL("INSERT INTO Targeta (tPK, tTipus, tPosicio, tImatge) VALUES  (16,10,16,'others/preso.png')");
		
		db.execSQL("INSERT INTO Targeta VALUES  (17,'Copacabana Beach',1,17,'Rio De Janeiro','',80,60,10,40,120,200,320,40,'rio/riocopacabana.png')");
		db.execSQL("INSERT INTO Targeta VALUES  (18,'Christ Redeemer',1,18,'Rio De Janeiro','',120,60,15,60,180,300,480,60,'rio/riochrist.png')");
		
		db.execSQL("INSERT INTO Targeta (tPK, tTipus, tPosicio, tImatge) VALUES  (19,2,19,'suerte/suerte2.png')");
		
		db.execSQL("INSERT INTO Targeta VALUES  (20,'MNAC',1,20,'Barcelona','',260,145,32,130,390,650,1040,130,'bcn/bcnlesrambles.png')");
		db.execSQL("INSERT INTO Targeta VALUES  (21,'Les Rambles',1,21,'Barcelona','',260,145,32,130,390,650,1040,130,'bcn/bcnmnac.png')");
		db.execSQL("INSERT INTO Targeta VALUES  (22,'Sagrada Família',1,22,'Barcelona','',290,145,36,145,435,725,1160,145,'bcn/bcnsagradafamilia.png')");
		
		db.execSQL("INSERT INTO Targeta (tPK, tTipus, tPosicio, tImatge, tCarrerNom) VALUES  (23,8,23,'avio/londres.png','Londres')");
		
		db.execSQL("INSERT INTO Targeta (tPK, tTipus, tPosicio, tImatge, tCarrerNom, tCarrerPreuBasic, tCarrerPreuCobrarB, tCarrerPreuHipotecat) VALUES  (24,9,24,'deserts/atacama.png','Atacama',300,75,150)");
		
		db.execSQL("INSERT INTO Targeta VALUES  (25,'The Pantheon',1,25,'Roma','',90,60,11,45,135,225,360,45,'roma/romaphanteon.png')");
		db.execSQL("INSERT INTO Targeta VALUES  (26,'Piazza San Pietro',1,26,'Roma','',90,60,11,45,135,225,360,45,'roma/sanpietro.png')");
		db.execSQL("INSERT INTO Targeta VALUES  (27,'Colosseo',1,27,'Roma','',120,60,15,60,180,300,480,60,'roma/colosseo.png')");
		
		db.execSQL("INSERT INTO Targeta (tPK, tTipus, tPosicio, tImatge) VALUES  (28,2,28,'suerte/suerte2.png')");
		
		db.execSQL("INSERT INTO Targeta VALUES  (29,'Macao Beach',1,29,'PuntaCana','',140,90,17,70,210,350,560,70,'puntacana/macao.png')");
		db.execSQL("INSERT INTO Targeta VALUES  (30,'Bavaro Beach',1,30,'PuntaCana','',180,90,22,90,270,450,720,90,'puntacana/bavaro.png')");
		
		db.execSQL("INSERT INTO Targeta (tPK, tTipus, tPosicio, tImatge) VALUES  (31,4,31,'others/bote.png')");
		
		db.execSQL("INSERT INTO Targeta VALUES  (32,'Champs Elysees',1,32,'Paris','',130,85,16,65,195,325,520,65,'paris/parischamps.png')");
		db.execSQL("INSERT INTO Targeta VALUES  (33,'Eiffel Tower',1,33,'Paris','',170,85,21,85,255,425,680,85,'paris/pariseiffel.png')");
		
		db.execSQL("INSERT INTO Targeta (tPK, tTipus, tPosicio, tImatge) VALUES  (34,7,34,'others/wingold.png')");
		
		db.execSQL("INSERT INTO Targeta VALUES  (35,'Sensoji Temple',1,35,'Tokyo','',190,115,23,95,285,475,760,95,'tokyo/tokyosensoji.png')");
		db.execSQL("INSERT INTO Targeta VALUES  (36,'Tokyo Tower',1,36,'Tokyo','',230,115,28,115,345,575,920,115,'tokyo/tokyotower.png')");
		
		db.execSQL("INSERT INTO Targeta (tPK, tTipus, tPosicio, tImatge, tCarrerNom) VALUES  (37,8,37,'avio/johan.png','Johannesburgo')");
		
		db.execSQL("INSERT INTO Targeta (tPK, tTipus, tPosicio, tImatge, tCarrerNom, tCarrerPreuBasic, tCarrerPreuCobrarB, tCarrerPreuHipotecat) VALUES  (38,9,38,'deserts/sahara.png','Sahara',300,75,150)");
		
		db.execSQL("INSERT INTO Targeta VALUES  (39,'Burj Khalifa',1,39,'Dubai','',270,150,33,135,405,675,1080,135,'dubai/dubaiburjalarab.png')");
		db.execSQL("INSERT INTO Targeta VALUES  (40,'Burj Al Arab',1,40,'Dubai','',270,150,33,135,405,675,1080,135,'dubai/dubaiburjkhalifa.png')");
		db.execSQL("INSERT INTO Targeta VALUES  (41,'The Palm Islands',1,41,'Dubai','',300,150,37,150,450,750,1200,150,'dubai/dubaipalmislands.png')");
		
		db.execSQL("INSERT INTO Targeta (tPK, tTipus, tPosicio, tImatge) VALUES  (42,2,42,'suerte/suerte3.png')");
		
		db.execSQL("INSERT INTO Targeta VALUES  (43,'River Nile',1,43,'Egypt','',80,55,10,40,120,200,320,40,'egipte/egipterivernile.png')");
		db.execSQL("INSERT INTO Targeta VALUES  (44,'Pyramid of Giza',1,44,'Egypt','',80,55,10,40,120,200,320,40,'egipte/egiptepyramid.png')");
		db.execSQL("INSERT INTO Targeta VALUES  (45,'Abu Simbel',1,45,'Egypt','',110,55,14,55,165,275,440,55,'egipte/egipteabusimbel.png')");
		
		db.execSQL("INSERT INTO Targeta (tPK, tTipus, tPosicio, tImatge) VALUES  (46,5,46,'others/gotoprision.png')");
		
		db.execSQL("INSERT INTO Targeta VALUES  (47,'Buckingham Palace',1,47,'London','',190,115,23,95,285,475,760,95,'londres/londresbuckin.png')");
		db.execSQL("INSERT INTO Targeta VALUES  (48,'Big Ben',1,48,'London','',230,115,28,115,345,575,920,115,'londres/londresbigben.png')");
		
		db.execSQL("INSERT INTO Targeta (tPK, tTipus, tPosicio, tImatge) VALUES  (49,2,49,'suerte/suerte4.png')");
		
		db.execSQL("INSERT INTO Targeta VALUES  (50,'Berlin Wall',1,50,'Berlin','',200,120,25,100,300,500,800,100,'berlin/berlinwall.png')");
		db.execSQL("INSERT INTO Targeta VALUES  (51,'Brandenburger Gate',1,51,'Berlin','',240,120,30,120,360,600,960,120,'berlin/berlinbranden.png')");
		
		db.execSQL("INSERT INTO Targeta (tPK, tTipus, tPosicio, tImatge, tCarrerNom) VALUES  (52,8,52,'avio/melbourne.png','Melbourne')");
		
		db.execSQL("INSERT INTO Targeta VALUES  (53,'Agra Fort',1,53,'Agra','',150,40,18,75,225,375,600,75,'agra/agrafort.png')");
		db.execSQL("INSERT INTO Targeta VALUES  (54,'Taj Mahal',1,54,'Agra','',180,90,18,90,270,450,720,90,'agra/agratajmahal.png')");
		
		db.execSQL("INSERT INTO Targeta (tPK, tTipus, tPosicio, tImatge, tCarrerNom, tCarrerPreuBasic, tCarrerPreuCobrarB, tCarrerPreuHipotecat) VALUES  (55,9,55,'deserts/gobi.png','Gobi',300,75,150)");
		
		db.execSQL("INSERT INTO Targeta (tPK, tTipus, tPosicio, tImatge) VALUES  (56,2,56,'suerte/suerte4.png')");
		
		db.execSQL("INSERT INTO Targeta VALUES  (57,'Bondi Beach',1,57,'Sydney','',260,150,32,130,390,650,1040,130,'syd/sydbondi.png')");
		db.execSQL("INSERT INTO Targeta VALUES  (58,'Sydney Opera',1,58,'Sydney','',300,150,37,150,450,750,1200,150,'syd/sydopera.png')");
		
		db.execSQL("INSERT INTO Targeta (tPK, tTipus, tPosicio, tImatge) VALUES  (59,2,59,'suerte/suerte4.png')");
		
		db.execSQL("INSERT INTO Targeta (tPK, tTipus, tPosicio, tImatge) VALUES  (60,6,60,'others/losegold.png')");

		Log.d("MONOPOLIDOS", "DB -> INSERTS ON TABLE TARGETA COMPLETED");
	}

}
