package epqp.monopolidos.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import epqp.monopolidos.game.R;

public class LoadingDialog extends Dialog {
	
	private int screenHeight;
	
	private Activity context;

	public LoadingDialog(Activity context) {
		super(context);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.context = context;
		init("Loading");
	}
	
	public LoadingDialog(Activity context, String text){
		super(context);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.context = context;
		init(text);
	}
	
	public void onBackPressed(){
		//NOTHING
	}
	
	@SuppressWarnings("deprecation")
	private void init(String text){		
		Display display = ((Activity)context).getWindowManager().getDefaultDisplay(); 
		screenHeight = display.getHeight()-getStatusBarHeight(); 
		setContentView(R.layout.dialog_loading);
		
		RelativeLayout rl = (RelativeLayout)findViewById(R.id.layoutLoading);
		ProgressBar pb = (ProgressBar)findViewById(R.id.progressbarLoading);
		TextView textview = (TextView)findViewById(R.id.txtLoading);
		
		int dialogHeight = (int) (screenHeight * 0.18);
		
		FrameLayout.LayoutParams paramsF = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, dialogHeight);
		rl.setLayoutParams(paramsF);
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int) (dialogHeight * 0.7), (int) (dialogHeight*0.7));
		params.topMargin = (int) (dialogHeight *0.15);
		params.leftMargin = (int) (dialogHeight * 0.15);
		pb.setLayoutParams(params);
		
		textview.setText(text + "  ");
		float densityMultiplier = getContext().getResources().getDisplayMetrics().density;
		float scaledPx = (float) (dialogHeight * 0.9 * 0.3  / densityMultiplier);
		textview.setTextSize(scaledPx);
		params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, (int) (dialogHeight*0.9));
		params.topMargin = (int) (dialogHeight *0.05);
		params.leftMargin = (int) (dialogHeight);
		params.rightMargin = (int)(dialogHeight * 0.05);
		textview.setLayoutParams(params);
		textview.setGravity(Gravity.CENTER_VERTICAL);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
	    return false;
	}
	
	private int getStatusBarHeight() {
		int result = 0;
		int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = context.getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}

}
