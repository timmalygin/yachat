package chat.another.yet.yachat.utils;

import android.graphics.Color;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import chat.another.yet.yachat.R;
import chat.another.yet.yachat.model.Message;
import chat.another.yet.yachat.model.User;

/**
 * Created by timofey.malygin on 14/03/2017.
 */
public class Helper {

    private static final String LOGIN = "user1";
    private static final String PASSWORD = "password1";

    private static final String LOGIN2 = "user2";
    private static final String PASSWORD2 = "password2";
    public static final int COUNT_FRIENDS = 20;
    private static int badId = -1;

    public static boolean isLoginCorrect(@NonNull String login) {
        return LOGIN.equals(login) || LOGIN2.equals(login);
    }

    public static boolean isPasswordCorrect(@NonNull String password) {
        return PASSWORD.equals(password) || PASSWORD2.equals(password);
    }

    private static Map<String, List<User>> friendsMap = new HashMap<>();

    public static List<User> getFriendFor(@NonNull String login) {
        List<User> friends = Helper.friendsMap.get(login);
        if (friends != null) {
            return friends;
        }
        friends = new ArrayList<>();
        int[] icons = new int[]{
                R.drawable.ic_user_1, R.drawable.ic_user_2, R.drawable.ic_user_3,
                R.drawable.ic_user_4, R.drawable.ic_user_5, R.drawable.ic_user_6,
                R.drawable.ic_user_7, R.drawable.ic_user_8, R.drawable.ic_user_9,
                R.drawable.ic_user_10, R.drawable.ic_user_11, R.drawable.ic_user_12
        };

        int[] colors = new int[]{
                Color.parseColor("#6A1B9A"), Color.parseColor("#2196F3"), Color.parseColor("#F44336"),
                Color.parseColor("#009688"), Color.parseColor("#CDDC39"), Color.parseColor("#FF9800"),
                Color.parseColor("#9E9E9E"), Color.parseColor("#33691E"), Color.parseColor("#33691E"),
        };

        String[] names = new String[]{
                "Вася", "Галя", "Мстислав Петрович", "Иван", "Петр", "Владимир", "Дмитрий", "Саша",
                "Даниил", "Николай", "СамсунгСамсунгович", "Джигурда", "Картошка"
        };

        String[] surname = new String[]{
                " Пупкин", " Недалекович", " II", " IV", " I", " Путин", " Мстиславич", " Грей",
                " Велокович", " Чудотворец", " Бээ", " Нормал", " фри", "III"
        };

        for (int i = 0; i < COUNT_FRIENDS; i++) {
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
            friends.add(user);
        }
        friendsMap.put(login, friends);
        return friends;
    }

    private static Map<User, List<Message>> messagesMap = new HashMap<>();

    public static List<Message> getMessageFor(@NonNull User companion) {
        Random random = ThreadLocalRandom.current();
        if (badId == -1) {
            badId = random.nextInt(COUNT_FRIENDS);
        }
        List<Message> messages = Helper.messagesMap.get(companion);
        if (companion.id != badId && messages != null) {
            return messages;
        }

        messages = new ArrayList<>();
        String[] simpleQuestions = new String[]{
                "Привет", "Здарово!", "АЧоДелаешь?", "Как твое ничо?", "Пойдешь на лекцию?", "Да",
                "Норм", "Как семья?", "Ничего", "Горы, только горы", "как отпуск?", "Винипух",
                "Погуляем?", "Не могу", "У меня пельмени стынут"
        };
        final int count = random.nextInt(40) + 10; // (50; 10]
        for (int i = 0; i < count; i++) {
            int indexOfText = random.nextInt(simpleQuestions.length);
            Message message = new Message(companion, random.nextBoolean(), simpleQuestions[indexOfText]);
            messages.add(message);
        }
        messagesMap.put(companion, messages);
        return messages;
    }

}
