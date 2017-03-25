package chat.another.yet.yachat.ui.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import chat.another.yet.yachat.R;
import chat.another.yet.yachat.model.Message;
import chat.another.yet.yachat.model.User;
import chat.another.yet.yachat.utils.Helper;

/**
 * Created by timofey.malygin on 16/03/2017.
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageHolder> {

    private final List<Message> messages;

    public MessageAdapter(@NonNull User companion) {
        messages = Helper.getMessageFor(companion);
    }

    @Override
    public MessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case R.layout.li_message_in:
                return new IncommingMessageHolder(inflater.inflate(viewType, parent, false));
            case R.layout.li_message_out:
                return new MessageHolder(inflater.inflate(viewType, parent, false));
            default:
                throw new RuntimeException("Unknown type " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(MessageHolder holder, int position) {
        if (holder instanceof IncommingMessageHolder) {
            ((IncommingMessageHolder) holder).bind(messages.get(position),
                    position > 0 && getItemViewType(position - 1) == R.layout.li_message_in);
        } else {
            holder.bind(messages.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return messages.get(position).incomming ? R.layout.li_message_in : R.layout.li_message_out;
    }

    public void addNewMessage(@NonNull Message message) {
        messages.add(message);
        notifyItemInserted(messages.size());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public static class IncommingMessageHolder extends MessageHolder {

        final AppCompatImageView avatarView;

        IncommingMessageHolder(View itemView) {
            super(itemView);
            avatarView = (AppCompatImageView) itemView.findViewById(R.id.user_icon);
        }

        void bind(@NonNull Message message, boolean prevIncomming) {
            super.bind(message);
            if (prevIncomming) {
                avatarView.setVisibility(View.INVISIBLE);
            } else {
                avatarView.setVisibility(View.VISIBLE);
                avatarView.setImageResource(message.companion.icon);
            }
        }
    }

    public static class MessageHolder extends RecyclerView.ViewHolder {

        final TextView messageView;

        MessageHolder(View itemView) {
            super(itemView);
            messageView = (TextView) itemView.findViewById(R.id.message);
        }

        void bind(@NonNull Message message) {
            messageView.setText(message.message);
        }

        @VisibleForTesting
        public boolean is(Message message) {
            return messageView.getText().equals(message.message);
        }
    }
}
