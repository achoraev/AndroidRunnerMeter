package com.example.runner;

import android.content.Context;
import android.widget.Toast;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Created by angelr on 14-May-15.
 */
public class ParseCommon {
    public static void registerOnParse(String userName, String passWord, String eMail, Context context) throws ParseException {
        ParseUser user = new ParseUser();
        user.setUsername(userName);
        user.setPassword(passWord);
        user.setEmail(eMail);

        user.signUp();
//        user.signUpInBackground(new SignUpCallback() {
//            public void done(ParseException e) {
//                if (e == null) {
//                    // Hooray! Let them use the app now.
//                } else {
//                    // Sign up didn't succeed. Look at the ParseException
//                    // to figure out what went wrong
//                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
//                    throw new IllegalArgumentException(e.getMessage());
//                }
//            }
//        });
    }

    public static void logInInParse(String userName, String passWord, final Context context) {
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
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    throw new IllegalArgumentException(e.getMessage());
                }
            }
        });
    }

    public static void ParseInitialize(Context context) {
        Parse.enableLocalDatastore(context);
        Parse.initialize(context, context.getString(R.string.appID), context.getString(R.string.clientKey));
    }
}
