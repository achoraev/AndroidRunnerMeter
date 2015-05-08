package com.example.runner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import com.parse.ParseException;

/**
 * Created by angelr on 08-May-15.
 */
public class RegisterActivity extends Activity implements View.OnClickListener {
    public EditText username, password, confirmPass, emailField;
    public Button registerBtn;
    public String userName;
    public String passWord;
    public String eMail;
    Intent loginScreenIntent;
    Intent registerScreenIntent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        confirmPass = (EditText) findViewById(R.id.confirmPass);
        emailField = (EditText) findViewById(R.id.emailInput);

        registerBtn = (Button) findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        try {
            userName = username.getText().toString();
            passWord = password.getText().toString();
            eMail = emailField.getText().toString();

            if (isValidInput(userName, passWord)) {
                registerOnParse(userName, passWord, eMail);
                loginScreenIntent = new Intent(this, LoginActivity.class);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                startActivity(loginScreenIntent);
                Toast.makeText(this, userName + " is successfully registered!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, userName + " not registered", Toast.LENGTH_SHORT).show();
            registerScreenIntent = new Intent(this, RegisterActivity.class);
            startActivity(registerScreenIntent);
        }
    }

    private void registerOnParse(String userName, String passWord, String eMail) {
        ParseUser user = new ParseUser();
        user.setUsername(userName);
        user.setPassword(passWord);
        user.setEmail(eMail);

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                }
            }
        });
    }

    private boolean isValidInput(String userName, String passWord) {
        if(2 > userName.length() || userName.length() > 15){
            Toast.makeText(this, "Username must be between 2 and 15 symbols!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (2 > passWord.length() || passWord.length() > 15){
            Toast.makeText(this, "Username must be between 2 and 15 symbols!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}