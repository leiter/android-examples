package com.android.marco.testtesting;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by gen on 21.05.15.
 */
public class ProgressCycle extends View {

    private Rect container;
    private RectF progressIndicatorFrame;
    private Paint paint;

    public ProgressCycle(Context context,Rect r) {
        super(context);
        container=r;
        paint = new Paint();
        progressIndicatorFrame = new RectF(container.left +40,container.top+40,
                                           container.right-40,container.top+container.width()-40);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#005C5C"));
        canvas.drawCircle(container.centerX(), container.top + container.width() / 2,
                container.width() / 2 - 10, paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(40);
        paint.setShadowLayer(12,0,0,Color.YELLOW);
        paint.setColor(Color.RED);
        canvas.drawArc(progressIndicatorFrame, -75, 245, false, paint);



    }

}
