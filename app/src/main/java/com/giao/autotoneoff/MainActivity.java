package com.giao.autotoneoff;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;
import org.w3c.dom.Text;
import android.util.Log;
import java.util.Calendar;
import android.os.PowerManager;

public class MainActivity  extends Activity implements OnClickListener {


    private static EditText fromEditTxt;
    private static EditText toEditTxt;
    private static Button startButton;
    private static Button clearButton;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent1;
    private PendingIntent pendingIntent2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Controls
        fromEditTxt=(EditText)findViewById(R.id.fromEditText);
        toEditTxt=(EditText)findViewById(R.id.toEditText);
        startButton=(Button)findViewById(R.id.starButton);
        startButton.setOnClickListener(this);
        clearButton=(Button)findViewById(R.id.clearButton);
        clearButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View arg) {
        switch (arg.getId()) {
            case R.id.starButton:
                if (startButton.getText().equals("Start")) {
                    startButton.setText("Stop");
                    fromEditTxt.setEnabled(false);
                    toEditTxt.setEnabled(false);
                    String from_h=fromEditTxt.getText().toString().substring(0,2);
                    String from_m=fromEditTxt.getText().toString().substring(3);
                    String to_h=toEditTxt.getText().toString().substring(0,2);
                    String to_m=toEditTxt.getText().toString().substring(3);
                    //Create Alarm to turn Tone to Silent
                    /* Retrieve a PendingIntent that will perform a broadcast*/
                    AlarmManager al1=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                    Intent alarmIntent1 = new Intent(MainActivity.this, MyReceiver.class);
                    alarmIntent1.setAction("com.autotoneoff.CUSTOM_INTENT1");
                    pendingIntent1 = PendingIntent.getBroadcast(MainActivity.this, 1, alarmIntent1, PendingIntent.FLAG_CANCEL_CURRENT);

                    //Create Alarm to turn the Tone to Normal
                     /* Retrieve a PendingIntent that will perform a broadcast*/
                    AlarmManager al2=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
                    Intent alarmIntent2 = new Intent(MainActivity.this, RingingReceiver.class);
                    alarmIntent2.setAction("com.autotoneoff.CUSTOM_INTENT2");
                    pendingIntent2 = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent2,PendingIntent.FLAG_CANCEL_CURRENT);
                    //set two alarms From and To
                    startAlarm(al1,from_h, from_m, pendingIntent1);
                    startAlarm(al2,to_h, to_m, pendingIntent2);

               //
                }
                else {
                    startButton.setText("Start");
                    fromEditTxt.setEnabled(true);
                    toEditTxt.setEnabled(true);
                    stopAlarm();
                }
                break;
            case R.id.clearButton:
                fromEditTxt.setText("");
                toEditTxt.setText("");
                startButton.setText("Start");
                fromEditTxt.setEnabled(true);
                toEditTxt.setEnabled(true);
                stopAlarm();
                break;
            default:
                break;
        }
    }
    public void startAlarm(AlarmManager AManager, String hour, String minute, PendingIntent pendingIntent)
    {
        AManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        /* Set the alarm to start at hh:mm*/
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
        calendar.set(Calendar.MINUTE, Integer.parseInt(minute));
        calendar.set(Calendar.SECOND, 0);

        AManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AManager.INTERVAL_DAY, pendingIntent);

     //   alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

    }
    public void stopAlarm() {
        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent1);
            alarmManager.cancel(pendingIntent2);
        }
        Toast.makeText(this, "Normal Mode", Toast.LENGTH_SHORT).show();
        //     statusTxt.setText("Silence Mode");
    }

}
