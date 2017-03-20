package chat.another.yet.yachat.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import chat.another.yet.yachat.R;
import chat.another.yet.yachat.model.Message;
import chat.another.yet.yachat.model.User;
import chat.another.yet.yachat.ui.adapter.MessageAdapter;

/**
 * Чат с конкретным пользователем
 */
public class ChatFragment extends Fragment implements TextWatcher, View.OnClickListener {

    private User user;

    private RecyclerView messagesView;
    private ImageButton sendButton;
    private EditText newMessageView;
    private MessageAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        messagesView = (RecyclerView) view.findViewById(R.id.messages_list);
        sendButton = (ImageButton) view.findViewById(R.id.send);
        newMessageView = (EditText) view.findViewById(R.id.message_input);

        newMessageView.addTextChangedListener(this);
        sendButton.setOnClickListener(this);

        messagesView.setLayoutManager(new LinearLayoutManager(getContext()));
        checkEnabled(newMessageView.getText());
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
        adapter = new MessageAdapter(this.user);
        messagesView.setAdapter(adapter);
        messagesView.scrollToPosition(adapter.getItemCount());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        checkEnabled(s);
    }

    private void checkEnabled(CharSequence s) {
        sendButton.setEnabled(!TextUtils.isEmpty(s));
    }

    @Override
    public void onClick(View v) {
        final Message message = new Message(user, false, newMessageView.getText().toString());
        adapter.addNewMessage(message);
        messagesView.smoothScrollToPosition(adapter.getItemCount());
        newMessageView.removeTextChangedListener(this);
        newMessageView.setText("");
        newMessageView.addTextChangedListener(this);
    }
}
