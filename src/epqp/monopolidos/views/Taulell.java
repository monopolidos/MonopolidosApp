package epqp.monopolidos.views;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import epqp.monopolidos.asynctasks.ChangeTornTask;
import epqp.monopolidos.dialogs.DaiceDialog;
import epqp.monopolidos.game.GameActivity;
import epqp.monopolidos.game.R;
import epqp.monopolidos.listeners.TableroTouchListener;
import epqp.monopolidos.objects.Partida;
import epqp.monopolidos.objects.Player;
import epqp.monopolidos.objects.Sessio;
import epqp.monopolidos.objects.Targeta;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TableRow.LayoutParams;

public class Taulell extends RelativeLayout {

	private int[] ordre = new int[] {16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 15, 32, 14, 33,13, 34,12,35,11,36,10,37,9,38,8,39,7,40,6,41,5,42,4,43,3,44,2,45,1,60,59,58,57,56,55,54,53,52,51,50,49,48,47,46};
	
	private GameActivity context;

	private VerticalScroll vScroll;
	private HorizontalScroll hScroll;
	private TableroTouchListener tListener;
	private RelativeLayout tablero;
	
	private int screenWidth;
	private int screenHeight;
	
	private Targeta[] targetes;
	private Partida partida;
	private ImageView[] fitxes;
	
	private int targetaWidth = 125;
	private int targetaHeight = 200;
	private int containerHeight = 300;
	private int indicatorHeight = 20;
	private int indicatorWidth = 192;
	private int fitxaSize = 40;
	
	private int taulellSize;
	
	private static Taulell INSTANCE = null;

	@SuppressWarnings("deprecation")
	public Taulell(GameActivity context, Targeta[] targets) {
		super(context);
		this.context = context;
		targetes = targets;
		this.setClipChildren(false);
		
		Display display = context.getWindowManager().getDefaultDisplay(); 
		screenWidth = display.getWidth();  
		screenHeight = display.getHeight();  
		
		vScroll = new VerticalScroll(context);
		hScroll = new HorizontalScroll(context);
		hScroll.addView(vScroll);
		this.addView(hScroll);

		tListener = new TableroTouchListener(hScroll, vScroll);
		this.setOnTouchListener(tListener);	
		
		taulellSize = (containerHeight * 2) + (targetaWidth * 14);
		
		this.Construct();
		
		INSTANCE = this;
	}
	
	public void setPartida(Partida partida){
		this.partida = partida;
	}
	
	private void Construct(){
		Log.d("MONOPOLIDOS", "Taulell -> construct()");
		LinearLayout verticalLayout = new LinearLayout(context);
		verticalLayout.setOrientation(LinearLayout.VERTICAL);
		
		tablero = new RelativeLayout(context);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(taulellSize, taulellSize);
		tablero.setLayoutParams(params);
		tablero.setClipChildren(false);
		tablero.setClipChildren(false);
		
		for(int i = 1; i <= 60; i++){
			Point position = GetTargetaPosition(i);
			RelativeLayout container = new RelativeLayout(context);
			container.setClipChildren(false);
			container.setClipToPadding(false);
			ImageView imgTargeta = null;
			RelativeLayout indicator = null;
			
			if(i==1 || i == 16 || i == 31 || i == 46){
				params = new RelativeLayout.LayoutParams(containerHeight, containerHeight);
				if(i == 1){
					imgTargeta = createField(targetes[i-1], targetaHeight, targetaHeight, 0, containerHeight - targetaHeight);
				}
				else if(i==16){
					imgTargeta = createField(targetes[i-1], targetaHeight, targetaHeight, containerHeight - targetaHeight, containerHeight - targetaHeight);
				}
				else if(i == 31){
					imgTargeta = createField(targetes[i-1], targetaHeight, targetaHeight, containerHeight - targetaHeight, 0);
				}
				else {
					imgTargeta = createField(targetes[i-1], targetaHeight, targetaHeight, 0, 0);
				}
			}
			else if(i > 1 && i < 16){
				params = new RelativeLayout.LayoutParams(containerHeight, targetaWidth);
				imgTargeta = createField(targetes[i-1], targetaHeight, targetaWidth, 0, containerHeight - targetaHeight);
				indicator = createIndicator(indicatorHeight, indicatorWidth, 4, 40);
			}
			else if(i > 16 && i < 31){
				params = new RelativeLayout.LayoutParams(targetaWidth, containerHeight);
				imgTargeta = createField(targetes[i-1], targetaWidth, targetaHeight, containerHeight - targetaHeight, 0);
				indicator = createIndicator(indicatorWidth, indicatorHeight, 40, 4);
			}
			else if(i > 31 && i < 46){
				params = new RelativeLayout.LayoutParams(containerHeight, targetaWidth);
				imgTargeta = createField(targetes[i-1], targetaHeight, targetaWidth, 0, 0);
				indicator = createIndicator(indicatorHeight, indicatorWidth, 4, containerHeight - indicatorHeight - 40);
			}
			else if(i > 46){
				params = new RelativeLayout.LayoutParams(targetaWidth, containerHeight);
				imgTargeta = createField(targetes[i-1], targetaWidth, targetaHeight, 0, 0);
				indicator = createIndicator(indicatorWidth, indicatorHeight, containerHeight - indicatorHeight - 40, 4);
			}
			
			params.leftMargin = position.x;
			params.topMargin = position.y;
			container.setLayoutParams(params);
			
			if(imgTargeta != null){
				container.addView(imgTargeta);
				if(indicator != null){
					container.addView(indicator);
				}
			}
			
			tablero.addView(container);
			
		}
		
		verticalLayout.addView(tablero);
		vScroll.addView(verticalLayout);
	}
	
