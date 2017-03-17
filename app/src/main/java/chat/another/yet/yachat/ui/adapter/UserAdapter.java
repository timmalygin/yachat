package chat.another.yet.yachat.ui.adapter;

import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import chat.another.yet.yachat.R;
import chat.another.yet.yachat.model.User;
import chat.another.yet.yachat.ui.fragment.OnSelectUserListener;
import chat.another.yet.yachat.utils.Helper;

/**
 * Created by timofey.malygin on 16/03/2017.
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    private final List<User> users;
    @NonNull
    private final OnSelectUserListener listener;

    public UserAdapter(@NonNull String login, @NonNull OnSelectUserListener listener) {
        users = Helper.getFriendFor(login);
        this.listener = listener;
    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new UserHolder(inflater.inflate(R.layout.li_user, parent, false), listener);
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {
        holder.bind(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class UserHolder extends RecyclerView.ViewHolder {

        final AppCompatImageView iconView;
        final TextView nameView;
        final OnSelectUserListener listener;

        UserHolder(View itemView, OnSelectUserListener listener) {
            super(itemView);
            iconView = (AppCompatImageView) itemView.findViewById(R.id.user_icon);
            nameView = (TextView) itemView.findViewById(R.id.user_name);
            this.listener = listener;
        }

        void bind(@NonNull final User user) {
            iconView.setImageResource(user.icon);
            nameView.setText(user.name);
            iconView.setImageTintList(ColorStateList.valueOf(user.color));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSelectUser(user);
                }
            });
        }
    }
}
