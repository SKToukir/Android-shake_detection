package toukir.best.vamerica.shakeapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.widget.Toast;

import com.github.tbouron.shakedetector.library.ShakeDetector;

import static android.R.attr.start;

/**
 * Created by toukir on 9/23/16.
 */

public class OnBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {

        ShakeDetector.create(context, new ShakeDetector.OnShakeListener() {
            @Override
            public void OnShake() {
                Toast.makeText(context,"shake",Toast.LENGTH_LONG).show();
                Intent i = new Intent(context,MainActivity.class);
//                i.setClassName(context, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
                //ShakeDetector.start();
            }
        });


    }
}
