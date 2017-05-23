package com.example.owned.ownedlock;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

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
                        ft.addToBackStack(null);
                        ft.commit();
                        return true;
                    case R.id.navigation_preview:
                        PreveiwFragment = new PreviewFragment(settingsResource);
                        ft.replace(R.id.content, PreveiwFragment);
                        ft.commit();
                        return true;
                    case R.id.navigation_info:
                        ft.replace(R.id.content, InfoFragment);
                        ft.addToBackStack(null);
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
    }
}