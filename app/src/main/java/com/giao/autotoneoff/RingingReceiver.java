package com.giao.autotoneoff;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.widget.Toast;

/**
 * Created by Long on 1/31/2016.
 */
public class RingingReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //Toast.makeText(context, "Intent Detected.", Toast.LENGTH_LONG).show();
        //   statusTxt.setText("OFF");
        AudioManager aManager=(AudioManager)context.getSystemService(context.AUDIO_SERVICE);

        if(aManager.getRingerMode() == aManager.RINGER_MODE_SILENT) {
            aManager.setRingerMode(aManager.RINGER_MODE_NORMAL);
            Toast.makeText(context, "Normal Mode.", Toast.LENGTH_LONG).show();

        }
    }
}
