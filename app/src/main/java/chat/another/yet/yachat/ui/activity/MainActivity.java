package chat.another.yet.yachat.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import chat.another.yet.yachat.R;
import chat.another.yet.yachat.model.User;
import chat.another.yet.yachat.ui.fragment.ChatFragment;
import chat.another.yet.yachat.ui.fragment.FriendsFragment;
import chat.another.yet.yachat.ui.fragment.OnSelectUserListener;
import chat.another.yet.yachat.utils.Helper;

public class MainActivity extends AppCompatActivity implements OnSelectUserListener {

    public static final String USERNAME = "USERNAME";
    private int badId = ThreadLocalRandom.current().nextInt(Helper.COUNT_FRIENDS);
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            userName = savedInstanceState.getString(USERNAME);
        } else {
            final Bundle extras = getIntent().getExtras();
            if (extras != null) {
                userName = extras.getString(USERNAME);
            }
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(userName);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            final FriendsFragment userListFragment = (FriendsFragment) getSupportFragmentManager().findFragmentById(R.id.users_list_fragment);
            userListFragment.setLogin(userName);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(USERNAME, userName);
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

    @VisibleForTesting
    public List<User> getFriendsList() {
        return Helper.getFriendFor(userName);
    }

    @VisibleForTesting
    public boolean isTablet() {
        return getSupportFragmentManager().findFragmentById(R.id.chat_fragment) != null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.exit_menu:
                finish();
                startActivity(new Intent(this, AuthActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
