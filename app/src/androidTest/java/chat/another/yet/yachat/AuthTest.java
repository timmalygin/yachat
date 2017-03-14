package chat.another.yet.yachat;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import chat.another.yet.yachat.ui.activity.AuthActivity;
import chat.another.yet.yachat.user.Users;

/**
 * Проверяем авторизацию
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class AuthTest {

    @Rule
    public ActivityTestRule<AuthActivity> authActivityRule = new ActivityTestRule<AuthActivity>(AuthActivity.class);

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
        setTextOn(R.id.login, Users.LOGIN);
        // проверяем что кнопка теперь доступна и нажимаем на нее
        clickOnButton();
        // проверяем вьюху с ошибкой
        checkErrorShown(R.string.err_incorrect_password);

        setTextOn(R.id.password, Users.PASSWORD);
        // проверяем что кнопка теперь доступна и нажимаем на нее
        clickOnButton();

        // проверяем что мы перешли на новую активити
        Espresso.onView(Matchers.allOf(
                ViewMatchers.isAssignableFrom(TextView.class),
                ViewMatchers.withParent(ViewMatchers.isAssignableFrom(Toolbar.class))))
                .check(ViewAssertions.matches(ViewMatchers.withText(Users.LOGIN)));
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
