package chat.another.yet.yachat.model;

import android.support.annotation.NonNull;

/**
 * Created by timofey.malygin on 16/03/2017.
 */

public class Message {

    @NonNull
    public final User companion;
    public final boolean incomming;
    @NonNull
    public final String message;

    public Message(@NonNull User companion, boolean incomming, @NonNull String message) {
        this.companion = companion;
        this.incomming = incomming;
        this.message = message;
    }
}
