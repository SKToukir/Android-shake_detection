package toukir.best.vamerica.shakeapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.tbouron.shakedetector.library.ShakeDetector;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView textSpeachInput;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voice_recog);

        textSpeachInput = (TextView) findViewById(R.id.textInput);

        ShakeDetector.create(this, new ShakeDetector.OnShakeListener() {
            @Override
            public void OnShake() {
                finish();
                Toast.makeText(getApplicationContext(), "Device shaken!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();

        switch (keyCode){
            case KeyEvent.KEYCODE_VOLUME_UP:
                if (action == KeyEvent.ACTION_DOWN){
                    Toast.makeText(getApplicationContext(),"V UP",Toast.LENGTH_LONG).show();
                    dialPhoneNumber("01672150360");
                }
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (action == KeyEvent.ACTION_DOWN){
                    Toast.makeText(getApplicationContext(),"V DOWN",Toast.LENGTH_LONG).show();
                }
                return true;
            default:
                return super.dispatchKeyEvent(event);
        }


    }

    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void multiTouchActivity(View view) {
//        promptSpeechInput();

        Intent intent =new Intent(MainActivity.this,SpeechTotext.class);
        startActivity(intent);
    }

    /**
     * Showing google speech input dialog
     * */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Say something");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "Not supported",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    textSpeachInput.setText(result.get(0));
                }
                break;
            }

        }

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

//    public void btnSet(View view) {
//        Intent intent = new Intent();
//        intent.setAction("toukir.best.vamerica.shakeapp.android.action.broadcast");
//        sendBroadcast(intent);
//    }
    }
}
