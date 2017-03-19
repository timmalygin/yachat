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
import chat.another.yet.yachat.ui.adapter.FriendsAdapter;

/**
 * Фрагмент со списком Друзей
 */
public class FriendsFragment extends Fragment {

    private RecyclerView usersView;
    private String login;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_users, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        usersView = (RecyclerView) view.findViewById(R.id.friends_list);
        usersView.setLayoutManager(new LinearLayoutManager(getContext()));
        updateUi();
    }

    public void setLogin(@NonNull String login) {
        this.login = login;
        updateUi();
    }

    private void updateUi() {
        if (usersView == null || login == null) {
            return;
        }
        usersView.setAdapter(new FriendsAdapter(this.login, (OnSelectUserListener) getActivity()));
    }

}
