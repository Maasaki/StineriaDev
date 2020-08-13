package com.dev.stineria;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.MessageFormat;

public class MainJava extends AppCompatActivity implements IAsyncResponse {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        findViewById(R.id.btn_goregister).setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), Register.class)));
        findViewById(R.id.btn_passforgot).setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), ForgotPass.class)));
        findViewById(R.id.btn_login).setOnClickListener(view -> {
            EditText username = findViewById(R.id.et_username_login);
            EditText password = findViewById(R.id.password);
            new test(this).execute(username.getText(), password.getText());
        });
    }

    @Override
    public void processFinish(String output) {
        if (output == null) {
            Toast.makeText(this, "Login error", Toast.LENGTH_LONG).show();
        } else if (output.equals("OK.")) {
            startActivity(new Intent(getApplicationContext(), Index.class));
        }
    }

    public static class test extends AsyncTask<Editable, Void, String> {
        public IAsyncResponse delegate = null;
        private final String base = "http://185.200.34.169/api/v1/";
        private final String page = "login.php";
        private final String args = "?username={0}&password={1}";
        private String result = "";

        public test(IAsyncResponse delegate) {
            this.delegate = delegate;
        }

        @Override
        protected String doInBackground(Editable... strings) {
            try {
                URL uri = new URL(base + page + MessageFormat.format(args, strings[0], strings[1]));
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