package com.dev.stineria;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ForgotPass extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_pass);
        findViewById(R.id.btn_gologin).setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), MainJava.class)));
    }
}
