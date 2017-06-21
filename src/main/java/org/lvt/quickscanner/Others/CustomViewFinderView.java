package org.lvt.quickscanner.Others;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;

import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.ViewfinderView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ylt1hc on 6/14/2017.
 */
public class CustomViewFinderView extends ViewfinderView {
    public CustomViewFinderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public void onDraw(Canvas canvas) {
        try {

            Rect frame        = framingRect;
            Rect previewFrame = previewFramingRect;
            int  width        = canvas.getWidth();
            int  height       = canvas.getHeight();

            refreshSizes();
            if (framingRect == null || previewFramingRect == null) {
                return;
            }
//            paint.setColor(resultBitmap != null ? resultColor : maskColor);
            paint.setColor(resultBitmap != null ? resultColor : maskColor);
            canvas.drawRect(0, 0, width, frame.top, paint);
            canvas.drawRect(0, frame.top, frame.left, frame.bottom + 1, paint);
            canvas.drawRect(frame.right + 1, frame.top, width, frame.bottom + 1, paint);
            canvas.drawRect(0, frame.bottom + 1, width, height, paint);

            if (resultBitmap != null) {
                paint.setAlpha(CURRENT_POINT_OPACITY);
                canvas.drawBitmap(resultBitmap, null, frame, paint);
            } else {
                paint.setColor(laserColor);
                paint.setAlpha(SCANNER_ALPHA[scannerAlpha]);
                scannerAlpha = (scannerAlpha + 1) % SCANNER_ALPHA.length;
                int middle = frame.height() / 2 + frame.top;
                canvas.drawRect(frame.left + 2, middle - 2, frame.right - 1, middle + 2, paint);

                float scaleX = frame.width() / (float) previewFrame.width();
                float scaleY = frame.height() / (float) previewFrame.height();

                List<ResultPoint> currentPossible = possibleResultPoints;
                List<ResultPoint> currentLast     = lastPossibleResultPoints;
                int               frameLeft       = frame.left;
                int               frameTop        = frame.top;
                if (currentPossible.isEmpty()) {
                    lastPossibleResultPoints = null;
                } else {
                    possibleResultPoints = new ArrayList<>(5);
                    lastPossibleResultPoints = currentPossible;
                    paint.setAlpha(CURRENT_POINT_OPACITY);
                    paint.setColor(resultPointColor);
                    for (ResultPoint point : currentPossible) {
                        canvas.drawCircle(frameLeft + (int) (point.getX() * scaleX),
                                frameTop + (int) (point.getY() * scaleY),
                                POINT_SIZE, paint);
                    }
                }
                if (currentLast != null) {
                    paint.setAlpha(CURRENT_POINT_OPACITY / 2);
                    paint.setColor(resultPointColor);
                    float radius = POINT_SIZE / 2.0f;
                    for (ResultPoint point : currentLast) {
                        canvas.drawCircle(frameLeft + (int) (point.getX() * scaleX),
                                frameTop + (int) (point.getY() * scaleY),
                                radius, paint);
                    }
                }

                postInvalidateDelayed(ANIMATION_DELAY,
                        frame.left - POINT_SIZE,
                        frame.top - POINT_SIZE,
                        frame.right + POINT_SIZE,
                        frame.bottom + POINT_SIZE);
            }

            paint.setColor(Color.RED);

            int edgesLength = (frame.bottom - frame.top) / 6;
            int edgeWidth = 4;

            canvas.drawRect(frame.left, frame.top, frame.left + edgeWidth, frame.top + edgesLength, paint);
            canvas.drawRect(frame.left, frame.top, frame.left + edgesLength, frame.top + edgeWidth, paint);

            canvas.drawRect(frame.left, frame.bottom - edgesLength, frame.left + edgeWidth, frame.bottom, paint);
            canvas.drawRect(frame.left, frame.bottom - edgeWidth, frame.left + edgesLength, frame.bottom, paint);

            canvas.drawRect(frame.right - edgeWidth, frame.top, frame.right, frame.top + edgesLength, paint);
            canvas.drawRect(frame.right - edgesLength, frame.top, frame.right, frame.top + edgeWidth, paint);

            canvas.drawRect(frame.right - edgeWidth, frame.bottom - edgesLength, frame.right, frame.bottom, paint);
            canvas.drawRect(frame.right - edgesLength, frame.bottom - edgeWidth, frame.right, frame.bottom, paint);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
