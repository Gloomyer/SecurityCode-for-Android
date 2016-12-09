package com.gloomyer.securitycode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SecurityCode code = (SecurityCode) findViewById(R.id.code);
        findViewById(R.id.getCode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "code:" + code.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
