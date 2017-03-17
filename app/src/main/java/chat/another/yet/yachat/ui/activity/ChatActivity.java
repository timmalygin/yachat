package chat.another.yet.yachat.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import chat.another.yet.yachat.R;
import chat.another.yet.yachat.model.User;
import chat.another.yet.yachat.ui.fragment.ChatFragment;

public class ChatActivity extends AppCompatActivity {

    public static final String FRIEND = "FRIEND";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        User friend = getIntent().getExtras().getParcelable(FRIEND);
        ChatFragment chatFragment = (ChatFragment) getSupportFragmentManager().findFragmentById(R.id.chat_fragment);
        chatFragment.setFriend(friend);
    }

}
