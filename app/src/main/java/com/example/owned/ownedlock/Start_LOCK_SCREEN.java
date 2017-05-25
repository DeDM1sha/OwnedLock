package com.example.owned.ownedlock;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Start_LOCK_SCREEN extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        Intent intent = new Intent();
        intent.setClass(Start_LOCK_SCREEN.this, LockScreenService.class);
        startService(intent);
        Toast.makeText(Start_LOCK_SCREEN.this, "GO!", Toast.LENGTH_SHORT).show();
        finish();

    }
}
