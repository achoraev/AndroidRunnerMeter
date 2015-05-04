package com.example.runner;

import android.app.Activity;
import android.os.Bundle;
import com.parse.Parse;
import com.parse.ParseObject;

public class StartActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        ParseInitialize();
        ParseObjectInitialize();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    private void ParseObjectInitialize() {
        ParseObject testObject = new ParseObject("RunnerObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
    }

    private void ParseInitialize() {
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "qRfCcjHNsMi4Q2uDBma1npkkHW8Uoj7hiZqDLbHN", "aYeD0yfzCYfKtKQLxs1I6aMwODsR3iB5pfvyE8mx");
    }
}
