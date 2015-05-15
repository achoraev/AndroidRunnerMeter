package com.example.runner;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by angelr on 14-May-15.
 */
public class Validation {
    public static boolean isValidUsername(Context context, String userName) {
        if(userName.length() > 2 && userName.length() < 15){
            return true;
        }
        Toast.makeText(context, context.getString(R.string.usernameValidation), Toast.LENGTH_SHORT).show();
        return false;
    }

    public static boolean isValidPassword(Context context, String passWord) {
        if (passWord.length() > 2 && passWord.length() < 15){
            return true;
        }
        Toast.makeText(context, context.getString(R.string.passValidation), Toast.LENGTH_SHORT).show();
        return false;
    }

    public static boolean isValidEmail(Context context, String mail) {
        if(!mail.contains("@") || !mail.contains(".")){
            Toast.makeText(context, context.getString(R.string.invalidMail), Toast.LENGTH_SHORT).show();
        } else if (mail.length() < 2 || mail.length() > 20){
            Toast.makeText(context, context.getString(R.string.mailValidation), Toast.LENGTH_SHORT).show();
        }

        if(mail.contains("@") && mail.contains(".") && (mail.length() > 2 && mail.length() < 20)){
            return true;
        }
        return false;
    }

    public static boolean isConfirmedPassword(Context context, String passWord, String confirmPassword) {
        if(passWord.equals(confirmPassword)){
            return true;
        }
        Toast.makeText(context, context.getString(R.string.notSamePass), Toast.LENGTH_SHORT).show();
        return false;
    }
}