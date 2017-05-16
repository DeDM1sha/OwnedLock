package com.example.owned.ownedlock;


import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;


public class PreviewFragment extends Fragment implements View.OnClickListener {
    private TextView TimeView;
    private TextView PasswordView;
    private Button[] buttons = new Button[10];
    private Button DeleteButton;
    private String Password = "";
    private String Time;
    private boolean Click = false;
    private SettingsResource settingsResource;

    private MyTimerTask MyTimerTask = new MyTimerTask();

    public PreviewFragment(SettingsResource settingsResource) {
        this.settingsResource = settingsResource;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_preview, container, false);
        TimeView = (TextView) view.findViewById (R.id.TimeView);
        PasswordView = (TextView) view.findViewById (R.id.PasswordView);
        buttons[0] = (Button) view.findViewById (R.id.Button_0);
        buttons[1] = (Button) view.findViewById (R.id.Button_1);
        buttons[2] = (Button) view.findViewById (R.id.Button_2);
        buttons[3] = (Button) view.findViewById (R.id.Button_3);
        buttons[4] = (Button) view.findViewById (R.id.Button_4);
        buttons[5] = (Button) view.findViewById (R.id.Button_5);
        buttons[6] = (Button) view.findViewById (R.id.Button_6);
        buttons[7] = (Button) view.findViewById (R.id.Button_7);
        buttons[8] = (Button) view.findViewById (R.id.Button_8);
        buttons[9] = (Button) view.findViewById (R.id.Button_9);
        DeleteButton = (Button) view.findViewById (R.id.Button_Delete);
            for (short i = 0; i < 10; i++)
                buttons[i].setOnClickListener(this);
        DeleteButton.setOnClickListener(this);

        //////////////////////////////////////////////////////////
        PasswordView.setText(   "First:" + settingsResource.isFirst() + "\n" +
                                "Second:" + settingsResource.isSecond() + "\n" +
                                "Third:" + settingsResource.isThird());
        //////////////////////////////////////////////////////////

        TimeView.setText(getTime());
        return view;
    }

    @Override
    public void onClick(View v) {

        if (Click){
            Password = "";
            Click = false;
            PasswordView.setTextColor(Color.WHITE);
        }
        Button button = (Button) v;
            if (button.getText().equals("delete")) {
                char [] Massive = Password.toCharArray();
                short C = (short) Password.length();
                Password = "";
                for (short i = 0; i < C-1; i++)
                    Password = Password + Massive[i];
                    PasswordView.setText(Password);
                if (Click) {
                    Click = false;
                    PasswordView.setTextColor(Color.WHITE);
                }
            }
            else {
                Password += button.getText();
                PasswordView.setText(Password);

            }
    }

    class MyTimerTask extends AsyncTask<Void, Void, Void> {
        boolean inWork = true;
        @Override
        protected void onProgressUpdate(Void... values) {
            inWork = true;
            TimeView.setText(getTime());
        }

        @Override
        protected Void doInBackground(Void... params) {

            while (inWork) {
                publishProgress();
                SystemClock.sleep(500);
            }
            return null;
        }

        private void stop() {
            inWork = false;
        }
    }

    private String getTime() {
        Time = String.valueOf(new SimpleDateFormat("kk:mm:ss").format(new Date(new Date().getTime())));
        char[] chArray = Time.toCharArray();
        if (chArray[0] == '2' && chArray[1] == '4')// функция SimpleDateFormat показывает часы в формате 1-24, здесь производится замена числа 24 на 00
        {
            chArray[0] = '0';
            chArray[1] = '0';
        }
        Time = "" + chArray[0] + chArray[1] + ":" + chArray[3] + chArray[4] + ":" + chArray[6] + chArray[7];
        return Time;
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
