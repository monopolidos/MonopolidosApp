package epqp.monopolidos.dialogs;

import epqp.monopolidos.asynctasks.DaiceTask;
import epqp.monopolidos.game.GameActivity;
import epqp.monopolidos.game.R;
import epqp.monopolidos.objects.Partida;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class DaiceDialog extends Dialog {
	
	private GameActivity context;
	private ImageView daice;
	
	private int[] nums = new int[]{R.drawable.daice1, R.drawable.daice2, R.drawable.daice3, R.drawable.daice4, R.drawable.daice5, R.drawable.daice6};
	
	private int screenWidth;
	private int dialogHeight;
	private int dialogWidth;
	
	private DaiceTask dTask;
	private Partida partida;

	public DaiceDialog(GameActivity context, Partida partida) {
		super(context);
		Log.d("MONOPOLIDOS", "new DaiceDialog()");
		this.context = context;
		this.partida = partida;
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		RelativeLayout rl = new RelativeLayout(context);
		setContentView(rl);
		
		init(context);
		
		rl.setBackgroundColor(Color.GRAY);
		
		daice = new ImageView(context);
		
		FrameLayout.LayoutParams paramsF = new FrameLayout.LayoutParams(dialogWidth, dialogHeight);
		rl.setLayoutParams(paramsF);
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int) (dialogWidth *0.5), (int) (dialogHeight * 0.5));
		params.leftMargin = (int) (dialogWidth *0.25);
		params.topMargin = (int) (dialogHeight * 0.25);
		daice.setLayoutParams(params);
		
		rl.addView(daice);

		dTask = new DaiceTask(this, partida, context);
		dTask.execute();
	}
	
	@SuppressWarnings("deprecation")
	private void init(Context context){
		Display display = ((Activity)context).getWindowManager().getDefaultDisplay(); 
		screenWidth = display.getWidth();  

		LayoutParams params = getWindow().getAttributes();
		params.y = -40;
		getWindow().setAttributes(params);
		
		dialogWidth = (int) (screenWidth * 0.75);
		dialogHeight = (int) (screenWidth * 0.75);
	}

	public void setDaiceImage(final int numActual){
		
		context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
        		daice.setImageResource(nums[numActual-1]);
            }
        });
		
	}

	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			if(dTask.DaiceIsRodant()){
				Log.d("MONOPOLIDOS", "DaiceDialog -> onTouchEvent()");
				dTask.changeIsRodant();
			}
		}
		return true;
	}

	public void onBackPressed(){
		Log.d("MONOPOLIDOS", "DaiceDialog -> onBackPressed()");
		dTask.cancel(true);
		this.hide();
	}
	
}
