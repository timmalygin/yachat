package chat.another.yet.yachat.ui.fragment;

import android.support.annotation.NonNull;

import chat.another.yet.yachat.model.User;

/**
 * Created by timofey.malygin on 17/03/2017.
 */
public interface OnSelectUserListener {
    void onSelectUser(@NonNull User user);
}
