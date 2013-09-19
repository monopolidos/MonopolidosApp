package epqp.monopolidos.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class VerticalScroll extends ScrollView {
	
	public VerticalScroll(Context context) {
		super(context);
        init();
	}
	
	public VerticalScroll(Context context, AttributeSet attrs) {
		super(context, attrs);
        init();
    }
	
	public VerticalScroll(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
	
	private void init(){
		this.setVerticalScrollBarEnabled(false);
	}

	public boolean onTouchEvent(MotionEvent ev) {
		return false;
	}
	
}
