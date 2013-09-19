package epqp.monopolidos.game;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import epqp.monopolidos.asynctasks.RegisterTask;
import epqp.monopolidos.objects.Sessio;

public class RegisterActivity extends Activity {

	private RelativeLayout registerLayout = null;
	private EditText txtUsername;
	private EditText txtPassword;
	private EditText txtPasswordRep;
	private TextView labelUsername;
	private TextView labelPassword;
	private TextView labelPasswordRep;
	private Button btnRegistrar;
	private ImageView icon;
	
	private int screenHeight;
	private int screenWidth;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		if(Sessio.getInstance().isLogged()){
			Intent i = new Intent(RegisterActivity.this, GameActivity.class);
			startActivity(i);
			finish();
        }
		
		Display display = getWindowManager().getDefaultDisplay(); 
		screenWidth = display.getWidth();  
		screenHeight = display.getHeight() - getStatusBarHeight(); 
		
		registerLayout = (RelativeLayout)findViewById(R.id.registerLayout);
		txtUsername = (EditText)findViewById(R.id.txtUsernameRegister);
		txtPassword = (EditText)findViewById(R.id.txtPasswordRegister);
		txtPasswordRep = (EditText)findViewById(R.id.passwordRep);
		labelUsername = (TextView)findViewById(R.id.labelUsername);
		labelPassword = (TextView)findViewById(R.id.labelPassword);
		labelPasswordRep = (TextView)findViewById(R.id.labelPasswordRepeat);
		btnRegistrar = (Button)findViewById(R.id.btnRegistrar);
		icon = (ImageView)findViewById(R.id.iconm);
		
		designWindow();
		
		btnRegistrar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String user = txtUsername.getText().toString().trim();
				String pass = txtPassword.getText().toString().trim();
				String passRep = txtPasswordRep.getText().toString().trim();
				
				if(user.length()> 5){
					txtUsername.setHint("");
					if(pass.equals(passRep)){
						if(pass.length()> 5){
							txtPassword.setHint("");
							new RegisterTask(RegisterActivity.this).execute(user, pass);
						}
						else {
							txtPassword.setText("");
							txtPasswordRep.setText("");
							txtPassword.setHint("Min length is 5");
							txtPassword.setHintTextColor(Color.RED);
						}
					}
					else {
						txtPassword.setText("");
						txtPasswordRep.setText("");
						txtPassword.setHint("Passwords not equals");
						txtPassword.setHintTextColor(Color.RED);
					}
				}
				else {
					txtUsername.setText("");
					txtUsername.setHint("Min length is 5");
					txtUsername.setHintTextColor(Color.RED);
				}
				
			}
		});
		
		registerLayout.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				hideKeyboard(v);
				return false;
			}
		});
	}
	
	private void designWindow(){
		//POSITION OF THE ICON IMAGE
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int)(screenWidth*0.8), (int) (screenHeight * 0.15));
		params.leftMargin = (int)(screenWidth*0.1);
		params.topMargin =(int)(screenHeight*0.02);
		icon.setLayoutParams(params);
		
		//POSITION OF TEXTFIELD USERNAME
		params = new RelativeLayout.LayoutParams((int)(screenWidth*0.8), (int) (screenHeight * 0.05));
		params.leftMargin = (int)(screenWidth*0.1);
		params.topMargin =(int)(screenHeight*0.2);
		labelUsername.setLayoutParams(params);
		labelUsername.setIncludeFontPadding(false);
		
		params = new RelativeLayout.LayoutParams((int) (screenWidth * 0.8), (int) (screenHeight * 0.1));
		params.leftMargin = (int) (screenWidth*0.1);
		params.topMargin = (int)(screenHeight*0.25);
		txtUsername.setLayoutParams(params);
		
		//POSITION OF TEXTFIELD PASSWORD
		params = new RelativeLayout.LayoutParams((int)(screenWidth*0.8), (int) (screenHeight * 0.05));
		params.leftMargin = (int)(screenWidth*0.1);
		params.topMargin =(int)(screenHeight*0.4);
		labelPassword.setLayoutParams(params);
		labelPassword.setIncludeFontPadding(false);
		
		params = new RelativeLayout.LayoutParams((int) (screenWidth * 0.8), (int) (screenHeight * 0.1));
		params.leftMargin = (int) (screenWidth*0.1);
		params.topMargin = (int)(screenHeight*0.45);
		txtPassword.setLayoutParams(params);
		
		//POSITION OF TEXTFIELD PASSWORD REPEAT
		params = new RelativeLayout.LayoutParams((int)(screenWidth*0.8), (int) (screenHeight * 0.05));
		params.leftMargin = (int)(screenWidth*0.1);
		params.topMargin =(int)(screenHeight*0.6);
		labelPasswordRep.setLayoutParams(params);
		labelPasswordRep.setIncludeFontPadding(false);
		labelPasswordRep.setText("Repeat Password:");
		
		params = new RelativeLayout.LayoutParams((int) (screenWidth * 0.8), (int) (screenHeight * 0.1));
		params.leftMargin = (int) (screenWidth*0.1);
		params.topMargin = (int)(screenHeight*0.65);
		txtPasswordRep.setLayoutParams(params);
		
		//POSITION OF BUTTON REGISTER
		params = new RelativeLayout.LayoutParams((int)(screenWidth*0.84), (int)(screenHeight*0.1));
		params.leftMargin = (int) (screenWidth*0.08);
		params.topMargin = (int) (screenHeight * 0.8);
		btnRegistrar.setLayoutParams(params);
		
		txtUsername.requestFocus();

		this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	}
	
	protected void hideKeyboard(View view)
	{
	    InputMethodManager in = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
	    in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
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
