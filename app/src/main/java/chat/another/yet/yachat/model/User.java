package chat.another.yet.yachat.user;

import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

/**
 * Created by timofey.malygin on 16/03/2017.
 */
public class User {
    public final int id;

    @NonNull
    public String name;

    @DrawableRes
    public int icon;

    @ColorInt
    public final int color;

    public User(int id, @NonNull String name, @DrawableRes int icon, @ColorInt int color) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.color = color;
    }
}
