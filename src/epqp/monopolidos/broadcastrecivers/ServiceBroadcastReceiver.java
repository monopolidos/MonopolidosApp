package epqp.monopolidos.broadcastrecivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ServiceBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
		if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction()))
		{
			//Intent i = new Intent(context, HelloService.class);
			//context.startService(i);
		}
		else if(Intent.ACTION_EXTERNAL_APPLICATIONS_AVAILABLE.equals(intent.getAction())){
			//Intent i = new Intent(context, HelloService.class);
			//context.startService(i);
		}
		
	}

}
