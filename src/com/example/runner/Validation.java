package com.example.runner;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by angelr on 14-May-15.
 */
public class Validation {
    public static boolean isValidUsername(Context context, String userName) {
        if(2 < userName.length() || userName.length() < 15){
            return true;
        }
        Toast.makeText(context, context.getString(R.string.usernameValidation), Toast.LENGTH_SHORT).show();
        return false;
    }

    public static boolean isValidPassword(Context context, String passWord) {
        if (2 < passWord.length() || passWord.length() < 15){
            return true;
        }
        Toast.makeText(context, context.getString(R.string.passValidation), Toast.LENGTH_SHORT).show();
        return false;
    }

    public static boolean isValidEmail(Context context, String mail) {
        if(mail.indexOf("@") == -1){
            Toast.makeText(context, context.getString(R.string.emailContainsDot), Toast.LENGTH_SHORT).show();
            return false;
        } else if (3 > mail.length() || mail.length() > 20){
            Toast.makeText(context, context.getString(R.string.mailValidation), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}