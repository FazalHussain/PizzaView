package com.library.pizza;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Pizza View is a view which is used to create Pizza like view.
 *
 * @author Fazal Hussain
 */
public class PizzaView extends View {

    private Paint paint;

    public PizzaView(Context context) {
        super(context);
        init();
    }

    public PizzaView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * Initialize paint
     */
    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //onDraw call multiple times per second do not create an object inside onDraw
        final int width = getWidth() - getPaddingLeft() - getPaddingTop();
        final int height = getHeight() - getPaddingRight() - getPaddingBottom();
        final int cx = width / 2 + getPaddingLeft();
        final int cy = height / 2 + getPaddingTop();
        final float diameter = Math.min(width, height) - paint.getStrokeWidth();
        final float radius = diameter / 2;
        canvas.drawCircle(cx, cy, radius, paint);
        drawPizzaCuts(canvas, cx, cy, radius);
    }

    /**
     * <p>Draw the line using {@link Canvas} calculate the degree by dividing with number of wedges
     * than invoked the {@link Canvas#save()} to perserve the canvas condition. Rotate the
     * canvas with the specific degree with center coordinate
     * {@link Canvas#rotate(float, float, float)} and finally draw the line from
     * center point to center top</p>
     *
     * @see Canvas#drawLine(float, float, float, float, Paint)
     *
     * @param canvas The where you want to draw line.
     * @param cx     The center x coordinate point.
     * @param cy     The center y coordinate point.
     * @param radius The radius of circle.
     */
    private void drawPizzaCuts(Canvas canvas, float cx, float cy, float radius) {
        final int numWedges = 10;
        final float degrees = 360f / numWedges;
        //preserve the canvas condition
        canvas.save();
        for (int i = 0; i < numWedges; i++) {
            canvas.rotate(degrees, cx, cy);
            canvas.drawLine(cx, cy, cx, cy - radius, paint);
        }
        canvas.restore();

    }
}
