package com.minephone.png2gifview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final Png2GifView view1 = (Png2GifView) findViewById(R.id.img1);
        final Png2GifView view2 = (Png2GifView) findViewById(R.id.img2);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view1.setRun(!view1.isRunning());
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view2.setRun(!view2.isRunning());
            }
        });
    }
}
