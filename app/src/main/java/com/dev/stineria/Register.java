package com.dev.stineria;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.MessageFormat;

public class Register extends AppCompatActivity implements IAsyncResponse {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        findViewById(R.id.btn_gologin).setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), MainJava.class)));
        findViewById(R.id.btn_register).setOnClickListener(view -> {
            EditText username = findViewById(R.id.username_register);
            EditText email = findViewById(R.id.email_register);
            EditText phone = findViewById(R.id.phone_register);
            EditText password = findViewById(R.id.pass_register);
            new test(this).execute(username.getText(), email.getText(), phone.getText(), password.getText());

        });
    }

    @Override
    public void processFinish(String output) {
        if(output.equals("OK.")) {
            startActivity(new Intent(getApplicationContext(), MainJava.class));
        }
    }

    public static class test extends AsyncTask<Editable, Void, String> {
        public IAsyncResponse delegate = null;
        private final String base = "http://185.200.34.169/api/v1/";
        private final String page = "register.php";
        private final String args = "?username={0}&email={1}&phone={2}&password={3}";
        private String result = "";

        public test(IAsyncResponse delegate) {
            this.delegate = delegate;
        }

        @Override
        protected String doInBackground(Editable... strings) {
            try {
                URL uri = new URL(base + page + MessageFormat.format(args, strings[0], strings[1], strings[2], strings[3]));
                BufferedReader in = new BufferedReader(new InputStreamReader(uri.openStream()));
                result = in.readLine();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            delegate.processFinish(s);
        }
    }
}
