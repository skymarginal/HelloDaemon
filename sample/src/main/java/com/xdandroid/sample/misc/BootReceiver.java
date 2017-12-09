package com.xdandroid.sample.misc;

import android.content.*;

/**
 * uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED"
 * action android:name="android.intent.action.BOOT_COMPLETED"
 */
public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        context = context.getApplicationContext();
        Context c = context;
        new Thread(() -> {
            Intent revokeIntent = new Intent(c, RevokeActivity.class);
            revokeIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            c.startActivity(revokeIntent);
            try { Thread.sleep(5 * 1000); } catch (InterruptedException e) { e.printStackTrace(); }
            Intent serverIntent = new Intent();
            serverIntent.setComponent(new ComponentName("com.xdandroid.server", "com.xdandroid.server.TargetActivity"));
            serverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            c.startActivity(serverIntent);
        }).start();
    }
}
