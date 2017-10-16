package com.fengan.dynamicrectangledemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fengan.dynamicrectanglelibrary.DynamicRectangleView;

public class ScrollViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);
        ObservableScrollView scrollView = (ObservableScrollView) findViewById(R.id.sv);
        final DynamicRectangleView dynamicRectangleView = (DynamicRectangleView) findViewById(R.id.drv);
        scrollView.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                dynamicRectangleView.setPercent((float) y/dynamicRectangleView.getMeasuredHeight());
//                if (y>oldy) {
//                    Log.e("fengan","上上");
//                }else if(y<oldy){
//                    Log.e("fengan","下下");
//                }
            }
        });
    }
}
