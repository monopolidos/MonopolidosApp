package epqp.monopolidos.game;

import java.util.Random;

import epqp.monopolidos.asynctasks.ChangeTornTask;
import epqp.monopolidos.bd.MonopolidosSQLiteHelper;
import epqp.monopolidos.dialogs.DaiceDialog;
import epqp.monopolidos.dialogs.TargetaDialog;
import epqp.monopolidos.listeners.GameMenuClickListener;
import epqp.monopolidos.models.TargetaModel;
import epqp.monopolidos.objects.Partida;
import epqp.monopolidos.objects.Sessio;
import epqp.monopolidos.objects.Targeta;
import epqp.monopolidos.views.Taulell;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class GameActivity extends Activity {

	// -------------------------------------------------------------------------------
	//    VARIABLES
	// -------------------------------------------------------------------------------
	
	private int screenHeight = -1;
	private int screenWidth = -1;
	
	private Taulell taulell;
	private RadioButton rbExit;
	private RadioButton rbDice;
	private RadioGroup rgGame;
	private RelativeLayout parentLay;
	
	private GameMenuClickListener menuClickListener;
	
	private Targeta[] targetes;
	
	private TargetaModel targetaModel;
	
	private MonopolidosSQLiteHelper mSQLiteHelper;
	private Partida partida;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("MONOPOLIDOS", "GameActivity -> onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		Intent i = getIntent();
		partida = (Partida) i.getSerializableExtra("partida");
		
		menuClickListener = new GameMenuClickListener(this);
		
		Display display = getWindowManager().getDefaultDisplay(); 
		screenWidth = display.getWidth();  
		screenHeight = display.getHeight() - getStatusBarHeight() - 80;  
		
		parentLay = (RelativeLayout)findViewById(R.id.tableroParentLayout);
		parentLay.setClipChildren(false);
		rgGame = (RadioGroup)findViewById(R.id.radiogroupgame);
		rbExit = (RadioButton)findViewById(R.id.rbExit);
		rbDice = (RadioButton)findViewById(R.id.rbRollTheDice);
		
		rbExit.setOnCheckedChangeListener(menuClickListener);
		rbDice.setOnCheckedChangeListener(menuClickListener);
		
		mSQLiteHelper = new  MonopolidosSQLiteHelper(this, "Monopolidos.db", null, 2);
        SQLiteDatabase db = mSQLiteHelper.getWritableDatabase();
        
        targetaModel = new TargetaModel(mSQLiteHelper);
        
        if(db != null){
        	db.close();
        }
        
        targetes = targetaModel.getAllTargets();
        
        addTaulell();
        
		designWindow();

		taulell.ColocaFitxes();
		
		taulell.ScrollToUserPosition();
		
		if(partida.isPlayerTorn() && !partida.PlayerHasRolled()){
			DaiceDialog dialog = new DaiceDialog(this, this.getPartida());
			dialog.show();
		}
		else {
			if(partida.PlayerHasRolled()){
				setTirada(partida.GetPlayerTir());
			}
			else {
				//Borrar el boto del menu de tirar.
				ViewGroup parent = (ViewGroup)rbDice.getParent();
				parent.removeView(rbDice);
			}
		}

	}
	
	public void onBackPressed(){
		super.onBackPressed();
		taulell.ClearTaulell();
	}
	
	public Taulell GetTaulell(){
		return taulell;
	}
	
	private void addTaulell(){
		taulell = Taulell.getINSTANCE();
        
        if(taulell == null){
        	taulell = new Taulell(this, targetes);
        }
        
        ViewGroup parent = ((ViewGroup)taulell.getParent());
        if(parent != null){
        	parent.removeView(taulell);
        }
        
        taulell.setPartida(partida);

    	parentLay.addView(taulell);
	}
	
	private void designWindow(){
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(screenWidth, screenHeight);
		params.topMargin = 0;
		params.leftMargin = 0;
		taulell.setLayoutParams(params);
		
		params = new RelativeLayout.LayoutParams(screenWidth, 80);
		params.topMargin = screenHeight;
		params.leftMargin = 0;
		rgGame.setLayoutParams(params);
	}
	
	public void setTirada(int num){
		partida.SaveTirDice(num);
		int posActual = partida.GetPlayerPosition();
		int posFinal = posActual + num;
		
		if(posFinal > 60){
			posFinal = posFinal - 60;
		}
		
		taulell.MoveFitxa(posActual, posFinal, num, 1);
		
		//Borrar el boto del menu de tirar.
		ViewGroup parent = (ViewGroup)rbDice.getParent();
		parent.removeView(rbDice);
	}
	
	public void acabarTirada(int posFinal){
		partida.SetPlayerPosition(posFinal);
		
		//TargetaDialog dialog = new TargetaDialog(this, taulell.GetTargetaOfPosition(posFinal));
		//dialog.show();
		
		//Canviar el torn.
		new ChangeTornTask(this).execute(Sessio.getInstance().getUser().getUsuariPK()+"", partida.getPartidaPK()+ "", posFinal+"");
	}
	
	public Partida getPartida(){
		return this.partida;
	}
	
	public int getStatusBarHeight() {
		int result = 0;
		int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}
}
