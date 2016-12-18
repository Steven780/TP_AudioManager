package com.example.android.tp_audiomanager;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
    Button toggle;
    TextView title;
    ImageView image;
    Drawable newPhoneImage;
    private AudioManager myAudioManager;
    private boolean mPhoneIsSilent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = (TextView)findViewById(R.id.title);
        image = (ImageView)findViewById(R.id.imageView);


        myAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);


        checkPhoneRingMode();
        setButtonClickListener();
    }

    private void setButtonClickListener() {
        toggle = (Button)findViewById(R.id.button_toggle);
        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mPhoneIsSilent){
                    myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    mPhoneIsSilent = false;
                }else{
                    myAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                    mPhoneIsSilent = true;
                }

                toggleUI();

            }
        });
    }


    private void checkPhoneRingMode(){
            int ringerMode = myAudioManager.getRingerMode();
            if(ringerMode==AudioManager.RINGER_MODE_VIBRATE){
                mPhoneIsSilent = true;
            }else{
                mPhoneIsSilent = false;
            }
    }

    private void toggleUI(){
        if(mPhoneIsSilent){
            title.setText("Vibrate");
            newPhoneImage = getResources().getDrawable(R.mipmap.phone_silent);
            image.setImageDrawable(newPhoneImage);
            Toast.makeText(MainActivity.this,"Now in Vibrate Mode",
            Toast.LENGTH_SHORT).show();
        }else{
            title.setText("Loud");
            newPhoneImage = getResources().getDrawable(R.mipmap.phone_on);
            image.setImageDrawable(newPhoneImage);
            Toast.makeText(MainActivity.this,"Now in Loud Mode",
            Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onResume(){
        super.onResume();
        checkPhoneRingMode();
        toggleUI();
    }

}