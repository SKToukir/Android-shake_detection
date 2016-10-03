package toukir.best.vamerica.shakeapp;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

/**
 * Created by toukir on 9/23/16.
 */

public class ShakeEventListener implements SensorEventListener {

    private final Context context;

    public ShakeEventListener(Context mContext) {
        context = mContext;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        handleShake(sensorEvent);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    void handleShake(SensorEvent event) {
                 // start main activity
            Intent intent = new Intent(context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

    }
}