	private Point GetTargetaPosition(int i){
		Point p = new Point();
		
		if(i >= 1 && i <= 15)
		{
			p.x = 0;
			p.y = containerHeight + ((15 - i)*targetaWidth);
		}
		else if(i >= 16 && i <= 30)
		{
			i = i - 15;
			p.y = 0;
			if(i==1){
				p.x = 0;
			}
			else {
				p.x = containerHeight + ((i-2)*targetaWidth);
			}
		}
		else if( i >= 31 && i <= 45)
		{
			i = i - 30;
			p.x = containerHeight + (14*targetaWidth);
			if(i==1){
				p.y = 0;
			}
			else {
				p.y = containerHeight + ((i - 2)*targetaWidth);
			}
		}
		else if( i >=46 && i <= 60)
		{
			p.y = containerHeight + (14*targetaWidth);
			i = i - 45;
			p.x = containerHeight + ((15 - i)*targetaWidth);
		}
		
		return p;
	}
	
	private RelativeLayout createIndicator(int width, int height, int top, int left){
		RelativeLayout indicator = new RelativeLayout(context);
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
		params.topMargin = top;
		params.leftMargin = left;
		indicator.setLayoutParams(params);
		indicator.setBackgroundColor(Color.WHITE);
		
		return indicator;
	}
	
