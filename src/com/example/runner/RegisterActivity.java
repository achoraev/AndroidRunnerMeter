package com.example.runner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.text.MessageFormat;

import static com.example.runner.Validation.*;

/**
 * Created by angelr on 08-May-15.
 */
public class RegisterActivity extends Activity implements View.OnClickListener {
    public RelativeLayout layout;
    public EditText username, password, confirmPass, emailField;
    public Button registerBtn;
    public String userName;
    public String passWord;
    public String confirmPassword;
    public String eMail;
    Intent homeScreenIntent;
    Intent registerScreenIntent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        Context context = getApplicationContext();
        layout = (RelativeLayout) findViewById(R.id.screenLayout);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        confirmPass = (EditText) findViewById(R.id.confirmPass);
        emailField = (EditText) findViewById(R.id.emailInput);

        registerBtn = (Button) findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(this);
        // to hide keyboard
        layout.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent ev) {
                Utility.hideKeyboard(view, context);
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        try {
            userName = username.getText().toString();
            passWord = password.getText().toString();
            confirmPassword = confirmPass.getText().toString();
            eMail = emailField.getText().toString();

            if (isValidUsername(this, userName) && isValidPassword(this, passWord) &&
                    isValidEmail(this, eMail) && passWord.equals(confirmPassword)) {
                registerOnParse(userName, passWord, eMail);
                homeScreenIntent = new Intent(this, LoginActivity.class);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                Toast.makeText(this, MessageFormat.format("{0} {1}", userName, getString(R.string.successfullyRegistered)), Toast.LENGTH_SHORT).show();
                startActivity(homeScreenIntent);
            } else {
                Toast.makeText(this, getString(R.string.notRegistered), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, userName + " not registered" + e.getMessage(), Toast.LENGTH_SHORT).show();
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
}