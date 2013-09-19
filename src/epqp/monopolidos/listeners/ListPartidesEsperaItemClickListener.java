package epqp.monopolidos.listeners;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.AdapterView.OnItemClickListener;
import epqp.monopolidos.dialogs.PartidaEsperaDialog;
import epqp.monopolidos.game.PartidesActivity;
import epqp.monopolidos.objects.Partida;

public class ListPartidesEsperaItemClickListener implements OnItemClickListener {

	private PartidesActivity context;
	private ListAdapter adapter;
	
	public ListPartidesEsperaItemClickListener(PartidesActivity cont, ListAdapter adapter){
		context = cont;
		this.adapter =adapter;
	}
	
	@Override
	public void onItemClick(AdapterView<?> adap, View view, int pos, long partidaPK) {
		
		Partida p = (Partida) adapter.getItem(pos);
		Log.d("MONOPOLIDOS", "Partida espera -> " + p.getPartidaPK());
		
		PartidaEsperaDialog dialog = new PartidaEsperaDialog(context, p);
		dialog.show();
		
	}	
	
}
