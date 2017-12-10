package cisc181.blackjack;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class BlackJack extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_jack);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        final AlphaAnimation fadeIn = new AlphaAnimation(0.0f , 1.0f );
        final Button startButton = findViewById(R.id.startButton);
        final Button instrButton = findViewById(R.id.instrButton);
        final ImageView splash = findViewById(R.id.blackjackpic);
        final TextView text = findViewById(R.id.blackjacktext);
        final SeekBar volumeControl = findViewById(R.id.volumeControl);
        final CheckBox muteBox = findViewById(R.id.muteBox);
        final TextView volumeText = findViewById(R.id.volumeText);
        fadeIn.setFillAfter(true);
        fadeIn.setDuration(2000);
        text.startAnimation(fadeIn);
        splash.startAnimation(fadeIn);
        startButton.startAnimation(fadeIn);
        instrButton.startAnimation(fadeIn);
        volumeText.startAnimation(fadeIn);
        volumeControl.startAnimation(fadeIn);
        muteBox.startAnimation(fadeIn);
        initControls(volumeControl, muteBox);
        volumeControl.setMax(100);
        volumeControl.setProgress(0);
    }

    private void initControls(SeekBar volumeSeekbar, CheckBox muteBox)
    {
        try
        {
            final AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
            MediaPlayer mp = MediaPlayer.create(this, R.raw.music);
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mp.setLooping(true);
            mp.start();

            volumeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
            {
                @Override
                public void onStopTrackingTouch(SeekBar arg0)
                {
                }

                @Override
                public void onStartTrackingTouch(SeekBar arg0)
                {
                }

                @Override
                public void onProgressChanged(SeekBar arg0, int progress, boolean arg2)
                {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress, 0);
                }
            });
            if (muteBox.isChecked()){
                mp.setVolume(0,0);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public void startGame(View view){

        Intent startNewActivity = new Intent(this, displayCardGame.class);
        startActivity(startNewActivity);

    }
    public void openInstructions(View view){

        Intent startNewActivity = new Intent(this, displayInstructions.class);
        startActivity(startNewActivity);

    }
    public void muteFunc(View view) {
        final CheckBox muteBox = findViewById(R.id.muteBox);
        if (muteBox.isChecked()) {
            AudioManager amanager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
            amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);
            amanager.setStreamMute(AudioManager.STREAM_ALARM, true);
            amanager.setStreamMute(AudioManager.STREAM_MUSIC, true);
            amanager.setStreamMute(AudioManager.STREAM_RING, true);
            amanager.setStreamMute(AudioManager.STREAM_SYSTEM, true);
        }
    }
}
