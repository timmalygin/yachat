package chat.another.yet.yachat.user;

import android.graphics.Color;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chat.another.yet.yachat.R;

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

    public static List<User> getUsers() {
        List<User> users = new ArrayList<>();
        int[] icons = new int[]{
                R.drawable.ic_user_1, R.drawable.ic_user_2, R.drawable.ic_user_3,
                R.drawable.ic_user_4, R.drawable.ic_user_5, R.drawable.ic_user_6,
                R.drawable.ic_user_7
        };

        int[] colors = new int[]{
                Color.parseColor("#6A1B9A"), Color.parseColor("#2196F3"),Color.parseColor("#F44336"),
                Color.parseColor("#009688"), Color.parseColor("#CDDC39"),Color.parseColor("#FF9800"),
                Color.parseColor("#9E9E9E"), Color.parseColor("#33691E"),Color.parseColor("#33691E"),
        };

        String[] names = new String[]{
                "Вася", "Галя", "Мстислав Петрович", "Иван", "Петр", "Владимир", "Дмитрий", "Саша",
                "Даниил", "Николай", "СамсунгСамсунгович", "Джигурда", "Картошка"
        };

        String[] surname = new String[]{
                " Пупкин", " Недалекович", " II", " IV", " I", " Путин", " Мстиславич", " Грей",
                " Велокович", " Чудотворец", " Бээ", " Нормал", " фри"
        };

        for (int i = 0; i < 10_000; i++) {
            int indexIcon = i % icons.length;
            if (indexIcon == 0) {
                Arrays.sort(icons);
            }
            int indexName = i % names.length;
            if (indexName == 0) {
                Arrays.sort(names);
            }
            int indexSurname = i % surname.length;
            if (indexSurname == 0) {
                Arrays.sort(surname);
            }
            int indexColor = i % colors.length;
            if (indexColor == 0) {
                Arrays.sort(colors);
            }

            User user = new User(i, names[indexName] + surname[indexSurname], icons[indexIcon], colors[indexColor]);
            users.add(user);
        }
        return users;
    }

}