	private ImageView createField(Targeta t, int width, int height, int top, int left){
        InputStream ims = null;
		ImageView img = new ImageView(context);
		try {
			ims = context.getAssets().open(t.gettImatge());
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
			params.topMargin = top;
			params.leftMargin = left;
			img.setLayoutParams(params);
			Bitmap bmp = BitmapFactory.decodeStream(ims);
			
			Bitmap resizedBitmap = Bitmap.createScaledBitmap(bmp, width, height, true);
			
			img.setImageBitmap(resizedBitmap); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return img;
	}
	
	private ImageView createBlankField(){
        InputStream ims = null;
		ImageView img = new ImageView(context);
		try {
			ims = context.getAssets().open("others/blank.png");
			img.setLayoutParams(new LayoutParams(targetaWidth, targetaWidth));
			Bitmap bmp = BitmapFactory.decodeStream(ims);
			
			Bitmap resizedBitmap = Bitmap.createScaledBitmap(bmp, targetaWidth, targetaWidth, true);
			
			img.setImageBitmap(resizedBitmap); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return img;
	}

	public void changeIndicatorColor(int position, int playerTorn){
		RelativeLayout container = (RelativeLayout) tablero.getChildAt(position-1);
		if(container.getChildCount() == 2){
			RelativeLayout indicator = (RelativeLayout) container.getChildAt(1);
			indicator.setBackgroundColor(Color.BLUE);
		}
	}
	
	public static Taulell getINSTANCE() {
		return INSTANCE;
	}

	public void ColocaFitxes(){
		ArrayList<Player> players= partida.getpPlayers();
		
		fitxes = new ImageView[players.size()];
		for(int i = 0; i < players.size(); i++){
			Player p = players.get(i);
			int fitxa = GetPlayerFitxa(p.getpTorn());
			int pos = p.getpPosition();
			
			Point positionTargeta = GetTargetaPosition(pos);
			Point position = GetFitxaPositionInTargeta(pos);
			ImageView imgFitxa = CreateFitxa(position.y + positionTargeta.y, position.x + positionTargeta.x, fitxa);
			tablero.addView(imgFitxa);
			fitxes[p.getpTorn()] = imgFitxa;
		}
	}
	
	public void MoveFitxa(int posActual, int posFinal, int tir, int cont){
		posActual++;
		if(posActual > 60)
			posActual = posActual - 60;
		
		Log.d("MONOPOLIDOS", "MoveFitxa from " + posActual + " to " + posFinal);
		if(cont <= tir){
			Player player = partida.GetPlayerTorn();
			ImageView fitxa = fitxes[player.getpTorn()];
			
			RelativeLayout.LayoutParams params = (LayoutParams) fitxa.getLayoutParams();
			int topAnt = params.topMargin;
			int leftAnt = params.leftMargin;
			
			Point posicio = GetTargetaPosition(posActual);
			Point posicioF = GetFitxaPositionInTargeta(posActual);
			
			int top = posicio.y + posicioF.y;
			int left = posicio.x + posicioF.x;
			
			int movY = top - topAnt;
			int movX = left - leftAnt;
			
			CreateAnimation(movX, movY, top, left, tir, cont, posActual, posFinal);
		}
		else {
			context.acabarTirada(posFinal);
		}
	}
	
	private void CreateAnimation(final int movX, final int movY, final int top, final int left, final int tir, final int cont, final int posActual, final int posFinal){
		Player player = partida.GetPlayerTorn();
		final ImageView fitxa = fitxes[player.getpTorn()];
		
		ScaleAnimation sAnim = new ScaleAnimation(1, (float)1.5, 1, (float) 1.5);
		sAnim.setDuration(500);
		
		TranslateAnimation anim = new TranslateAnimation(0, movX, 0, movY);
		anim.setFillAfter(true);
		anim.setDuration(1000);
		anim.setAnimationListener(new Animation.AnimationListener(){
		    @Override
		    public void onAnimationStart(Animation arg0) {
		    }           
		    @Override
		    public void onAnimationRepeat(Animation arg0) {
		    }           
		    @Override
		    public void onAnimationEnd(Animation arg0) {
		    	RelativeLayout.LayoutParams params = (LayoutParams) fitxa.getLayoutParams();
		    	params.topMargin = top;
		    	params.leftMargin = left;
		    	if(cont <= tir){
			    	Log.d("MONOPOLIDOS", "onAnimationEnd()");
		    		fitxa.setLayoutParams(params);
		    	}

		    	MoveFitxa(posActual, posFinal, tir, cont + 1);
		    }
		});
		
		AnimationSet animations = new AnimationSet(false);
		animations.addAnimation(sAnim);
		animations.addAnimation(anim);
		animations.setFillAfter(false);
		
		fitxa.startAnimation(animations);
	}
	
	private ImageView CreateFitxa(int top, int left, int fitxa){
		ImageView fitxaimg = new ImageView(context);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(fitxaSize, fitxaSize);
		params.topMargin = top;
		params.leftMargin = left;
		fitxaimg.setLayoutParams(params);
		
		Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), fitxa);
		Bitmap scaledBmp = Bitmap.createScaledBitmap(bmp, fitxaSize, fitxaSize, true);
		fitxaimg.setImageBitmap(scaledBmp);
		
		return fitxaimg;
	}
	
