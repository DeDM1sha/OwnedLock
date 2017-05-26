package com.example.owned.ownedlock;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;


/**
 * if the screen is locked, this Activity will show.
 *
 * @author Andy
 */
public class LockScreenActivity extends Activity implements View.OnClickListener{

    public static boolean isLocked = false;
    private TextView TimeView;
    private TextView PasswordView;
    private Button[] buttons = new Button[10];
    private Button DeleteButton;

    private String Password = "";
    private String Time = "";
    private String TimeCode = "";
    private boolean Clicked = false;

    private final String FORMAT = "FORMAT";
    private final String REVERSE = "REVERSE";
    private final String HOURS = "HOURS";
    private final String MINUTES = "MINUTES";
    private final String SECONDS = "SECONDS";
    private final String ALTERNATE_PASSWORD = "ALNERNATE_PASSWORD";
    private String Format;
    private String Reverse;
    private String Hours;
    private String Minutes;
    private String Seconds;
    private String AlternatePassword;

    SharedPreferences sharedPreferences;

    private LockScreenActivity.MyTimerTask MyTimerTask = new LockScreenActivity.MyTimerTask();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);

        setDefaultKeyMode(DEFAULT_KEYS_DISABLE);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_lock_screen);


        sharedPreferences = getSharedPreferences(FORMAT, Context.MODE_PRIVATE);
        Format = sharedPreferences.getString(FORMAT, "");

        sharedPreferences = getSharedPreferences(REVERSE, Context.MODE_PRIVATE);
        Reverse =  sharedPreferences.getString(REVERSE, "");

        sharedPreferences = getSharedPreferences(HOURS, Context.MODE_PRIVATE);
        Hours = sharedPreferences.getString(HOURS, "");

        sharedPreferences = getSharedPreferences(MINUTES, Context.MODE_PRIVATE);
        Minutes = sharedPreferences.getString(MINUTES, "");

        sharedPreferences = getSharedPreferences(SECONDS, Context.MODE_PRIVATE);
        Seconds = sharedPreferences.getString(SECONDS, "");

        sharedPreferences = getSharedPreferences(ALTERNATE_PASSWORD, Context.MODE_PRIVATE);
        AlternatePassword = sharedPreferences.getString(ALTERNATE_PASSWORD, "");

        isLocked = true;

        TimeView = (TextView) findViewById(R.id.TimeView);
        PasswordView = (TextView) findViewById(R.id.PasswordView);
        buttons[0] = (Button) findViewById(R.id.Button_0);
        buttons[1] = (Button) findViewById(R.id.Button_1);
        buttons[2] = (Button) findViewById(R.id.Button_2);
        buttons[3] = (Button) findViewById(R.id.Button_3);
        buttons[4] = (Button) findViewById(R.id.Button_4);
        buttons[5] = (Button) findViewById(R.id.Button_5);
        buttons[6] = (Button) findViewById(R.id.Button_6);
        buttons[7] = (Button) findViewById(R.id.Button_7);
        buttons[8] = (Button) findViewById(R.id.Button_8);
        buttons[9] = (Button) findViewById(R.id.Button_9);
        DeleteButton = (Button) findViewById(R.id.Button_Delete);
        for (short i = 0; i < 10; i++)
            buttons[i].setOnClickListener(this);
        DeleteButton.setOnClickListener(this);
        TimeView.setText(getTime());

        try {
            startService(new Intent(this, LockScreenService.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        if (button.getText().equals("delete")) {
            if (Password.length() == 4)
                PasswordView.setTextColor(Color.WHITE);
            char [] Massive = Password.toCharArray();
            short C = (short) Password.length();
            Password = "";
            for (short i = 0; i < C-1; i++)
                Password = Password + Massive[i];
            PasswordView.setText(Password);
        }
        else {
            if (Seconds.equals("ON")) {
                if (Password.length() == 6) {
                    Password = "";
                    PasswordView.setTextColor(Color.WHITE);
                }
            }
            else {
                if (Password.length() == 4) {
                    Password = "";
                    PasswordView.setTextColor(Color.WHITE);
                }
            }
            Password += button.getText();
            PasswordView.setText(Password);
            if (Seconds.equals("ON")) {
                    if (Password.length() == 6) {
                        if (Password.equals(TimeCode) || (AlternatePassword.length() > 3 && AlternatePassword.equals(Password))) {
                            virbate();
                            isLocked = false;
                            finish();
                        } else {
                            Toast.makeText(this, "Неверный пароль!", Toast.LENGTH_LONG).show();
                            PasswordView.setTextColor(Color.RED);
                        }
                    }
            }
            else {
                if (Password.length() == 4) {
                    if (Password.equals(TimeCode) || (AlternatePassword.length() > 3 && AlternatePassword.equals(Password))) {
                        virbate();
                        isLocked = false;
                        finish();
                    } else {
                        Toast.makeText(this, "Неверный пароль!", Toast.LENGTH_LONG).show();
                        PasswordView.setTextColor(Color.RED);
                    }
                }
            }
        }
        Clicked = true;
    }

    class MyTimerTask extends AsyncTask<Void, Void, Void> {
        boolean inWork = true;

        @Override
        protected void onProgressUpdate(Void... values) {
            inWork = true;
            TimeView.setText(getTime());
            if (Clicked) {
                PasswordView.setText(Password);
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
boolean a = false;
    private String getTime() {
            if (Format.equals("12h"))
                Time = String.valueOf(new SimpleDateFormat("hh:mm:ss").format(new java.util.Date(new java.util.Date().getTime())));
            else
                Time = String.valueOf(new SimpleDateFormat("kk:mm:ss").format(new java.util.Date(new java.util.Date().getTime())));
        char[] chArray = Time.toCharArray();
                if (chArray[0] == '2' && chArray[1] == '4') {
                    chArray[0] = '0';
                    chArray[1] = '0';
                } // функция SimpleDateFormat показывает часы в формате 1-24, здесь производится замена числа 24 на 00
//                if (Hours.equals("ON")) {
//                    if (chArray[1] == '9') {
//                        chArray[0] = '1';
//                        chArray[1] = '0';
//                    }
//                    if (chArray[0] == '1') {
//                        chArray[0] = '1';
//                        chArray[1] = '1';
//                    }
//                    if (chArray[1] == '1') {
//                        chArray[0] = '1';
//                        chArray[1] = '2';
//                    }
//                    if (chArray[1] == '8')
//                        chArray[1] =  '9';
//                    if (chArray[1] == '7')
//                        chArray[1] = '8';
//                    if (chArray[1] == '6')
//                        chArray[1] = '7';
//                    if (chArray[1] == '5')
//                        chArray[1] = '6';
//                    if (chArray[1] == '4')
//                        chArray[0] = '3';
//
//                    if (chArray[1] == '3')
//                        chArray[1] = '4';
//                    if (chArray[1] == '2')
//                        chArray[1] = '3';
//                    if (chArray[1] == '1')
//                        chArray[1] = '2';
//                }
                if (Reverse.equals("ON"))
                    TimeCode = "" + chArray[4] + chArray[3] + chArray[1] + chArray[0];
                 else
                    TimeCode = "" + chArray[0] + chArray[1] + chArray[3] + chArray[4];
                if (Seconds.equals("ON")) {
                    TimeCode = "" + chArray[0] + chArray[1] + chArray[3] + chArray[4] + chArray[6] + chArray[7];
                        if (Reverse.equals("ON"))
                            TimeCode = "" + chArray[7] + chArray[6] + chArray[4] + chArray[3] + chArray[1] + chArray[0];
                }

        Time = "" + chArray[0] + chArray[1] + ":" + chArray[3] + chArray[4] + ":" + chArray[6] + chArray[7];
        return Time;
    }

    @Override
    protected void onPause() {
        super.onPause();
        MyTimerTask.stop();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {

        if ((keyCode == KeyEvent.KEYCODE_HOME)) {
            // Key code constant: Home key. This key is handled by the framework and is never delivered to applications.
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onUserLeaveHint() {
        Intent intent = new Intent();
        intent.setClass(LockScreenActivity.this, LockScreenActivity.class);
        super.startActivity(intent);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    public void onBackPressed() {
//return;
}


    @Override
    public void onResume() {
        super.onResume();
        MyTimerTask = new LockScreenActivity.MyTimerTask();
        MyTimerTask.execute();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * virbate means that the screen is unlocked success
     */
    private void virbate() {
        Vibrator vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }
}
