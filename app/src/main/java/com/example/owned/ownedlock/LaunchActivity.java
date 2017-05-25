package com.example.owned.ownedlock;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class LaunchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        Button btn_start = (Button) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * start lock screen service
                 */
                Intent intent = new Intent();
                intent.setClass(LaunchActivity.this, LockScreenService.class);
                startService(intent);
                Toast.makeText(LaunchActivity.this, "GO", Toast.LENGTH_SHORT).show();

                finish();
            }
        });
    }
}
