package epqp.monopolidos.listeners;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.AdapterView.OnItemClickListener;
import epqp.monopolidos.game.GameActivity;
import epqp.monopolidos.game.PartidesActivity;
import epqp.monopolidos.objects.Partida;

public class ListPartidesNoTornItemClickListener implements OnItemClickListener {

	private PartidesActivity context;
	private ListAdapter adapter;
	
	public ListPartidesNoTornItemClickListener(PartidesActivity cont, ListAdapter adapter){
		context = cont;
		this.adapter =adapter;
	}
	
	@Override
	public void onItemClick(AdapterView<?> adap, View view, int pos, long partidaPK) {
		
		Partida p = (Partida) adapter.getItem(pos);
		Log.d("MONOPOLIDOS", "Partida no torn -> " + p.getPartidaPK());
		Intent intent = new Intent(context, GameActivity.class);
		Bundle b = new Bundle();
		b.putSerializable("partida", p);
		intent.putExtras(b);
		context.startActivity(intent);
		
	}	
	
}