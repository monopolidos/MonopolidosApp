package epqp.monopolidos.listeners;

import epqp.monopolidos.dialogs.DaiceDialog;
import epqp.monopolidos.game.GameActivity;
import epqp.monopolidos.game.LoginActivity;
import epqp.monopolidos.game.R;
import epqp.monopolidos.objects.Sessio;
import epqp.monopolidos.views.Taulell;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;

public class GameMenuClickListener implements OnCheckedChangeListener {

	private GameActivity context;
	
	public GameMenuClickListener(GameActivity cont){
		this.context = cont;
	}
	
	@Override
	public void onCheckedChanged(CompoundButton rg, boolean isChecked) {
		if(isChecked){
			switch (rg.getId()) {
			case R.id.rbExit:
				context.GetTaulell().ClearTaulell();
				context.finish();
				break;
			case R.id.rbRollTheDice:
				DaiceDialog dialog = new DaiceDialog(context, context.getPartida());
				dialog.show();
				break;
			}
		}		
	}

}
