package com.reactnativenotificationbadge;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.module.annotations.ReactModule;

import java.util.List;
import java.util.Map;

@ReactModule(name = NotificationBadgeModule.NAME)
public class NotificationBadgeModule extends ReactContextBaseJavaModule {
    public static final String NAME = "NotificationBadge";
    private static Integer UNIQUE_ID = 1;
    private static String CHANNEL_ID = "count_channel";

    public NotificationBadgeModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    @NonNull
    public String getName() {
        return "NotificationBadge";
    }

    @ReactMethod
    public void configure(String title, String text) {
        // Get the context.
        ReactApplicationContext context = getReactApplicationContext();

        // Get the sharedPreferences and open the editor.
        SharedPreferences sharedPreferences = context.getSharedPreferences(getName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Put the variables in the editor and save it.
        editor.putString("title", title);
        editor.putString("text", text);
        editor.apply();
    }

    @ReactMethod
    public void setNumber(int count) {
        // Get the context.
        ReactApplicationContext context = getReactApplicationContext();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            // Get the notification manager.
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            // Make the channel. If it already exists Android will not create another.
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "Count", NotificationManager.IMPORTANCE_LOW);
            notificationChannel.setShowBadge(true);
            notificationChannel.setDescription("Used to show badges on the app icon");
            notificationManager.createNotificationChannel(notificationChannel);

            // If the count is zero we will cancel the notification.
            if (count == 0) {
                notificationManager.cancel(UNIQUE_ID);
                return;
            }

            // Open the sharedPreferences
            SharedPreferences sharedPreferences = context.getSharedPreferences(getName(), Context.MODE_PRIVATE);

            // Get the title and text in case the user has configured those.
            String title = sharedPreferences.getString("title", "Notifications");
            String text = sharedPreferences.getString("text", "You have %count% notifications");
            String processedText = text.replace("%count%", Integer.toString(count));

            // Make the actual notification.
            Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(processedText)
                .setNumber(count)
                .setSmallIcon(R.drawable.ic_notify_status)
                .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
                .build();

            // Send the notification.
            notificationManager.notify(UNIQUE_ID, notification);
      }
    }

}
