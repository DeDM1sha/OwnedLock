package com.example.owned.ownedlock;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

public class SettingsFragment extends Fragment {

    private SettingsResource settingsResource;
    private Switch Switch_12h;
    private Switch Switch_24h;
    private Switch Switch_Reverse;
    private Switch Switch_Hours;
    private Switch Switch_Minutes;
    private Switch Switch_Seconds;
    private Button Button_DefaultPassword;
    private Button Button_AlternatePassword;

    private boolean Clicked = false;

    private MyTimerTask MyTimerTask = new MyTimerTask();

    public SettingsFragment(SettingsResource settingsResource) {
        this.settingsResource = settingsResource;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        Switch_12h = (Switch) view.findViewById(R.id.Switch_12h);
        Switch_24h = (Switch) view.findViewById(R.id.Switch_24h);
        Switch_Reverse = (Switch) view.findViewById(R.id.Switch_Reverse);
        Switch_Hours = (Switch) view.findViewById(R.id.Switch_Hours);
        Switch_Minutes = (Switch) view.findViewById(R.id.Switch_Minutes);
        Switch_Seconds = (Switch) view.findViewById(R.id.Switch_Seconds);
        Button_DefaultPassword = (Button) view.findViewById(R.id.Button_DefaultPassword);
        Button_AlternatePassword = (Button) view.findViewById(R.id.Button_AlternatePassword);
/////////////////////////////////////////////////////////////////////////////
        Switch_12h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View e) {
                if (Switch_12h.isChecked()) {
                    settingsResource.setSwitch_12h(true);
                    settingsResource.setSwitch_24h(false);
                }
            Clicked = true;
            }
        });
/////////////////////////////////////////////////////////////////////////////
        Switch_24h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View e) {
                if (Switch_24h.isChecked()) {
                    settingsResource.setSwitch_24h(true);
                    settingsResource.setSwitch_12h(false);
                }
            Clicked = true;
            }
        });
/////////////////////////////////////////////////////////////////////////////
        Switch_Reverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View e) {
                if (Switch_Reverse.isChecked())
                    settingsResource.setSwitch_Reverse(true);
                else
                    settingsResource.setSwitch_Reverse(false);
            Clicked = true;
            }
        });
/////////////////////////////////////////////////////////////////////////////
        Switch_Hours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Switch_Hours.isChecked()) {
                    settingsResource.setSwitch_Hours(true);
                    TimeSettingsFragment fragment = new TimeSettingsFragment(TimeSettingsFragment.HOUR, settingsResource);
                    FragmentTransaction ft = (getActivity()).getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.content, fragment);
                    ft.addToBackStack(null);
                    ft.commit();
                } else
                    settingsResource.setSwitch_Hours(false);
            Clicked = true;
            }
        });
/////////////////////////////////////////////////////////////////////////////
        Switch_Minutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Switch_Minutes.isChecked()) {
                    settingsResource.setSwitch_Minutes(true);
                    TimeSettingsFragment fragment = new TimeSettingsFragment(TimeSettingsFragment.MINUTE, settingsResource);
                    FragmentTransaction ft = (getActivity()).getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.content, fragment);
                    ft.addToBackStack(null);
                    ft.commit();
                } else
                    settingsResource.setSwitch_Minutes(false);
            Clicked = true;
            }
        });
/////////////////////////////////////////////////////////////////////////////
        Switch_Seconds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Switch_Seconds.isChecked()) {
                    settingsResource.setSwitch_Seconds(true);
                    TimeSettingsFragment fragment = new TimeSettingsFragment(TimeSettingsFragment.SECOND, settingsResource);
                    FragmentTransaction ft = (getActivity()).getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.content, fragment);
                    ft.addToBackStack(null);
                    ft.commit();
                } else
                    settingsResource.setSwitch_Seconds(false);
            Clicked = true;
            }
        });
/////////////////////////////////////////////////////////////////////////////
        Button_DefaultPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                ChangingPasswordsFragment fragment = new ChangingPasswordsFragment("Default");
                FragmentTransaction ft = (getActivity()).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content, fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
/////////////////////////////////////////////////////////////////////////////
        Button_AlternatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangingPasswordsFragment fragment = new ChangingPasswordsFragment("Alternate");
                FragmentTransaction ft = (getActivity()).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content, fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
/////////////////////////////////////////////////////////////////////////////
        CreateView();
        return view;
    }
    class MyTimerTask extends AsyncTask<Void, Void, Void> {
        boolean inWork = true;

        @Override
        protected void onProgressUpdate(Void... values) {
            inWork = true;
                if (Clicked) {
                    CreateView();
                    Clicked = false;
                }
        }

        @Override
        protected Void doInBackground(Void... params) {

            while (inWork) {
                publishProgress();
                SystemClock.sleep(1);
            }
            return null;
        }

        private void stop() {
            inWork = false;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        MyTimerTask.stop();
    }

    @Override
    public void onResume() {
        super.onResume();
        MyTimerTask = new MyTimerTask();
        MyTimerTask.execute();
    }

    private void CreateView() {
/////////////////////////////////////////////////////////////////////////////
        if (settingsResource.isSwitch_12h()) {
            Switch_12h.setChecked(true);
            Switch_12h.setText(R.string.on);
            Switch_12h.setClickable(false);
        } else {
            Switch_12h.setChecked(false);
            Switch_12h.setText(R.string.off);
            Switch_12h.setClickable(true);
        }
/////////////////////////////////////////////////////////////////////////////
        if (settingsResource.isSwitch_24h()) {
            Switch_24h.setChecked(true);
            Switch_24h.setText(R.string.on);
            Switch_24h.setClickable(false);
        } else {
            Switch_24h.setChecked(false);
            Switch_24h.setText(R.string.off);
            Switch_24h.setClickable(true);
        }
/////////////////////////////////////////////////////////////////////////////
        if (settingsResource.isSwitch_Reverse()) {
            Switch_Reverse.setChecked(true);
            Switch_Reverse.setText(R.string.on);
        } else {
            Switch_Reverse.setChecked(false);
            Switch_Reverse.setText(R.string.off);
        }
/////////////////////////////////////////////////////////////////////////////
        if (settingsResource.isSwitch_Hours()) {
            Switch_Hours.setChecked(true);
            Switch_Hours.setText(R.string.on);
        } else {
            Switch_Hours.setChecked(false);
            Switch_Hours.setText(R.string.off);
        }
/////////////////////////////////////////////////////////////////////////////
        if (settingsResource.isSwitch_Minutes()) {
            Switch_Minutes.setChecked(true);
            Switch_Minutes.setText(R.string.on);
        } else {
            Switch_Minutes.setChecked(false);
            Switch_Minutes.setText(R.string.off);
        }
/////////////////////////////////////////////////////////////////////////////
        if (settingsResource.isSwitch_Seconds()) {
            Switch_Seconds.setChecked(true);
            Switch_Seconds.setText(R.string.on);
        } else {
            Switch_Seconds.setChecked(false);
            Switch_Seconds.setText(R.string.off);
        }
    }
}