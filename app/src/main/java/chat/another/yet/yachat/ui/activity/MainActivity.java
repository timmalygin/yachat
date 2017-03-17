package chat.another.yet.yachat.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.concurrent.ThreadLocalRandom;

import chat.another.yet.yachat.R;
import chat.another.yet.yachat.model.User;
import chat.another.yet.yachat.ui.fragment.ChatFragment;
import chat.another.yet.yachat.ui.fragment.OnSelectUserListener;
import chat.another.yet.yachat.ui.fragment.UsersFragment;
import chat.another.yet.yachat.utils.Helper;

public class MainActivity extends AppCompatActivity implements OnSelectUserListener {

    public static final String USERNAME = "USERNAME";
    private int badId = ThreadLocalRandom.current().nextInt(Helper.COUNT_FRIENDS);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Bundle extras = getIntent().getExtras();
        String userName = extras.getString(USERNAME);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(userName);
        setSupportActionBar(toolbar);

        final UsersFragment userListFragment = (UsersFragment) getSupportFragmentManager().findFragmentById(R.id.users_list_fragment);
        userListFragment.setLogin(userName);
    }

    @Override
    public void onSelectUser(@NonNull User user) {
        final ChatFragment chatFragment = (ChatFragment) getSupportFragmentManager().findFragmentById(R.id.chat_fragment);
        if (chatFragment == null || user.id == badId) {
            Intent intent = new Intent(this, ChatActivity.class);
            intent.putExtra(ChatActivity.FRIEND, user);
            startActivity(intent);
        } else {
            chatFragment.setFriend(user);
        }
    }
}
