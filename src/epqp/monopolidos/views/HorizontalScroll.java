package epqp.monopolidos.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

public class HorizontalScroll extends HorizontalScrollView {
	
	public HorizontalScroll(Context context) {
		super(context);
        init();
	}
	
	public HorizontalScroll(Context context, AttributeSet attrs) {
		super(context, attrs);
        init();
    }
	
	public HorizontalScroll(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
	
	private void init(){
		this.setHorizontalScrollBarEnabled(false);
	}

	public boolean onTouchEvent(MotionEvent ev) {
		return false;
	}
}
