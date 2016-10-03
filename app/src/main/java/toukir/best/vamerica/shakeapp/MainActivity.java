package toukir.best.vamerica.shakeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voice_recog);

    }
//        ShakeDetector.create(this, new ShakeDetector.OnShakeListener() {
//            @Override
//            public void OnShake() {
//                Toast.makeText(getApplicationContext(), "Device shaken!", Toast.LENGTH_SHORT).show();
//            }
//        });


//    @Override
//    protected void onResume() {
//        super.onResume();
//        ShakeDetector.start();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        ShakeDetector.stop();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        ShakeDetector.destroy();
//    }

    public void btnSet(View view) {
        Intent intent = new Intent();
        intent.setAction("toukir.best.vamerica.shakeapp.android.action.broadcast");
        sendBroadcast(intent);
    }
}
