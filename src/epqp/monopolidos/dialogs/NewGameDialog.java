package epqp.monopolidos.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import epqp.monopolidos.asynctasks.NewGameTask;
import epqp.monopolidos.game.PartidesActivity;
import epqp.monopolidos.game.R;
import epqp.monopolidos.objects.Sessio;
import epqp.monopolidos.objects.User;

public class NewGameDialog extends Dialog {
	
	private int screenHeight;
	private int screenWidth;
	
	private int dialogWidth;
	private int dialogHeight;

	private EditText txtPlayer1 = null;
	private EditText txtPlayer2 = null;
	private EditText txtPlayer3 = null;
	private EditText txtPlayer4 = null;
	private Button btnSendRequests = null;
	private RelativeLayout dialogLayout = null;
	private ImageView icon = null;
	
	public NewGameDialog(final PartidesActivity c) {
		super(c); 
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.dialog_new_game);

		init(c);
		
		txtPlayer1 = (EditText)findViewById(R.id.txtPlayer1);
		txtPlayer2 = (EditText)findViewById(R.id.txtPlayer2);
		txtPlayer3 = (EditText)findViewById(R.id.txtPlayer3);
		txtPlayer4 = (EditText)findViewById(R.id.txtPlayer4);
		btnSendRequests = (Button)findViewById(R.id.btnConfirm);
		dialogLayout = (RelativeLayout)findViewById(R.id.dialogLayout);
		icon = (ImageView)findViewById(R.id.iconmonop);
		
		dialogLayout.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				hideKeyboard(v);
		        return false;
			}
		});
		
		
		btnSendRequests.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				User u = Sessio.getInstance().getUser();
				
				new NewGameTask(c, NewGameDialog.this).execute(u.getuName(), txtPlayer1.getText().toString().trim(), txtPlayer2.getText().toString().trim(), txtPlayer3.getText().toString().trim(), txtPlayer4.getText().toString().trim());
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
		dialogLayout.setLayoutParams(paramsf);
		
		RelativeLayout.LayoutParams params = new android.widget.RelativeLayout.LayoutParams((int) (dialogWidth * 0.8), (int) (dialogHeight * 0.1));
		params.topMargin = (int) (dialogHeight * 0.25);
		params.leftMargin = (int) (dialogWidth * 0.1);
		txtPlayer1.setLayoutParams(params);
		
		params = new android.widget.RelativeLayout.LayoutParams((int) (dialogWidth * 0.8), (int) (dialogHeight * 0.1));
		params.topMargin = (int) (dialogHeight * 0.40);
		params.leftMargin = (int) (dialogWidth * 0.1);
		txtPlayer2.setLayoutParams(params);
		
		params = new android.widget.RelativeLayout.LayoutParams((int) (dialogWidth*0.8), (int) (dialogHeight * 0.1));
		params.topMargin = (int) (dialogHeight * 0.55);
		params.leftMargin = (int) (dialogWidth * 0.1);
		txtPlayer3.setLayoutParams(params);
		
		params = new android.widget.RelativeLayout.LayoutParams((int) (dialogWidth * 0.8), (int) (dialogHeight * 0.1));
		params.topMargin = (int) (dialogHeight * 0.70);
		params.leftMargin = (int) (dialogWidth * 0.1);
		txtPlayer4.setLayoutParams(params);
		
		params = new android.widget.RelativeLayout.LayoutParams((int) (dialogWidth * 0.8), (int) (dialogHeight * 0.25));
		params.topMargin = (int) (dialogHeight * 0.01);
		params.leftMargin = (int) (dialogWidth * 0.1);
		icon.setLayoutParams(params);
		
		txtPlayer1.requestFocus();
		
		params = new android.widget.RelativeLayout.LayoutParams((int) (dialogWidth * 0.84), (int) (dialogHeight * 0.1));
		params.topMargin = (int) (dialogHeight * 0.85);
		params.leftMargin = (int) (dialogWidth * 0.08);
		btnSendRequests.setLayoutParams(params);
		
	}
	
	protected void hideKeyboard(View view)
	{
	    InputMethodManager in = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
	    in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	}
	
}
