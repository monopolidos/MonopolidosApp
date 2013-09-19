package epqp.monopolidos.dialogs;

import java.io.IOException;
import java.io.InputStream;

import epqp.monopolidos.game.GameActivity;
import epqp.monopolidos.game.R;
import epqp.monopolidos.objects.Targeta;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class TargetaDialog extends Dialog {

	private int screenHeight;
	private int screenWidth;
	
	private int dialogWidth;
	private int dialogHeight;
	
	private RelativeLayout dialogLayout = null;
	private ImageView targetaImageView = null;
	private Button buyButton = null;
	
	private Targeta targeta = null;
	
	int imageHeigt;
	int imageWidth;
	
	private GameActivity context;
	
	public TargetaDialog(GameActivity c, Targeta targeta){
		super(c); 
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.dialog_view_targeta);
		this.context = c;
		this.targeta = targeta;
		
		init(c);
		
		dialogLayout = (RelativeLayout)findViewById(R.id.dialogLayout);
		targetaImageView = (ImageView)findViewById(R.id.TargetaImageView);
		buyButton = (Button)findViewById(R.id.BuyTargetaButton);
		
		designWindow();
	}
	
	private void LoadImageTargeta(){
		InputStream ims = null;
		float relation = 1.6f;
		
		imageHeigt = (int) (dialogHeight * 0.65);
		imageWidth = (int) (imageHeigt / relation);
		try {
			if(targeta == null){
				Log.d("MONOPOLIDOS", "targeta is null");
			}
			ims = context.getAssets().open(targeta.gettImatge());
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(imageWidth, imageHeigt);
			params.topMargin = (int) (dialogHeight * 0.05);
			params.leftMargin = (dialogWidth - imageWidth)/2;
			targetaImageView.setLayoutParams(params);
			Bitmap bmp = BitmapFactory.decodeStream(ims);
			
			Matrix mat = new Matrix();
			mat.postRotate(180);//===>angle to be rotated
			Bitmap bMapRotate = Bitmap.createBitmap(bmp, 0, 0,bmp.getWidth(),bmp.getHeight(), mat, true);
			targetaImageView.setImageBitmap(bMapRotate);
			
			Bitmap resizedBitmap = Bitmap.createScaledBitmap(bMapRotate, imageWidth, imageHeigt, true);
			
			targetaImageView.setImageBitmap(resizedBitmap); 
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		
		LoadImageTargeta();
		
		int buttonHeight = (int) (dialogHeight * 0.2);
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int) (dialogWidth * 0.8), buttonHeight);
		params.leftMargin = (int) (dialogWidth * 0.1);
		params.topMargin = (int) (dialogHeight * 0.75);
		buyButton.setLayoutParams(params);
	}
}
