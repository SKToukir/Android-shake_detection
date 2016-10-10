package toukir.best.vamerica.shakeapp;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.rtp.AudioStream;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tyorikan.voicerecordingvisualizer.RecordingSampler;
import com.tyorikan.voicerecordingvisualizer.VisualizerView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class VoiceRecordActivity extends AppCompatActivity {

    private MediaPlayer   mPlayer = null;
    private static final String LOG_TAG = "AudioRecordTest";
    String outputFile;
    MediaRecorder mRecorder = null;
    MediaPlayer myPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_record);

        outputFile = Environment.getExternalStorageDirectory().
                getAbsolutePath() + "/recordfile.3gpp";
    }

    public void btnStart(View view) {

        start();
    }

    public void btnStop(View view) {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;

    }

    public void btnPlay(View view) {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(outputFile);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

    }

    public void start(){

            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mRecorder.setOutputFile(outputFile);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            try {
                mRecorder.prepare();
            } catch (IOException e) {
                Log.e(LOG_TAG, "prepare() failed");
            }

            mRecorder.start();

        Toast.makeText(getApplicationContext(), "Start recording...",
                Toast.LENGTH_SHORT).show();
    }

    public void stop(View view) throws IOException{

    }

    public void btnDecode(View view) throws IOException {

        mPlayer.release();
        mPlayer = null;

        FileInputStream in=new FileInputStream(outputFile);
        byte fileContent[] = new byte[(int)outputFile.length()];

        in.read(fileContent,0,fileContent.length);

        String encoded = Base64.encodeToString(fileContent, 0);
        byte[] valueDecoded= Base64.decode(encoded,Base64.DEFAULT);
        System.out.println("Decoded value is " + new String(valueDecoded));

        //  Utilities.log("~~~~~~~~ Encoded: ", encoded);
        Toast.makeText(getApplicationContext(),String.valueOf(valueDecoded),Toast.LENGTH_LONG).show();

    }
}
