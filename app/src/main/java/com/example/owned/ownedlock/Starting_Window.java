package com.example.owned.ownedlock;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Starting_Window extends Activity{

    private EditText Password_Field;
    private TextView Text_WritePassword;
    private TextView Information_About_Password;
    private Button Enter;

    private final String SAVED_PASSWORD = "PASSWORD";
    private String UserPassword;

    SharedPreferences sharedPreferences;

    private Starting_Window.MyTimerTask MyTimerTask = new Starting_Window.MyTimerTask();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_window);

        Password_Field = (EditText) findViewById(R.id.Password_Field);
        Text_WritePassword = (TextView) findViewById(R.id.Text_WritePassword);
        Information_About_Password = (TextView) findViewById(R.id.Information_About_Password);
        Enter = (Button) findViewById(R.id.Enter);

        sharedPreferences = getPreferences(MODE_PRIVATE);
        UserPassword = sharedPreferences.getString(SAVED_PASSWORD, "");

            if (UserPassword.length() == 0) {
                Information_About_Password.setText(R.string.information_about_password);
                Enter.setVisibility(1);
            }
            else
                Information_About_Password.setText("Введите пароль для входа");
    }

    class MyTimerTask extends AsyncTask<Void, Void, Void> {
        boolean inWork = true;

        @Override
        protected void onProgressUpdate(Void... values) {
            inWork = true;
                if (UserPassword.length() == 0) {
                    Enter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (Password_Field.getText().toString().length() < 4)
                                Toast.makeText(getApplicationContext(), "Пароль должен содержать более 3-ех цифр", Toast.LENGTH_SHORT).show();
                            else {
                                sharedPreferences = getPreferences(MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString(SAVED_PASSWORD, Password_Field.getText().toString());
                                editor.commit();
                                Toast.makeText(getApplicationContext(), "Пароль успешно сохранен", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Starting_Window.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
                }
                else if (Password_Field.getText().toString().equals(UserPassword)){
                    Intent intent = new Intent(Starting_Window.this, MainActivity.class);
                    startActivity(intent);
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
        MyTimerTask = new Starting_Window.MyTimerTask();
        MyTimerTask.execute();
    }

    @Override // Вопрос пользователю о его желании выйти из приложения
    public void onBackPressed() {
        openQuitDialog();
    }
    private void openQuitDialog() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(
                Starting_Window.this);
        quitDialog.setTitle("Выйти из приложения?");

        quitDialog.setPositiveButton("Да!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        quitDialog.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        quitDialog.show();
    }
}
