package com.giao.autotoneoff;
import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.widget.Toast;


/**
 * Created by Long on 1/26/2016.
 */

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //Toast.makeText(context, "Intent Detected.", Toast.LENGTH_LONG).show();
        //   statusTxt.setText("OFF");
        AudioManager aManager=(AudioManager)context.getSystemService(context.AUDIO_SERVICE);
        aManager.setRingerMode(aManager.RINGER_MODE_SILENT);
        Toast.makeText(context, "Silent Mode.", Toast.LENGTH_LONG).show();

    }


}
