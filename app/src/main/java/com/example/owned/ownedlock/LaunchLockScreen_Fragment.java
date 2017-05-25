package com.example.owned.ownedlock;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class LaunchLockScreen_Fragment extends Fragment {

    private Button Start;

    public LaunchLockScreen_Fragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_launch_lockscreen, container, false);
        Start = (Button) view.findViewById(R.id.Button_Launch);

        Start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v) {
                startActivity(new Intent(getActivity(), Start_LOCK_SCREEN.class));
        }
        });

        return view;
    }

}
