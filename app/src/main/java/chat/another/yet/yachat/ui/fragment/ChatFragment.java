package chat.another.yet.yachat.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import chat.another.yet.yachat.R;
import chat.another.yet.yachat.model.User;
import chat.another.yet.yachat.ui.adapter.MessageAdapter;

/**
 * Чат с конкретным пользователем
 */
public class ChatFragment extends Fragment {

    private RecyclerView messagesView;
    private User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        messagesView = (RecyclerView) view.findViewById(R.id.messages_list);
        messagesView.setLayoutManager(new LinearLayoutManager(getContext()));
        updateUi();
    }

    public void setFriend(@NonNull User user) {
        this.user = user;
        updateUi();
    }

    private void updateUi() {
        if (messagesView == null || user == null) {
            return;
        }
        messagesView.setAdapter(new MessageAdapter(this.user));
    }
}
