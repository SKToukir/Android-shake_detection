package toukir.best.vamerica.shakeapp;

import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.attr.phoneNumber;

public class SpeechTotext extends AppCompatActivity {

    ContentResolver resolver;
    TextView txtText;
    protected static final int RESULT_SPEECH = 1;

    Cursor cursor;
    String contactName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_totext);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                ContentResolver cr = getContentResolver();
//                Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode("01672150360"));
//                Cursor cursor = cr.query(uri, new String[]{ContactsContract.PhoneLookup.NUMBER}, null, null, null);
//                if (cursor == null) {
//                    return;
//                }
//                String contactName = null;
//                if(cursor.moveToFirst()) {
//                    contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
//                }
//
//                if(cursor != null && !cursor.isClosed()) {
//                    cursor.close();
//                }
//
//                txtText.setText(contactName);
//            }
//        }).start();


        txtText = (TextView) findViewById(R.id.textView);

        txtText.setText(getContactByName("tonmoy vaia"));
    }

    public void btnSpeak(View view) {
        Intent intent = new Intent(
                RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");

        try {
            startActivityForResult(intent, RESULT_SPEECH);
            txtText.setText("");
        } catch (ActivityNotFoundException a) {
            Toast t = Toast.makeText(getApplicationContext(),
                    "Ops! Your device doesn't support Speech to Text",
                    Toast.LENGTH_SHORT);
            t.show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RESULT_SPEECH: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> text = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    txtText.setText(text.get(0));
                }
                break;
            }

        }
    }

    public String getContactByName(String contactNamea){
        String ret = null;
        String selection = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" like'%" + contactNamea +"%'";
        String[] projection = new String[] { ContactsContract.CommonDataKinds.Phone.NUMBER};
        Cursor c = this.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                projection, selection, null, null);
        if (c.moveToFirst()) {
            ret = c.getString(0);
        }
        c.close();
        if(ret==null)
            ret = "Unsaved";
        return ret;
    }
}
