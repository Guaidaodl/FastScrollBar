package io.github.guaidaodl.fastscrollbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

public class FastScrollBar extends View {

    private ListView mListView;
    private FastScrollBarAdapter mAdapter;
    private int mTextSize = 20;
    private int mTextColor = Color.rgb(0xB4, 0xB4, 0xB4);
    private Paint paint = new Paint();
    private int mLastIndex = -1;
    private int mBlankHeight = 0;
    private int mSingleHeight = 0;

    public FastScrollBar(Context context) {
        this(context, null);
    }

    public FastScrollBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FastScrollBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void calDrawArgs() {
        int height = getHeight();
        int keywordLength = mAdapter.getKeywordLength();
        mBlankHeight = (int) ((float)(height - mTextSize * keywordLength)
                / (keywordLength + 1) + 0.5f);
        mSingleHeight = (int) ((float)(height - mBlankHeight) / keywordLength+ 0.5f);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        calDrawArgs();
        paint.setColor(mTextColor);
        for (int i = 0; i < mAdapter.getKeywordLength(); i++) {
            paint.setTextSize(mTextSize);

            float xPos = getWidth() / 2 - paint.measureText(mAdapter.getKeyword(i)) / 2;
            float yPos = mSingleHeight * i + mBlankHeight + mTextSize;
            canvas.drawText(mAdapter.getKeyword(i), xPos, yPos, paint);
        }

        paint.reset();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setBackgroundColor(Color.argb(0x99, 0xE0, 0xE0, 0xE0));
                flipToItem(event.getY());
                break;
            case MotionEvent.ACTION_UP:
                setBackgroundColor(Color.argb(0, 0, 0 ,0));
                break;
            default:
                flipToItem(event.getY());
                break;
        }

        return true;
    }

    public void setTextColor(int color) {
        mTextColor = color;
    }

    public void setTextColorByResrouceId(int resourceId) {
        setTextColor(getResources().getColor(resourceId));
    }

    private void flipToItem(float y) {
        if (y < 0) {
            return;
        }
        int index = (int) ((y - mBlankHeight) / mSingleHeight);
        if (mLastIndex == index) {
            return;
        }

        mLastIndex = index;

        if (index < mAdapter.getKeywordLength()) {
            int position = mAdapter.getPosition(mAdapter.getKeyword(index));
            if (position != -1) {
                mListView.setSelectionFromTop(position, 0);
            }
        }
    }

    public void bindView(ListView listView) {
        mListView = listView;
        if (listView.getAdapter() instanceof FastScrollBarAdapter) {
            mAdapter = (FastScrollBarAdapter) listView.getAdapter();
        } else {
            throw new ClassCastException();
        }
    }

    public interface FastScrollBarAdapter {
        //get the item' index in the listview, if can not find the item, return -1
        int getPosition(String keyword);
        String getKeyword(int index);
        int getKeywordLength();
    }

}