package chat.another.yet.yachat;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.matcher.IntentMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import chat.another.yet.yachat.model.User;
import chat.another.yet.yachat.ui.activity.MainActivity;
import chat.another.yet.yachat.ui.adapter.FriendsAdapter;

/**
 * Проверяем авторизацию
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class SomeTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule =
            new ActivityTestRule<>(MainActivity.class, true, false);

    private List<User> friends;
    private MainActivity activity;

    @Before
    public void init() {
        Intents.init();
        Intent intent = new Intent();
        intent.putExtra(MainActivity.USERNAME, "user1");
        mainActivityRule.launchActivity(intent);
        activity = mainActivityRule.getActivity();
        // получаем список друзей для текущего пользователя
        friends = mainActivityRule.getActivity().getFriendsList();
    }

    @After
    public void restore() {
        Intents.release();
    }

    @Test
    public void enterToFriendChat() {
        // проверяем что список с друзьями виден
        Espresso.onView(ViewMatchers.withId(R.id.users_list_fragment))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        // проходимся по списку друзей
        for (User friend : friends) {
            // скроллим до нужного пользователя, чтобы он был виден на экране
            Espresso.onView(ViewMatchers.withId(R.id.friends_list))
                    .perform(RecyclerViewActions.scrollToHolder(findHolderWithText(friend)));
            // нажимаем на него
            Espresso.onView(ViewMatchers.withTagValue(Matchers.<Object>is(Integer.valueOf(friend.id))))
                    .perform(ViewActions.click());

            if (!activity.isTablet()) {
                // если это телефон то проверяем что мы перешли на следующую форму (Activity)
                Intents.intended(IntentMatchers.hasComponent(MainActivity.class.getName()));
                Espresso.pressBack();
            }
        }
    }

    /**
     * Matches the {@link CustomAdapter.ViewHolder}s in the middle of the list.
     */
    private static Matcher<FriendsAdapter.FriendHolder> findHolderWithText(final User friend) {
        return new TypeSafeMatcher<FriendsAdapter.FriendHolder>() {
            @Override
            public void describeTo(Description description) {
                description.appendText(friend.id + " name " + friend.name);
            }

            @Override
            protected boolean matchesSafely(FriendsAdapter.FriendHolder holder) {
                return holder.is(friend);
            }

        };
    }
}
