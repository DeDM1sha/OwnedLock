package com.example.owned.ownedlock;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimeSettingsFragment extends Fragment {
    public static final String HOUR = "hour";
    public static final String MINUTE = "minute";
    public static final String SECOND = "second";

    private SettingsResource settingsResource;
    private int title = -1;
    private int description = -1;

    public TimeSettingsFragment(String type, SettingsResource settingsResource) {
        this.settingsResource = settingsResource;

        switch (type) {
            case HOUR:
                title = R.string.settings_time_title_hour;
                description = R.string.settings_time_description_hour;
                break;

            case MINUTE:
                title = R.string.settings_time_title_minute;
                description = R.string.settings_time_description_minute;
                break;

            case SECOND:
                title = R.string.settings_time_title_second;
                description = R.string.settings_time_description_second;
                break;
        }
        if (title == -1)
            title = R.string.not_found;
        if (description == -1)
            description = R.string.not_found;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_settings, container, false);

        ((TextView) view.findViewById(R.id.Title)).setText(title);
        ((TextView) view.findViewById(R.id.Description)).setText(description);


        return view;
    }



}
