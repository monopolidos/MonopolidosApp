package epqp.monopolidos.listeners;

import epqp.monopolidos.dialogs.LoadingDialog;
import epqp.monopolidos.dialogs.NewGameDialog;
import epqp.monopolidos.dialogs.OptionsDialog;
import epqp.monopolidos.game.LoginActivity;
import epqp.monopolidos.game.PartidesActivity;
import epqp.monopolidos.game.R;
import epqp.monopolidos.objects.Sessio;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;


public class MenuClickListener implements OnCheckedChangeListener {

	private PartidesActivity context;
	
	public MenuClickListener(Context context){
		this.context = (PartidesActivity) context;
	}
	
	private void showDialogAddNewGame(){
		Log.d("MONOPOLIDOS", "showDialogAddNewGame()");
		NewGameDialog dialog = new NewGameDialog(context);
		dialog.show();
	}
	
	private void showDialogOptions(){
		Log.d("MONOPOLIDOS", "showDialogOptions()");
		OptionsDialog dialog = new OptionsDialog(context);
		dialog.show();
	}

	@Override
	public void onCheckedChanged(CompoundButton rg, boolean isChecked) {
		Log.d("MONOPOLIDOS", "onClick radio button -> " + rg.getId());
		if(isChecked){
			switch (rg.getId()) {
			case R.id.btnAdd:
				Log.d("MONOPOLIDOS", "Radio button is new game -> " + R.id.btnAdd);
				showDialogAddNewGame();
				break;
			case R.id.btnOptions:
				Log.d("MONOPOLIDOS", "Radio button is options -> " + R.id.btnOptions);
				showDialogOptions();
				break;
			case R.id.btnLogout:
				Log.d("MONOPOLIDOS", "Radio button is logout -> " + R.id.btnLogout);
				Sessio s = Sessio.getInstance();
				s.destroy();
				Intent i = new Intent(context, LoginActivity.class);
				context.startActivity(i);
				context.finish();
				break;
			}	
		}
		
	}

}
