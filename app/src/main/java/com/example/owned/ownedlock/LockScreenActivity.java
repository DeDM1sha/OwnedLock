package com.example.owned.ownedlock;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import java.util.Date;


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

   // private SettingsResource settingsResource;

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
            if (Password.length() == 4){
                Password = "";
                PasswordView.setTextColor(Color.WHITE);
            }
            Password += button.getText();
            PasswordView.setText(Password);
            if (Password.length() == 4) {
                if (Password.equals(TimeCode)) {
                    Toast.makeText(this, "Пароль верный!", Toast.LENGTH_LONG).show();
                    PasswordView.setTextColor(Color.GREEN);
                } else {
                    Toast.makeText(this, "Неверный пароль!", Toast.LENGTH_LONG).show();
                    PasswordView.setTextColor(Color.RED);
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

    private String getTime() {
//        if (settingsResource.isSwitch_12h())
//            Time = String.valueOf(new SimpleDateFormat("hh:mm:ss").format(new Date(new Date().getTime())));
        //else {
            Time = String.valueOf(new SimpleDateFormat("kk:mm:ss").format(new Date(new Date().getTime())));
            char[] chArray = Time.toCharArray();
            if (chArray[0] == '2' && chArray[1] == '4') {
                chArray[0] = '0';
                chArray[1] = '0';
            } // функция SimpleDateFormat показывает часы в формате 1-24, здесь производится замена числа 24 на 00
            Time = "" + chArray[0] + chArray[1] + ":" + chArray[3] + chArray[4] + ":" + chArray[6] + chArray[7];
       // }
        //char[] chArray = Time.toCharArray();
        TimeCode = "" + chArray[0] + chArray[1] + chArray[3] + chArray[4];
//        if (settingsResource.isSwitch_Reverse()){
//            TimeCode = "" + chArray[4] + chArray[3] + chArray[1] + chArray[0];
//        }
        return Time;
    }

    @Override
    protected void onPause() {
        super.onPause();
        MyTimerTask.stop();
        Intent intent = new Intent();
        intent.setClass(LockScreenActivity.this, LockScreenActivity.class);
        super.startActivity(intent);
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
        Toast toast = Toast.makeText(getApplicationContext(), "Нажата кнопка HOME", Toast.LENGTH_SHORT);
        toast.show();
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
