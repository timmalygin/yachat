package chat.another.yet.yachat.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

/**
 * Created by timofey.malygin on 16/03/2017.
 */
public class User implements Parcelable {
    public final int id;

    @NonNull
    public final String name;

    @DrawableRes
    public final int icon;

    @ColorInt
    public final int color;

    public User(int id, @NonNull String name, @DrawableRes int icon, @ColorInt int color) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.color = color;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.icon);
        dest.writeInt(this.color);
    }

    protected User(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.icon = in.readInt();
        this.color = in.readInt();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
