package com.example.owned.ownedlock;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;

import java.util.List;

final class Validator {

    private Validator() throws InstantiationException {
        throw new InstantiationException("This class is not for instantiation");
    }

    /**
     * check whether be empty/null or not
     *
     * @param string
     * @return
     */
    public static boolean isEffective(String string) {
        if ((string == null) || ("".equals(string)) || (" ".equals(string))
                || ("null".equals(string)) || ("\n".equals(string)))
            return false;
        else
            return true;
    }
}
public class LauncherHomeActivity extends Activity {
    private String mPackageName;
    private String mClassName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher_home);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = new Intent(this, LockScreenService.class);
        startService(intent);

        getLauncherPackageName(this);

        if (!LockScreenActivity.isLocked) {
            if (Validator.isEffective(mPackageName) && Validator.isEffective(mClassName)) {
                Intent systemIntent = new Intent();
                systemIntent.setComponent(new ComponentName(mPackageName, mClassName));
                startActivity(systemIntent);
            }
        }

        moveTaskToBack(false);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    /**
     * get the system launcher package name and class name,we use system launcher as default<br/>
     * also we can let user to choose a launcher from system laucher and our custom launchers,"BaiDu" lock screen app and "Go" lock screen app just do this.<br/>
     *
     * @param context
     */
    private void getLauncherPackageName(Context context) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        List<ResolveInfo> resInfoList = context.getPackageManager().queryIntentActivities(intent, PackageManager.GET_ACTIVITIES);
        if (resInfoList != null) {
            ResolveInfo resInfo;
            for (int i = 0; i < resInfoList.size(); i++) {
                resInfo = resInfoList.get(i);
                if ((resInfo.activityInfo.applicationInfo.flags &
                        ApplicationInfo.FLAG_SYSTEM) > 0) {
                    mPackageName = resInfo.activityInfo.packageName;
                    mClassName = resInfo.activityInfo.name;

                    break;
                }
            }
        }
    }
}
