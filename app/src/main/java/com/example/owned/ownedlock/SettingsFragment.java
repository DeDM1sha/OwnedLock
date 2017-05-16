package com.example.owned.ownedlock;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;


public class SettingsFragment extends Fragment {

    private SettingsResource settingsResource;

    public SettingsFragment(SettingsResource settingsResource) {
        this.settingsResource = settingsResource;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        final CheckBox first = (CheckBox) view.findViewById(R.id.checkBox);
        final CheckBox second = (CheckBox) view.findViewById(R.id.checkBox2);
        final CheckBox third = (CheckBox) view.findViewById(R.id.checkBox3);

        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View e) {
                if (first.isChecked())
                    settingsResource.setFirst(true);
                else
                    settingsResource.setFirst(false);
            }
        });

        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View e) {
                if (second.isChecked())
                    settingsResource.setSecond(true);
                else
                    settingsResource.setSecond(false);
            }
        });

        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View e) {
                if (third.isChecked())
                    settingsResource.setSecond(true);
                else
                    settingsResource.setSecond(false);
            }
        });

        return view;
    }

}
