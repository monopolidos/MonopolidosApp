package epqp.monopolidos.adapter;

import java.util.ArrayList;

import epqp.monopolidos.game.R;
import epqp.monopolidos.objects.Partida;

import android.app.Activity;
import android.graphics.Color;
import android.text.TextUtils.TruncateAt;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ListPartTornAdapter extends BaseAdapter {

	private ArrayList<Partida> partides;
	private Activity context;
	private int width;
	private int height;
	
	public ListPartTornAdapter(Activity context, ArrayList<Partida> p, int width, int height){
		partides = p;
		this.context = context;
		this.width = width;
		this.height = (int) (height*0.3);
	}
	
	@Override
	public int getCount() {
		return partides.size();
	}

	@Override
	public Partida getItem(int pos) {
		return partides.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return partides.get(pos).getPartidaPK();
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup viewGroup) {
		View element = convertView;
		
		Partida p = getItem(pos);
		
		if(element == null){
			LayoutInflater inflater = context.getLayoutInflater();
			element = inflater.inflate(R.layout.list_partides_espera, null);
		}

		float densityMultiplier = context.getResources().getDisplayMetrics().density;
		float scaledPx;
		
		RelativeLayout parent = (RelativeLayout) element.findViewById(R.id.itemPartidaEspera);
		TextView name = (TextView) element.findViewById(R.id.partEsperaName);
		TextView players =(TextView) element.findViewById(R.id.partEsperaPlayers);
		TextView noConfirm = (TextView) element.findViewById(R.id.partEsperaPlayersNotconfirm);
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
		parent.setLayoutParams(params);
		
		params = new RelativeLayout.LayoutParams((int) (width * 0.95), (int) (height*0.5));
		params.topMargin = 0;
		params.leftMargin = (int) (width * 0.05);
		name.setLayoutParams(params);
		scaledPx = (float) (height * 0.5 * 0.3  / densityMultiplier);
		name.setTextSize(scaledPx);
		name.setText(p.getpName());
		name.setTextColor(Color.BLACK);
		
		params = new RelativeLayout.LayoutParams((int) (width * 0.9), (int) (height*0.25));
		params.topMargin = (int) (height * 0.5);
		params.leftMargin = (int) (width * 0.1);
		players.setLayoutParams(params);
		scaledPx = (float) (height * 0.25 * 0.5  / densityMultiplier);
		players.setTextSize(scaledPx);
		players.setText("Players: " + p.getpPlayersToString());
		players.setTextColor(Color.GRAY);
		players.setSingleLine(true);
		players.setEllipsize(TruncateAt.END);
		
		noConfirm.setVisibility(View.INVISIBLE);
		
		return element;
	}

}
