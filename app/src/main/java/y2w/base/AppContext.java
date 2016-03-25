package y2w.base;

import android.app.Application;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.yun2win.imlib.IMClient;

import y2w.common.UserInfo;
import y2w.ui.activity.LoginActivity;

/**
 * Created by maa2 on 2016/1/8.
 */
public class AppContext extends Application{

    private final String TAG = "AppContext";
    private static AppContext appContext;
    private String appKey = "";
    public static AppContext getAppContext(){
        return  appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        IMClient.init(this);
        appKeyInit();
    }

    public String getAppKey(){
        return appKey;
    }

    private void appKeyInit(){
        ApplicationInfo appInfo = null;
        try {
            appInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            if (appInfo.metaData != null) {
                appKey = (appInfo.metaData.get("YUN2WIN_APP_KEY") != null) ? appInfo.metaData.get("YUN2WIN_APP_KEY").toString() : "";
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void logout(){
        UserInfo.clearPassWord();
        Intent intent = new Intent(AppData.getInstance().getMainActivity(), LoginActivity.class);
        AppData.getInstance().getMainActivity().startActivity(intent);
    }

}
