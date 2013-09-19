package epqp.monopolidos.game;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
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
import epqp.monopolidos.asynctasks.LoginTask;
import epqp.monopolidos.bd.MonopolidosSQLiteHelper;
import epqp.monopolidos.objects.Sessio;

public class LoginActivity extends Activity {
	
	private int screenHeight = -1;
	private int screenWidth = -1;
	
	private RelativeLayout loginLayout = null;
	private EditText txtUser = null;
	private EditText txtPassword = null;
	private Button btnLogin = null;
	private Button btnRegister = null;
	private TextView labelUsername = null;
	private TextView labelPassword = null;
	private ImageView icon = null;
	
	private MonopolidosSQLiteHelper mSQLiteHelper;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		mSQLiteHelper = new  MonopolidosSQLiteHelper(this, "Monopolidos.db", null, 2);
        SQLiteDatabase db = mSQLiteHelper.getWritableDatabase();
        
        if(Sessio.getInstance() == null)
        {
        	Log.d("MONOPOLIDOS", "Create new instance of Session");
        	new Sessio(mSQLiteHelper);
        }
        
        if(db != null){
        	db.close();
        }
        
        if(Sessio.getInstance().isLogged()){
        	Log.d("MONOPOLIDOS", "isLogged() -> open PartidesActivity");
			Intent i = new Intent(LoginActivity.this, PartidesActivity.class);
			startActivity(i);
			finish();
        }
		
		Display display = getWindowManager().getDefaultDisplay(); 
		screenWidth = display.getWidth();  
		screenHeight = display.getHeight() - getStatusBarHeight(); 
		
		loginLayout = (RelativeLayout)findViewById(R.id.loginLayout);
		txtUser = (EditText)findViewById(R.id.txtUser);
		txtPassword = (EditText)findViewById(R.id.txtPassword);
		btnLogin = (Button)findViewById(R.id.btnLogin);
		btnRegister = (Button)findViewById(R.id.buttonRegi);
		labelPassword = (TextView)findViewById(R.id.txtviewPassword);
		labelUsername = (TextView)findViewById(R.id.labelUsername);
		icon = (ImageView)findViewById(R.id.iconm);
		
		loginLayout.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				hideKeyboard(v);
				return false;
			}
		});
		
		designWindow();
		
		btnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Log.d("MONOPOLIDOS", "onClick -> Login");
				String username = txtUser.getText().toString();
				String password = txtPassword.getText().toString();

				new LoginTask(LoginActivity.this).execute(username.trim(), password.trim());
			}
		});
		
		btnRegister.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
				startActivityForResult(i, 1);
			}
		});	
	}
	
	private void designWindow(){
		//POSITION THE ICON IMAGE
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int)(screenWidth*0.8), (int) (screenHeight * 0.2));
		params.leftMargin = (int)(screenWidth*0.1);
		params.topMargin =(int)(screenHeight*0.05);
		icon.setLayoutParams(params);
		
		//POSITION THE LABEL OF USER
		params = new RelativeLayout.LayoutParams((int)(screenWidth*0.8), (int) (screenHeight * 0.05));
		params.leftMargin = (int)(screenWidth*0.1);
		params.topMargin =(int)(screenHeight*0.35);
		labelUsername.setLayoutParams(params);
		labelUsername.setIncludeFontPadding(false);
		
		params = new RelativeLayout.LayoutParams((int) (screenWidth * 0.8), (int) (screenHeight * 0.1));
		params.leftMargin = (int) (screenWidth*0.1);
		params.topMargin = (int)(screenHeight*0.4);
		txtUser.setLayoutParams(params);
			
		//POSITION THE LABEL OF PASSWORD
		params = new RelativeLayout.LayoutParams((int)(screenWidth*0.8), (int) (screenHeight * 0.05));
		params.leftMargin = (int)(screenWidth*0.1);
		params.topMargin =(int)(screenHeight*0.55);
		labelPassword.setLayoutParams(params);
		labelPassword.setIncludeFontPadding(false);
		
		params = new RelativeLayout.LayoutParams((int) (screenWidth * 0.8), (int) (screenHeight * 0.1));
		params.leftMargin = (int) (screenWidth*0.1);
		params.topMargin = (int)(screenHeight*0.6);
		txtPassword.setLayoutParams(params);
		
		//POSITION THE BUTTONS
		params = new RelativeLayout.LayoutParams((int)(screenWidth*0.4), (int)(screenHeight*0.1));
		params.leftMargin = (int) (screenWidth*0.08);
		params.topMargin = (int) (screenHeight * 0.8);
		btnLogin.setLayoutParams(params);
		
		params = new RelativeLayout.LayoutParams((int)(screenWidth*0.4), (int)(screenHeight*0.1));
		params.leftMargin = (int) (screenWidth * 0.52);
		params.topMargin = (int) (screenHeight * 0.8);
		btnRegister.setLayoutParams(params);
		
		txtUser.requestFocus();

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
