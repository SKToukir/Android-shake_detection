package toukir.best.vamerica.shakeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MultiTouchEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_multi_touch_event);
        setContentView(new SingleTouchEventView(this, null));
    }
}
