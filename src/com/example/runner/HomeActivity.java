package com.example.runner;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.parse.ParseUser;

/**
 * Created by angelr on 08-May-15.
 */
public class HomeActivity extends Activity {
    public TextView text;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        text = (TextView)findViewById(R.id.textView);
        text.setText(getIntent().getStringExtra("username"));
    }

    @Override
    public void onBackPressed() {
        ParseUser.logOut();
    }
}