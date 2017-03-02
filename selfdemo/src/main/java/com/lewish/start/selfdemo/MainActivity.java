package com.lewish.start.selfdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnShowNotify;
    private Button mBtnClearNotify;
    private Button mBtnShowCustomeNotify;
    private static final int DEFAULT_NOTIFICATION_ID = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnShowNotify = (Button)findViewById(R.id.mBtnShowNotify);
        mBtnClearNotify = (Button)findViewById(R.id.mBtnClearNotify);
        mBtnShowCustomeNotify = (Button)findViewById(R.id.mBtnShowCustomeNotify);

        mBtnShowNotify.setOnClickListener(this);
        mBtnClearNotify.setOnClickListener(this);
        mBtnShowCustomeNotify.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mBtnShowNotify :
                showCZNotify();
//                showNotify();
//                showStartActivityNotify();
                break;
            case R.id.mBtnShowCustomeNotify:
                showCustomeNotify();
                break;
            case R.id.mBtnClearNotify:
                clearNotify(1);
                break;
        }
    }

    /**
     * 显示自定义通知
     */
    private void showCustomeNotify() {
        RemoteViews view_custom = new RemoteViews(getPackageName(), R.layout.view_custom);
        //设置对应IMAGEVIEW的ID的资源图片
        view_custom.setImageViewResource(R.id.custom_icon, R.mipmap.ic_launcher);
        view_custom.setTextViewText(R.id.tv_custom_title, "今日头条");
        view_custom.setTextViewText(R.id.tv_custom_content, "金州勇士官方宣布球队已经解雇了主帅马克-杰克逊，随后宣布了最后的结果。");
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        Notification notification = mBuilder
                .setContent(view_custom)
                .setTicker("测试通知来啦")//通知首次出现在通知栏，带上升动画效果的
                .setAutoCancel(false)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setOngoing(false)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        mNotificationManager.notify(DEFAULT_NOTIFICATION_ID,notification);
    }

    /**
     提醒标志符成员：
     Notification.FLAG_SHOW_LIGHTS              //三色灯提醒，在使用三色灯提醒时候必须加该标志符
     Notification.FLAG_ONGOING_EVENT          //发起正在运行事件（活动中）
     Notification.FLAG_INSISTENT   //让声音、振动无限循环，直到用户响应 （取消或者打开）
     Notification.FLAG_ONLY_ALERT_ONCE  //发起Notification后，铃声和震动均只执行一次
     Notification.FLAG_AUTO_CANCEL      //用户单击通知后自动消失
     Notification.FLAG_NO_CLEAR          //只有全部清除时，Notification才会清除 ，不清楚该通知(QQ的通知无法清除，就是用的这个)
     Notification.FLAG_FOREGROUND_SERVICE    //表示正在运行的服务
     */
    /**
     * 显示普通Notification
     */
    private void showNotify() {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        Notification notification = mBuilder
                .setTicker("测试通知来啦")//通知首次出现在通知栏，带上升动画效果的
                .setContentTitle("我是标题")
                .setContentText("我是内容")
                .setAutoCancel(false)
                .setWhen(System.currentTimeMillis())
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setOngoing(false)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        mNotificationManager.notify(DEFAULT_NOTIFICATION_ID,notification);
    }

    /**
     * 显示常驻Notification
     */
    private void showCZNotify(){
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        Notification notification = mBuilder
                .setTicker("测试通知来啦")//通知首次出现在通知栏，带上升动画效果的
                .setContentTitle("我是标题")
                .setContentText("我是内容")
                .setAutoCancel(false)
                .setWhen(System.currentTimeMillis())
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setOngoing(false)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();
        notification.flags = Notification.FLAG_ONGOING_EVENT;
        mNotificationManager.notify(DEFAULT_NOTIFICATION_ID,notification);
    }
    /**
     * 点击跳转到制定Activity
     */
    private void showStartActivityNotify(){
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        Notification notification = mBuilder
                .setTicker("测试通知来啦")//通知首次出现在通知栏，带上升动画效果的
                .setContentTitle("我是标题")
                .setContentIntent(PendingIntent.getActivity(this,0,getIntent(),0))
                .setContentText("我是内容")
                .setAutoCancel(false)
                .setWhen(System.currentTimeMillis())
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setOngoing(false)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        mNotificationManager.notify(DEFAULT_NOTIFICATION_ID,notification);
    }
    /**
     * 清除指定ID的通知栏
     */
    public void clearNotify(int notifyId){
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotificationManager.cancel(notifyId);//删除一个特定的通知ID对应的通知
//		mNotification.cancel(getResources().getString(R.string.app_name));
    }
    /**
     * 清除所有通知栏
     */
    public void clearAllNotify() {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotificationManager.cancelAll();// 删除你发的所有通知
    }
}
