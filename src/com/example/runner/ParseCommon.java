package com.example.runner;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by angelr on 14-May-15.
 */
public class ParseCommon {
    public static void registerOnParse(String userName, String passWord, String eMail) {
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

    public static void logInInParse(String userName, String passWord) {
        ParseUser.logInInBackground(userName, passWord, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
//                    homeScreenIntent.putExtra("username", userName);
//                    homeScreenIntent.putExtra("password", passWord);
////                            Toast.makeText(this, "Welcome " + userName, Toast.LENGTH_SHORT).show();
//                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
//                    startActivity(homeScreenIntent);
                } else {
//                    startActivity(registerScreenIntent);
                    // Signup failed. Look at the ParseException to see what happened.
                }
            }
        });
    }
}
