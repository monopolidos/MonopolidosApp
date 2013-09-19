package epqp.monopolidos.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import epqp.monopolidos.asynctasks.ChangeEstatPartidaTask;
import epqp.monopolidos.game.PartidesActivity;
import epqp.monopolidos.game.R;
import epqp.monopolidos.objects.Partida;
import epqp.monopolidos.objects.Sessio;

public class PartidaEsperaDialog extends Dialog {
	
	private PartidesActivity context;
	
	private int screenWidth;
	private int screenHeight;
	
	private int dialogWidth;
	private int dialogHeight;
	
	private RelativeLayout dialogLayout;
	private TextView tvConfirm;
	private Button btnConfirm;
	private Button btnRefuse;
	
	private Partida partida;
	
	public PartidaEsperaDialog(PartidesActivity context, Partida p){
		super(context);
		this.context = context;
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.dialog_partida_espera);
		
		partida = p;
		
		init(context);
		
		dialogLayout = (RelativeLayout)findViewById(R.id.dialogLayout);
		tvConfirm = (TextView)findViewById(R.id.textviewConfirm);
		btnConfirm = (Button) findViewById(R.id.buttonConfirm);
		btnRefuse = (Button) findViewById(R.id.buttonRefuse);
		
		if(partida.userIsConfirmed(Sessio.getInstance().getUser().getUsuariPK())){
			designWindowConfirmed();
		}
		else {
			designWindowNoConfirmed();
			btnConfirm.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					PartidaEsperaDialog.this.hide();
					new ChangeEstatPartidaTask(PartidaEsperaDialog.this.context).execute(Sessio.getInstance().getUser().getUsuariPK()+"", partida.getPartidaPK()+"", 1 +"");
				}
			});
		}
		
		btnRefuse.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				PartidaEsperaDialog.this.hide();
				new ChangeEstatPartidaTask(PartidaEsperaDialog.this.context).execute(Sessio.getInstance().getUser().getUsuariPK()+"", partida.getPartidaPK()+"", 2 +"");
			}
		});
	}
	
	private void designWindowConfirmed(){
		FrameLayout.LayoutParams paramsf = new android.widget.FrameLayout.LayoutParams((int) (dialogWidth), (int) (dialogHeight));
		dialogLayout.setLayoutParams(paramsf);
		
		float densityMultiplier = getContext().getResources().getDisplayMetrics().density;
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int) (dialogWidth*0.9), (int) (dialogHeight*0.45));
		params.topMargin = 0;
		params.leftMargin = (int) (dialogWidth * 0.05);
		tvConfirm.setLayoutParams(params);
		tvConfirm.setText("You have already confirmed this game.");
		tvConfirm.setTextSize((float) (dialogHeight * 0.45*0.3/densityMultiplier));
		tvConfirm.setGravity(Gravity.CENTER_VERTICAL);
		
		btnConfirm.setVisibility(View.INVISIBLE);
		
		params = new RelativeLayout.LayoutParams((int) (dialogWidth * 0.9),(int) (dialogHeight*0.45));
		params.topMargin = (int) (dialogHeight*0.5);
		params.leftMargin = (int) (dialogWidth * 0.05);
		btnRefuse.setText("REFUSE GAME");
		btnRefuse.setLayoutParams(params);
		btnRefuse.setTextSize((float) (dialogHeight * 0.4*0.3/densityMultiplier));
	}
	
	private void designWindowNoConfirmed(){
		FrameLayout.LayoutParams paramsf = new android.widget.FrameLayout.LayoutParams((int) (dialogWidth), (int) (dialogHeight));
		dialogLayout.setLayoutParams(paramsf);
		
		float densityMultiplier = getContext().getResources().getDisplayMetrics().density;
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int) (dialogWidth*0.9), (int) (dialogHeight*0.45));
		params.topMargin = 0;
		params.leftMargin = (int) (dialogWidth * 0.05);
		tvConfirm.setLayoutParams(params);
		tvConfirm.setText("Would you confirm this game?");
		tvConfirm.setTextSize((float) (dialogHeight * 0.45*0.3/densityMultiplier));
		tvConfirm.setGravity(Gravity.CENTER_VERTICAL);
		
		params = new RelativeLayout.LayoutParams((int) (dialogWidth * 0.45),(int) (dialogHeight*0.45));
		params.topMargin = (int) (dialogHeight*0.5);
		params.leftMargin = (int) (dialogWidth * 0.033);
		btnConfirm.setText("CONFIRM");
		btnConfirm.setLayoutParams(params);
		btnConfirm.setTextSize((float) (dialogHeight * 0.4*0.3/densityMultiplier));
		
		params = new RelativeLayout.LayoutParams((int) (dialogWidth * 0.45),(int) (dialogHeight*0.45));
		params.topMargin = (int) (dialogHeight*0.5);
		params.leftMargin = (int) (dialogWidth * 0.517);
		btnRefuse.setText("REFUSE");
		btnRefuse.setLayoutParams(params);
		btnRefuse.setTextSize((float) (dialogHeight * 0.4*0.3/densityMultiplier));
		
	}
	
	@SuppressWarnings("deprecation")
	private void init(Context context){
		Display display = ((Activity)context).getWindowManager().getDefaultDisplay(); 
		screenWidth = display.getWidth();  
		screenHeight = display.getHeight() - 80; 

		LayoutParams params = getWindow().getAttributes();
		params.y = -40;
		getWindow().setAttributes(params);
		
		dialogWidth = (int) (screenWidth * 0.9 );
		dialogHeight = (int) (screenHeight * 0.33 );
	}
}
