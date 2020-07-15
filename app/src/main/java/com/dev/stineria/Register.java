package com.dev.stineria;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Register extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        findViewById(R.id.btn_gologin).setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), MainJava.class)));
    }
}
