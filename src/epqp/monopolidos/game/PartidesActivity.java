package epqp.monopolidos.game;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import epqp.monopolidos.asynctasks.GetPartidesTask;
import epqp.monopolidos.listeners.MenuClickListener;
import epqp.monopolidos.views.CarouselPartides;

public class PartidesActivity extends Activity {

	private CarouselPartides partidesCarousel = null;
	private RadioButton rbAdd = null;
	private RadioButton rbOptions = null;
	private RadioButton rbLogout = null;
	private RelativeLayout parentLayout = null;
	
	private int screenHeight;
	private int screenWidth;
	
	private MenuClickListener mClickListener;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("MONOPOLIDOS", "PartidaActivity - onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_partides);
		
		Display display = getWindowManager().getDefaultDisplay(); 
		screenWidth = display.getWidth();  
		screenHeight = display.getHeight(); 
		
		mClickListener = new MenuClickListener(this);
		
		partidesCarousel = new CarouselPartides(this);
		parentLayout = (RelativeLayout)findViewById(R.id.parentLayoutPartides);
		rbAdd = (RadioButton)findViewById(R.id.btnAdd);
		rbOptions = (RadioButton)findViewById(R.id.btnOptions);
		rbLogout = (RadioButton)findViewById(R.id.btnLogout);
		
		parentLayout.addView(partidesCarousel);
		
		rbAdd.setOnCheckedChangeListener(mClickListener);
		rbOptions.setOnCheckedChangeListener(mClickListener);
		rbLogout.setOnCheckedChangeListener(mClickListener);
		
	}
	
	protected void onResume(){
		super.onResume();
		Log.d("MONOPOLIDOS", "PartidesActivity - onResume()");
		refreshData();
	}
	
	private void refreshData(){
		new GetPartidesTask(partidesCarousel).execute();
	}
	
	public CarouselPartides getCarouselPartides(){
		return this.partidesCarousel;
	}
}
