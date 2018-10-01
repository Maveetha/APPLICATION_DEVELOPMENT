package com.example.maveetha.passwordapp;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {
    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;
    private int notification_id;
    private RemoteViews remoteViews;
    private Context context;


    Button b0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b0=(Button)findViewById(R.id.wlogin);
        
            context = this;
            notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            builder = new NotificationCompat.Builder(this);

            remoteViews = new RemoteViews(getPackageName(),R.layout.custom_notification);
            remoteViews.setImageViewResource(R.id.notif_icon,R.mipmap.ic_launcher);
            remoteViews.setTextViewText(R.id.notif_title,"Welcome Again!");
            remoteViews.setProgressBar(R.id.progressBar,100,40,true);


            findViewById(R.id.exitid).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    notification_id = (int) System.currentTimeMillis();

                    Intent button_intent = new Intent("button_click");
                    button_intent.putExtra("id",notification_id);
                    PendingIntent button_pending_event = PendingIntent.getBroadcast(context,notification_id,
                            button_intent,0);

                    remoteViews.setOnClickPendingIntent(R.id.exitid,button_pending_event);

                    Intent notification_intent = new Intent(context,MainActivity.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(context,0,notification_intent,0);

                    builder.setSmallIcon(R.mipmap.ic_launcher)
                            .setAutoCancel(true)
                            .setCustomBigContentView(remoteViews)
                            .setContentIntent(pendingIntent);

                    notificationManager.notify(notification_id,builder.build());
                    finish();
                    System.exit(0);


                }
            });


        }

    public void clk0(View v){
        Intent i = new Intent(this,Login.class);
        startActivity(i);
    }
}
