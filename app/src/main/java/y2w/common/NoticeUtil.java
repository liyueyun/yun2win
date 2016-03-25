 package y2w.common;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.support.v4.app.NotificationCompat;

import com.yun2win.demo.R;

import y2w.base.AppContext;


 public class NoticeUtil {

     public static final int NOTIFICATION_ID = 140506002;
     public static String WHOSE="NONE";//谁的通知栏
     /**
      * 消息通知栏
      *
      * @param title
      *            标题
      * @param content
      *            消息內容
      * @param msgtype
      *            消息类型
      */
     @SuppressWarnings("deprecation")
     public static void notice(AppContext appContext,String title, String content, int msgtype,
             String othersideid,String chattype) {
         try{
             // 定义NotificationManager
             NotificationManager mNotificationManager = (NotificationManager) appContext
                     .getSystemService(Context.NOTIFICATION_SERVICE);
             // 定义通知栏展现的内容信息
             int icon = R.drawable.ic_launcher;
             Bitmap bitmap = BitmapFactory.decodeResource(appContext.getResources(),
                     icon);
             CharSequence tickerText = "理约云";
             long when = System.currentTimeMillis();
             CharSequence contentTitle = title;
             CharSequence contentText = content;

             Notification notification;
             Intent notificationIntent = null;
             WHOSE=othersideid;


             if (VERSION.SDK_INT >= VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
                 notification = (new NotificationCompat.Builder(appContext)
                         .setContentText(contentText).setContentTitle(contentTitle).setLargeIcon(bitmap)).getNotification()
                         ;
                 notification.icon = icon;
                 notification.when = when;
                 notification.tickerText = tickerText;
                 notification.flags = Notification.FLAG_AUTO_CANCEL;
                 notification.audioStreamType= android.media.AudioManager.ADJUST_LOWER;
                 notification.defaults = Notification.DEFAULT_SOUND;

             } else {
                 notification = new Notification(icon, tickerText, when);
                 notification.flags = Notification.FLAG_AUTO_CANCEL;
                 // 定义下拉通知栏时要展现的内容信息
               /*  notification.setLatestEventInfo(appContext, contentTitle, contentText,
                         contentIntent);*/
                 notification.audioStreamType= android.media.AudioManager.ADJUST_LOWER;
                 notification.defaults = Notification.DEFAULT_SOUND;

             }
             mNotificationManager.notify(NOTIFICATION_ID, notification);
             if (bitmap != null && bitmap.isRecycled()) {
                 bitmap.recycle();
                 bitmap = null;
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
     }

 }
