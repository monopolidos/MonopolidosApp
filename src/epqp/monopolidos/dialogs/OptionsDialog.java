package epqp.monopolidos.dialogs;

import epqp.monopolidos.game.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class OptionsDialog extends Dialog{

	private RelativeLayout optionsDialogLayot = null;
	private ImageView icon = null;
	private CheckBox chNot = null;
	private CheckBox ch1 = null;
	private TextView txtNot = null;
	private TextView txtSound = null;
	
	private int screenHeight;
	private int screenWidth;
	private int dialogWidth;
	private int dialogHeight;
	
	public OptionsDialog(Context context) {
		super(context);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.dialog_options);
		
		init(context);
		
		optionsDialogLayot = (RelativeLayout)findViewById(R.id.dialogOptionsLayout);
		icon = (ImageView)findViewById(R.id.iconmonop);
		chNot = (CheckBox)findViewById(R.id.chk);
		ch1 = (CheckBox)findViewById(R.id.ch1);
		txtNot = (TextView)findViewById(R.id.txtNot);
		txtSound = (TextView)findViewById(R.id.txtSound);
		
		chNot.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				if (arg1){
					Log.d("MONOPOLIDOS", "Not off");
				} else {
					Log.d("MONOPOLIDOS", "Not on");
				}
			}
		});
		
		ch1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				if (arg1){
					Log.d("MONOPOLIDOS", "Sound off");
				} else {
					Log.d("MONOPOLIDOS", "Sound on");
				}
			}
		});
		
		designWindow();
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	}
	
	@SuppressWarnings("deprecation")
	
	private void init(Context context){
		Display display = ((Activity)context).getWindowManager().getDefaultDisplay(); 
		screenWidth = display.getWidth();  
		screenHeight = display.getHeight() - 80; 

		LayoutParams params = getWindow().getAttributes();
		params.y = -40;
		getWindow().setAttributes(params);
		
		Log.d("MONOPOLIDOS", "Params.width dialog -> " + params.width);
		
		dialogWidth = (int) (screenWidth * 0.9 );
		dialogHeight = (int) (screenHeight * 0.9 );
	}
	
	private void designWindow(){
		FrameLayout.LayoutParams paramsf = new android.widget.FrameLayout.LayoutParams((int) (dialogWidth), (int) (dialogHeight));
		optionsDialogLayot.setLayoutParams(paramsf);
		
		RelativeLayout.LayoutParams params = new android.widget.RelativeLayout.LayoutParams((int) (dialogWidth * 0.84), (int) (dialogHeight * 0.1));
		params.topMargin = (int) (dialogHeight * 0.65);
		params.leftMargin = (int) (dialogWidth * 0.6);
		chNot.setLayoutParams(params);
		
		params = new android.widget.RelativeLayout.LayoutParams((int) (dialogWidth * 0.84), (int) (dialogHeight * 0.1));
		params.topMargin = (int) (dialogHeight * 0.66);
		params.leftMargin = (int) (dialogWidth * 0.1);
		txtSound.setLayoutParams(params);
		
		params = new android.widget.RelativeLayout.LayoutParams((int) (dialogWidth * 0.84), (int) (dialogHeight * 0.1));
		params.topMargin = (int) (dialogHeight * 0.50);
		params.leftMargin = (int) (dialogWidth * 0.6);
		ch1.setLayoutParams(params);
		
		params = new android.widget.RelativeLayout.LayoutParams((int) (dialogWidth * 0.84), (int) (dialogHeight * 0.1));
		params.topMargin = (int) (dialogHeight * 0.51);
		params.leftMargin = (int) (dialogWidth * 0.1);
		txtNot.setLayoutParams(params);
		
	}

}
