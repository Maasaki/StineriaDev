package com.dev.stineria;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainJava extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        findViewById(R.id.btn_goregister).setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), Register.class)));
        findViewById(R.id.btn_passforgot).setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ForgotPass.class)));
        findViewById(R.id.btn_login).setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), Index.class)));
    }
}