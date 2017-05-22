package com.example.owned.ownedlock;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    public final String SAVED_PASSWORD = "PASSWORD";
    private PreviewFragment PreveiwFragment;
    private InfoFragment InfoFragment;
    private SettingsFragment SettingsFragment;

    private SettingsResource settingsResource = new SettingsResource(false, true, false, false, false, false);

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()) {
                    case R.id.navigation_settings:
                        ft.replace(R.id.content, SettingsFragment);
                        ft.commit();
                        return true;
                    case R.id.navigation_preview:
                        PreveiwFragment = new PreviewFragment(settingsResource);
                        ft.replace(R.id.content, PreveiwFragment);
                        ft.commit();
                        return true;
                    case R.id.navigation_info:
                        ft.replace(R.id.content, InfoFragment);
                        ft.commit();
                        return true;
                }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        PreveiwFragment = new PreviewFragment(settingsResource);
        InfoFragment = new InfoFragment();
        SettingsFragment = new SettingsFragment(settingsResource);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_preview);

//        SharedPreferences sharedPreferences = this.getSharedPreferences(SAVED_PASSWORD, 0);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("TEST", "Hugant");
//        editor.apply();
    }


    public void setPreferencePassword(String key, String password) {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, password);
        editor.apply();
    }

    public String getPreferencePassword(String key) {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }
}