	private Point GetFitxaPositionInTargeta(int i){
		Point p = new Point();
		Random aleatori = new Random();
		if(i==1 || i == 16 || i == 31 || i == 46){
			if(i == 1){
				p.x = aleatori.nextInt(targetaHeight - fitxaSize - 10) + (containerHeight - targetaHeight + 5);
				p.y = aleatori.nextInt(targetaHeight - fitxaSize - 10) + 5;
			}
			else if(i==16){
				p.x = 80;
				//p.x = aleatori.nextInt(targetaHeight - fitxaSize - 10) + (containerHeight - targetaHeight + 5);
				p.y = aleatori.nextInt(targetaHeight - fitxaSize - 10) + (containerHeight - targetaHeight + 5);
			}
			else if(i == 31){
				p.x = aleatori.nextInt(targetaHeight - fitxaSize - 10) + 5;
				p.y = aleatori.nextInt(targetaHeight - fitxaSize - 10) + (containerHeight - targetaHeight + 5);
			}
			else {
				p.x = aleatori.nextInt(targetaHeight - fitxaSize - 10) + 5;
				p.y = aleatori.nextInt(targetaHeight - fitxaSize - 10) + 5;
			}
		}
		else if(i > 1 && i < 16){
			p.x = aleatori.nextInt(targetaHeight - fitxaSize - 10) + (containerHeight - targetaHeight + 5);
			p.y = aleatori.nextInt(targetaWidth - fitxaSize - 10) + 5;
		}
		else if(i > 16 && i < 31){
			p.x = aleatori.nextInt(targetaWidth - fitxaSize - 10) + 5;
			p.y = aleatori.nextInt(targetaHeight - fitxaSize - 10) + (containerHeight - targetaHeight + 5);
		}
		else if(i > 31 && i < 46){
			p.x = aleatori.nextInt(targetaHeight - fitxaSize - 10) + 5;
			p.y = aleatori.nextInt(targetaWidth - fitxaSize - 10) + 5;
		}
		else if(i > 46){
			p.x = aleatori.nextInt(targetaWidth - fitxaSize - 10) + 5;;
			p.y = aleatori.nextInt(targetaHeight - fitxaSize - 10) + 5;
		}
		
		return p;
	}
	
	private int TargetaTeFitxes(int pos){
		RelativeLayout container = (RelativeLayout) tablero.getChildAt(pos-1);
		int retorn = 0;
		
		if(pos==1 || pos == 16 || pos == 31 || pos == 46){
			retorn = (container.getChildCount() - 1);
		}
		else {
			retorn = (container.getChildCount() - 2);
		}
		
		return retorn;
	}
	
	private int GetPlayerFitxa(int playerTorn){
		int f = -1;
		
		if(playerTorn == 0){
			f = R.drawable.fvermella;
		}
		else if (playerTorn == 1) {
			f = R.drawable.fblava;
		}
		else if (playerTorn == 2) {
			f = R.drawable.fgroga;
		}
		else if (playerTorn == 3) {
			f = R.drawable.fverda;
		}
		else{
			f = R.drawable.frosa;
		}
		
		return f;
	}
	
	private int GetPlayerColor(int playerTorn){
		int c = -1;
		
		if(playerTorn == 0){
			c = Color.RED;
		}
		else if (playerTorn == 1) {
			c = Color.BLUE;
		}
		else if (playerTorn == 2) {
			c = Color.YELLOW;
		}
		else if (playerTorn == 3) {
			c = Color.GREEN;
		}
		else{
			c = Color.MAGENTA;
		}
		
		return c;
	}

	public void ClearTaulell(){
		for(int i = 0; i < fitxes.length; i++){
			ViewGroup parent = (ViewGroup) fitxes[i].getParent();
			parent.removeView(fitxes[i]);
		}
	}

	public void ScrollToUserPosition(){
		Player player = partida.GetPlayerTorn();
		int pos = player.getpPosition();
		final Point p = GetTargetaPosition(pos);
		
		vScroll.post(new Runnable() {
			
			@Override
			public void run() {
				hScroll.scrollTo(p.x, p.y);
				vScroll.scrollTo(p.x, p.y);
			}
		});
	}

	public Targeta GetTargetaOfPosition(int position){
		return targetes[position -1];
	}
}
