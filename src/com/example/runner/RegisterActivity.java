package com.example.runner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by angelr on 08-May-15.
 */
public class RegisterActivity extends Activity implements View.OnClickListener {
    public RelativeLayout layout;
    public EditText username, password, confirmPass, emailField;
    public Button registerBtn;
    public String userName;
    public String passWord;
    public String eMail;
    Intent homeScreenIntent;
    Intent registerScreenIntent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        layout = (RelativeLayout) findViewById(R.id.screenLayout);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        confirmPass = (EditText) findViewById(R.id.confirmPass);
        emailField = (EditText) findViewById(R.id.emailInput);

        registerBtn = (Button) findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(this);
        layout.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent ev)
            {
                hideKeyboard(view);
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        try {
            userName = username.getText().toString();
            passWord = password.getText().toString();
            eMail = emailField.getText().toString();

            if (isValidUsername(userName) && isValidPassword(passWord) && isValidEmail(eMail)) {
                registerOnParse(userName, passWord, eMail);
                homeScreenIntent = new Intent(this, LoginActivity.class);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                Toast.makeText(this, (getString(R.string.successfullyRegistered, userName)), Toast.LENGTH_SHORT).show();
                startActivity(homeScreenIntent);
            } else {
                Toast.makeText(this, "You are not registered!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, userName + " not registered" + e.getMessage(), Toast.LENGTH_SHORT).show();
            registerScreenIntent = new Intent(this, RegisterActivity.class);
            startActivity(registerScreenIntent);
        }
    }

    // hide keyboard
    protected void hideKeyboard(View view)
    {
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
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

    private boolean isValidUsername(String userName) {
        if(2 > userName.length() || userName.length() > 15){
            Toast.makeText(this, getString(R.string.usernameValidation), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String passWord) {
        if (2 > passWord.length() || passWord.length() > 15){
            Toast.makeText(this, getString(R.string.passValidation), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean isValidEmail(String mail) {
        if(mail.indexOf("@") == -1){
            Toast.makeText(this, getString(R.string.emailContainsDot), Toast.LENGTH_SHORT).show();
            return false;
        } else if (3 > mail.length() || mail.length() > 20){
            Toast.makeText(this, getString(R.string.mailValidation), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}