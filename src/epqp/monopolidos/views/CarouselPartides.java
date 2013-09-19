package epqp.monopolidos.views;

import java.util.ArrayList;

import epqp.monopolidos.adapter.ListPartEsperaAdapter;
import epqp.monopolidos.adapter.ListPartNoTornAdapter;
import epqp.monopolidos.adapter.ListPartTornAdapter;
import epqp.monopolidos.game.PartidesActivity;
import epqp.monopolidos.game.R;
import epqp.monopolidos.listeners.ListPartidesEsperaItemClickListener;
import epqp.monopolidos.listeners.ListPartidesNoTornItemClickListener;
import epqp.monopolidos.listeners.ListPartidesTornItemClickListener;
import epqp.monopolidos.objects.Partida;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CarouselPartides extends HorizontalScrollView {

	private LinearLayout linearLayout = null;
	private RelativeLayout partidesTornParent = null;
	private RelativeLayout partidesNoTornParent = null;
	private RelativeLayout partidesEspera = null;
	
	private int screenHeight;
	private int screenWidth;
	
	private int height;
	private int width;
	
	private int listHeight;
	private int listWidth;
	
	private float startX;
	private float initX;
	private int current;
	
	private ListView listPartidesEspera;
	private ListPartEsperaAdapter partEsperaAdapter;
	private ListPartidesEsperaItemClickListener partEsperaListener;
	
	private ListView listPartidesTorn;
	private ListPartTornAdapter partTornAdapter;
	private ListPartidesTornItemClickListener partTornListener;
	
	private ListView listPartidesNoTorn;
	private ListPartNoTornAdapter partNoTornAdapter;
	private ListPartidesNoTornItemClickListener partNoTornListener;
	
	
	private PartidesActivity context;
	
	@SuppressWarnings("deprecation")
	public CarouselPartides(Context context) {
		super(context);
		this.context = (PartidesActivity)context;
		current = 1;
		
		Display display = this.context.getWindowManager().getDefaultDisplay(); 
		screenWidth = display.getWidth();  
		screenHeight = display.getHeight() - 80 - getStatusBarHeight(); 
		
		linearLayout = new LinearLayout(context);
		linearLayout.setOrientation(LinearLayout.HORIZONTAL);
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(screenWidth, screenHeight);
		params.leftMargin = 0;
		params.topMargin = 0;
		this.setLayoutParams(params);
		
		height = (int) (screenHeight * 0.86);
		width = (int) (screenWidth * 0.8);
		listHeight = (int)(height * 0.74);
		listWidth = (int) (width * 0.9);
		
		this.setHorizontalScrollBarEnabled(false);
		
		construct();

		final CarouselPartides me = this;

		me.post(new Runnable() {     
		    @Override     
		    public void run() {         
		        me.scrollTo(current * screenWidth, 0);     
		    }  
		});
		
	}
	
	public boolean dispatchTouchEvent(MotionEvent ev)
	{
		  boolean ret = super.dispatchTouchEvent(ev);
		  if(ret) 
		  {
			  requestDisallowInterceptTouchEvent(true);
		  }
		  return ret;
	}  
	
	private void construct(){
		partidesEspera = new RelativeLayout(this.context);
		partidesEspera.setLayoutParams(new LayoutParams(screenWidth, screenHeight));
		partidesEspera.addView(constructPartidesEspera());
		linearLayout.addView(partidesEspera);
		
		partidesTornParent = new RelativeLayout(this.context);
		partidesTornParent.setLayoutParams(new LayoutParams(screenWidth, screenHeight));
		partidesTornParent.addView(constructPartidesTorn());
		linearLayout.addView(partidesTornParent);
		
		partidesNoTornParent = new RelativeLayout(this.context);
		partidesNoTornParent.setLayoutParams(new LayoutParams(screenWidth, screenHeight));
		partidesNoTornParent.addView(constructPartidesNoTorn());
		linearLayout.addView(partidesNoTornParent);
		
		this.addView(linearLayout);
	}
	
	private RelativeLayout constructPartidesEspera(){
		RelativeLayout rl = new RelativeLayout(this.context);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
		params.topMargin = (int) (screenHeight * 0.07);
		params.leftMargin = (int) (screenWidth * 0.1);
		rl.setLayoutParams(params);
		rl.setBackgroundResource(R.drawable.partides);
		
		RelativeLayout header = new RelativeLayout(this.context);
		params = new RelativeLayout.LayoutParams((int)(width * 0.9), (int) (height * 0.15));
		params.topMargin = (int) (height * 0.03);
		params.leftMargin = (int) (width * 0.05);
		header.setLayoutParams(params);
		header.setBackgroundResource(R.drawable.partidesespera);
		
		TextView title = new TextView(this.context);
		title.setText("NOT CONFIRMED");
		title.setTextAppearance(this.context, R.style.LabelText);
		params = new RelativeLayout.LayoutParams((int)(width * 0.9), (int) (height * 0.15));
		title.setGravity(Gravity.CENTER);
		title.setLayoutParams(params);
		float densityMultiplier = getContext().getResources().getDisplayMetrics().density;
		float scaledPx = (float) (height * 0.15 * 0.3  / densityMultiplier);
		title.setTextSize(scaledPx);
		
		header.addView(title);
		rl.addView(header);	
		
		listPartidesEspera = new ListView(context);
		params = new RelativeLayout.LayoutParams(listWidth, listHeight);
		params.topMargin = (int) (height * 0.21);
		params.leftMargin = (int) (width * 0.05);
		listPartidesEspera.setLayoutParams(params);
		
		rl.addView(listPartidesEspera);
		
		return rl;
	}
	
	private RelativeLayout constructPartidesTorn(){
		RelativeLayout rl = new RelativeLayout(this.context);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
		params.topMargin = (int) (screenHeight * 0.07);
		params.leftMargin = (int) (screenWidth * 0.1);
		rl.setLayoutParams(params);
		rl.setBackgroundResource(R.drawable.partides);
		
		RelativeLayout header = new RelativeLayout(this.context);
		params = new RelativeLayout.LayoutParams((int)(width * 0.9), (int) (height * 0.15));
		params.topMargin = (int) (height * 0.03);
		params.leftMargin = (int) (width * 0.05);
		header.setLayoutParams(params);
		header.setBackgroundResource(R.drawable.partidestorn);
		
		TextView title = new TextView(this.context);
		title.setText("YOUR TURN");
		title.setTextAppearance(this.context, R.style.LabelText);
		params = new RelativeLayout.LayoutParams((int)(width * 0.9), (int) (height * 0.15));
		title.setGravity(Gravity.CENTER);
		title.setLayoutParams(params);
		float densityMultiplier = getContext().getResources().getDisplayMetrics().density;
		float scaledPx = (float) (height * 0.15 * 0.3  / densityMultiplier);
		title.setTextSize(scaledPx);
		
		header.addView(title);
		rl.addView(header);	
		
		listPartidesTorn = new ListView(context);
		params = new RelativeLayout.LayoutParams(listWidth, listHeight);
		params.topMargin = (int) (height * 0.21);
		params.leftMargin = (int) (width * 0.05);
		listPartidesTorn.setLayoutParams(params);
		
		rl.addView(listPartidesTorn);
		
		return rl;
	}
	
	private RelativeLayout constructPartidesNoTorn(){
		RelativeLayout rl = new RelativeLayout(this.context);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
		params.topMargin = (int) (screenHeight * 0.07);
		params.leftMargin = (int) (screenWidth * 0.1);
		rl.setLayoutParams(params);
		rl.setBackgroundResource(R.drawable.partides);
		
		RelativeLayout header = new RelativeLayout(this.context);
		params = new RelativeLayout.LayoutParams((int)(width * 0.9), (int) (height * 0.15));
		params.topMargin = (int) (height * 0.03);
		params.leftMargin = (int) (width * 0.05);
		header.setLayoutParams(params);
		header.setBackgroundResource(R.drawable.partidesnotorn);
		
		TextView title = new TextView(this.context);
		title.setText("TURN OF OTHERS");
		title.setTextAppearance(this.context, R.style.LabelText);
		params = new RelativeLayout.LayoutParams((int)(width * 0.9), (int) (height * 0.15));
		title.setGravity(Gravity.CENTER);
		title.setLayoutParams(params);
		float densityMultiplier = getContext().getResources().getDisplayMetrics().density;
		float scaledPx = (float) (height * 0.15 * 0.3  / densityMultiplier);
		title.setTextSize(scaledPx);
		
		header.addView(title);
		rl.addView(header);	
		
		listPartidesNoTorn = new ListView(context);
		params = new RelativeLayout.LayoutParams(listWidth, listHeight);
		params.topMargin = (int) (height * 0.21);
		params.leftMargin = (int) (width * 0.05);
		listPartidesNoTorn.setLayoutParams(params);
		
		rl.addView(listPartidesNoTorn);
		
		return rl;
	}
	
	public boolean onTouchEvent(MotionEvent event) {
		float touchX;
		float distance;
	
	    switch (event.getAction()) {
	        case MotionEvent.ACTION_DOWN:
	            startX = event.getX();
	            initX = startX;
	            break;
	        case MotionEvent.ACTION_MOVE:
	            touchX = event.getX();
	        	distance = startX - touchX;
	            this.scrollBy((int) (distance), 0);
	            startX = touchX;
	            break;
	        case MotionEvent.ACTION_UP:
	            touchX = event.getX();
	        	distance = initX - touchX;
	        	
	        	if(distance > 0){
		        	if(distance > screenWidth * 0.4){
		        		if(current < 2){
		        			current++;
		        			this.scrollTo(current * screenWidth, 0);
		        		}
		        	}
		        	else {
	        			this.scrollTo(current * screenWidth, 0);
		        	}
	        	}
	        	else {
	        		distance = distance * -1;
		        	if(distance > screenWidth * 0.4){
		        		if(current > 0){
			        		current--;
		        			this.scrollTo(current * screenWidth, 0);
		        		}
		        	}
		        	else {
	        			this.scrollTo(current * screenWidth, 0);
		        	}
	        	}
	        	break;
	    }
	    
		return true;
	}
	
	public void refreshData(ArrayList<Partida> partidesEspera, ArrayList<Partida> partidesTorn, ArrayList<Partida> partidesNoTorn){
		partEsperaAdapter = new ListPartEsperaAdapter(context, partidesEspera, listWidth, listHeight);
		partEsperaListener = new ListPartidesEsperaItemClickListener(context, partEsperaAdapter);
		listPartidesEspera.setAdapter(partEsperaAdapter);
		listPartidesEspera.setOnItemClickListener(partEsperaListener);
		
		partTornAdapter = new ListPartTornAdapter(context, partidesTorn, listWidth, listHeight);
		partTornListener = new ListPartidesTornItemClickListener(context, partTornAdapter);
		listPartidesTorn.setAdapter(partTornAdapter);
		listPartidesTorn.setOnItemClickListener(partTornListener);

		partNoTornAdapter = new ListPartNoTornAdapter(context, partidesNoTorn, listWidth, listHeight);
		partNoTornListener = new ListPartidesNoTornItemClickListener(context, partNoTornAdapter);
		listPartidesNoTorn.setAdapter(partNoTornAdapter);		
		listPartidesNoTorn.setOnItemClickListener(partNoTornListener);
		
		final CarouselPartides me = this;

		me.post(new Runnable() {     
		    @Override     
		    public void run() {         
		        me.scrollTo(current * screenWidth, 0);     
		    }  
		});
	}
	
	private int getStatusBarHeight() {
		int result = 0;
		int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}

}
