package com.example.runner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Created by angelr on 08-May-15.
 */
public class LoginActivity extends Activity implements View.OnClickListener {
    public EditText username, password;
    public Button loginBtn, registerBtn, guestBtn;
    public String userName;
    public String passWord;
    Intent registerScreenIntent;
    Intent homeScreenIntent;
    Intent loginScreenIntent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        loginBtn = (Button) findViewById(R.id.loginBtn);
        registerBtn = (Button) findViewById(R.id.registerBtn);
        guestBtn = (Button) findViewById(R.id.guestBtn);
        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
        guestBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.registerBtn) {
            registerScreenIntent = new Intent(this, RegisterActivity.class);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            startActivity(registerScreenIntent);
        } else if (v.getId() == R.id.loginBtn){
            homeScreenIntent = new Intent(this, HomeActivity.class);
            userName = username.getText().toString();
            passWord = password.getText().toString();

            isUserLoggedIn(userName);

        } else if (v.getId() == R.id.guestBtn){
            homeScreenIntent = new Intent(this, HomeActivity.class);
            Toast.makeText(this, "Welcome guest", Toast.LENGTH_SHORT).show();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            startActivity(homeScreenIntent);
        }
    }

    private void isUserLoggedIn(String userName) {
        loginScreenIntent = new Intent(this, LoginActivity.class);
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            if(currentUser.getUsername().equals(userName)){
                Toast.makeText(this, userName + " already logged in", Toast.LENGTH_SHORT).show();
                startActivity(homeScreenIntent);
            } else {
                ParseUser.logOut();
                logInInParse();
                Toast.makeText(this, userName + " is not registered", Toast.LENGTH_SHORT).show();
            }
        } else {
            logInInParse();
            Toast.makeText(this, userName + " is successfully logged in", Toast.LENGTH_SHORT).show();
        }
    }

    private void logInInParse() {
        ParseUser.logInInBackground(userName, passWord, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
//                    homeScreenIntent.putExtra("username", userName);
//                    homeScreenIntent.putExtra("password", passWord);
////                            Toast.makeText(this, "Welcome " + userName, Toast.LENGTH_SHORT).show();
//                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    startActivity(homeScreenIntent);
                } else {
                    startActivity(registerScreenIntent);
                    // Signup failed. Look at the ParseException to see what happened.
                }
            }
        });
    }
}