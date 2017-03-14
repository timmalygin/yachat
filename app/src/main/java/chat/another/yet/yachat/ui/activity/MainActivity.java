package chat.another.yet.yachat.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import chat.another.yet.yachat.R;

public class MainActivity extends AppCompatActivity {

    public static final String USERNAME = "USERNAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Bundle extras = getIntent().getExtras();
        String userName = extras.getString(USERNAME);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(userName);
        setSupportActionBar(toolbar);
    }

}
