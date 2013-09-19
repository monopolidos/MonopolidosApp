package epqp.monopolidos.listeners;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

public class TableroTouchListener implements OnTouchListener {
	
	private float startX;
	private float startY;
	
	private HorizontalScrollView hScroll;
	private ScrollView vScroll;
	
	public TableroTouchListener(HorizontalScrollView hScroll2, ScrollView vScroll2){
		this.hScroll = hScroll2;
		this.vScroll = vScroll2;
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent event) {
		float touchX, touchY;
    	
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                touchX = event.getX();
                touchY = event.getY();
                vScroll.scrollBy((int) (startX - touchX), (int) (startY - touchY));
                hScroll.scrollBy((int) (startX - touchX), (int) (startY - touchY));
                startX = touchX;
                startY = touchY;
                break;
        }

        return true;
	}

}
