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
import com.parse.ParseUser;

/**
 * Created by angelr on 08-May-15.
 */
public class LoginActivity extends Activity implements View.OnClickListener {
    public RelativeLayout layout;
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

        Context context = getApplicationContext();
        layout = (RelativeLayout) findViewById(R.id.screenLayout);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        loginBtn = (Button) findViewById(R.id.loginBtn);
        registerBtn = (Button) findViewById(R.id.registerBtn);
        guestBtn = (Button) findViewById(R.id.guestBtn);
        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
        guestBtn.setOnClickListener(this);
        // to hide keyboard
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent ev) {
                Utility.hideKeyboard(view, context);
                return false;
            }
        });
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

            isUserLoggedIn(userName, passWord);

        } else if (v.getId() == R.id.guestBtn){
            homeScreenIntent = new Intent(this, HomeActivity.class);
            Toast.makeText(this, getString(R.string.welcome), Toast.LENGTH_SHORT).show();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            startActivity(homeScreenIntent);
        }
    }

    private void isUserLoggedIn(String userName, String passWord) {
        loginScreenIntent = new Intent(this, LoginActivity.class);
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            if(currentUser.getUsername().equals(userName)){
                Toast.makeText(this, userName + getString(R.string.allreadyLoggedIn), Toast.LENGTH_SHORT).show();
                startActivity(homeScreenIntent);
            } else {
                ParseUser.logOut();
                Toast.makeText(this, userName + getString(R.string.notRegister), Toast.LENGTH_SHORT).show();
                ParseCommon.logInInParse(userName, passWord);
            }
        } else {
            Toast.makeText(this, userName + getString(R.string.successLoggedIn), Toast.LENGTH_SHORT).show();
            ParseCommon.logInInParse(userName, passWord);
        }
    }
}