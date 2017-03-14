package chat.another.yet.yachat.user;

import android.support.annotation.NonNull;

/**
 * Created by timofey.malygin on 14/03/2017.
 */
public class Users {

    public static final String LOGIN = "user1";
    public static final String PASSWORD = "password1";

    public static final String LOGIN2 = "user2";
    public static final String PASSWORD2 = "password2";

    public static boolean isLoginCorrect(@NonNull String login) {
        return LOGIN.equals(login) || LOGIN2.equals(login);
    }

    public static boolean isPasswordCorrect(@NonNull String password) {
        return PASSWORD.equals(password) || PASSWORD2.equals(password);
    }

}
