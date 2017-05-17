package com.example.owned.ownedlock;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class SettingsFragment extends Fragment {

    private SettingsResource settingsResource;
    private Switch Switch_12h;
    private Switch Switch_24h;
    private Switch Reverse;
    private MyTimerTask MyTimerTask = new MyTimerTask();

    public SettingsFragment(SettingsResource settingsResource) {
        this.settingsResource = settingsResource;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        Switch_12h = (Switch) view.findViewById (R.id.Switch_12h);
        Switch_24h = (Switch) view.findViewById (R.id.Switch_24h);
        Reverse = (Switch) view.findViewById (R.id.Switch_Reverse);

        final Switch hourSwitch = (Switch) view.findViewById(R.id.Switch_Hours);
        final Switch minuteSwitch = (Switch) view.findViewById(R.id.Switch_Minutes);
        final Switch secondSwitch = (Switch) view.findViewById(R.id.Switch_Seconds);

        Switch_12h.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick (View e) {
                if (Switch_12h.isChecked()) {
                    settingsResource.setSwitch_12h (true);
                    Switch_12h.setClickable(false);
                    Switch_24h.setClickable(true);
                        if (Switch_24h.isChecked())
                            settingsResource.setSwitch_24h (false);
                }
            }
        });
        Switch_24h.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick (View e) {
                if (Switch_24h.isChecked()) {
                    settingsResource.setSwitch_24h (true);
                    Switch_24h.setClickable(false);
                    Switch_12h.setClickable(true);
                    if (Switch_12h.isChecked())
                        settingsResource.setSwitch_12h (false);
                }
            }
        });
        Switch_24h.setClickable(false);

        Reverse.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View e) {
                if (Reverse.isChecked())
                    settingsResource.setReverse(true);
                else
                    settingsResource.setReverse (false);
            }
        });


        hourSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hourSwitch.isChecked()) {
                    TimeSettingsFragment fragment =
                            new TimeSettingsFragment(TimeSettingsFragment.HOUR, settingsResource);
                    FragmentTransaction ft = ((MainActivity)getActivity()).getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.content, fragment);
                    ft.addToBackStack(null);
                    ft.commit();
                } else {
                    // off changed
                }

            }
        });

        minuteSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (minuteSwitch.isChecked()) {
                    TimeSettingsFragment fragment =
                            new TimeSettingsFragment(TimeSettingsFragment.MINUTE, settingsResource);
                    FragmentTransaction ft = ((MainActivity)getActivity()).getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.content, fragment);
                    ft.addToBackStack(null);
                    ft.commit();
                } else {
                    // off changed
                }

            }
        });

        secondSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (secondSwitch.isChecked()) {
                    TimeSettingsFragment fragment =
                            new TimeSettingsFragment(TimeSettingsFragment.SECOND, settingsResource);
                    FragmentTransaction ft = ((MainActivity)getActivity()).getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.content, fragment);
                    ft.addToBackStack(null);
                    ft.commit();
                } else {
                    // off changed
                }

            }
        });



        return view;
    }
    class MyTimerTask extends AsyncTask<Void, Void, Void> {
        boolean inWork = true;
        @Override
        protected void onProgressUpdate(Void... values) {
            inWork = true;
                if (settingsResource.isSwitch_12h()) {
                    Switch_12h.setChecked(true);
                    Switch_12h.setText("Вкл");
                }
                else {
                    Switch_12h.setChecked(false);
                    Switch_12h.setText("Выкл");
                }
                if (settingsResource.isSwitch_24h()) {
                    Switch_24h.setChecked(true);
                    Switch_24h.setText("Вкл");
                }
                else {
                    Switch_24h.setChecked(false);
                    Switch_24h.setText("Выкл");
                }
                if (settingsResource.isReverse()) {
                    Reverse.setChecked(true);
                    Reverse.setText("Вкл");
                }
                else {
                    Reverse.setChecked(false);
                    Reverse.setText("Выкл");
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
}