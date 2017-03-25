package chat.another.yet.yachat;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.matcher.IntentMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import chat.another.yet.yachat.ui.activity.AuthActivity;
import chat.another.yet.yachat.ui.activity.MainActivity;

/**
 * Проверяем авторизацию
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class AuthTest {

    public static final String LOGIN = "user1";
    public static final String PASSWORD = "password1";
    @Rule
    public ActivityTestRule<AuthActivity> authActivityRule =
            new ActivityTestRule<>(AuthActivity.class);

    @Before
    public void init() {
        Intents.init();
    }

    @After
    public void restore() {
        Intents.release();
    }

    /**
     * Тест проверяет ввод некорретных данных, ввод корректного логина и некорректного пароля,
     * и ввод корректных данных
     */
    @Test
    public void testAuth() {
        // проверяем что кнопка входа не доступна
        checkButtonDisabled();
        // проверяем что вьюха с ошибкой скрыта
        Espresso.onView(ViewMatchers.withId(R.id.error)).
                check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())));

        // введем неправильный логин
        setTextOn(R.id.login, "incorrectLogin");
        // проверем что кнопка кнопка все еще недоступна
        checkButtonDisabled();
        // установим неправильный пароль
        setTextOn(R.id.password, "incorrectPassword");
        // проверяем что кнопка теперь доступна и нажимаем на нее
        clickOnButton();
        // проверяем вьюху с ошибкой
        checkErrorShown(R.string.err_incorrect_login);
        // введем правильный логин
        setTextOn(R.id.login, LOGIN);
        // проверяем что кнопка теперь доступна и нажимаем на нее
        clickOnButton();
        // проверяем вьюху с ошибкой
        checkErrorShown(R.string.err_incorrect_password);

        setTextOn(R.id.password, PASSWORD);
        // проверяем что кнопка теперь доступна и нажимаем на нее
        clickOnButton();
        // проверяем что мы перешли на новую активити
        Intents.intended(IntentMatchers.hasComponent(MainActivity.class.getName()));

        Espresso.openActionBarOverflowOrOptionsMenu(authActivityRule.getActivity());

        Espresso.onView(ViewMatchers.withText(R.string.exit))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .perform(ViewActions.click());
        Intents.intended(IntentMatchers.hasComponent(AuthActivity.class.getName()));
    }

    private void checkErrorShown(@StringRes int strId) {
        Espresso.onView(ViewMatchers.withId(R.id.error)).
                check(ViewAssertions.matches(
                        Matchers.allOf(
                                ViewMatchers.withText(strId), // проверяем что текст ошибки правильный
                                ViewMatchers.isDisplayed()) // проверяем что ошибка отобразилась
                        )
                );
    }

    private void clickOnButton() {
        Espresso.onView(ViewMatchers.withId(R.id.enter)).
                check(ViewAssertions.matches(ViewMatchers.isEnabled()))
                .perform(ViewActions.click());// нажатие на кнопку
    }

    private void checkButtonDisabled() {
        Espresso.onView(ViewMatchers.withId(R.id.enter)).
                check(ViewAssertions.matches(Matchers.not(ViewMatchers.isEnabled())));
    }

    private void setTextOn(@IdRes int id, @NonNull String str) {
        Espresso.onView(ViewMatchers.withId(id))
                .perform(ViewActions.replaceText(str));
    }
}
