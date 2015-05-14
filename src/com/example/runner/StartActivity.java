package com.example.runner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.parse.Parse;
import com.parse.ParseObject;

public class StartActivity extends Activity implements View.OnClickListener {
    public Button enterBtn;
    Intent loginScreenIntent;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        ParseInitialize();
//        ParseObjectInitialize();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        enterBtn = (Button) findViewById(R.id.enterBtn);
        enterBtn.setOnClickListener(this);
    }

    protected void ParseObjectInitialize() {
        ParseObject testObject = new ParseObject("RunnerObject");
        testObject.put("foo", "test");
        testObject.saveInBackground();
    }

    protected void ParseInitialize() {
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, getString(R.string.appID), getString(R.string.clientKey));
    }

    @Override
    public void onClick(View v) {
        loginScreenIntent = new Intent(this, LoginActivity.class);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        startActivity(loginScreenIntent);

        Toast.makeText(this, getString(R.string.welcomeRunners), Toast.LENGTH_SHORT).show();
    }
}