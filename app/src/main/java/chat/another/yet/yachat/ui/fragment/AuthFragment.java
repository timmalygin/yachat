package chat.another.yet.yachat.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import chat.another.yet.yachat.R;
import chat.another.yet.yachat.ui.activity.MainActivity;
import chat.another.yet.yachat.utils.Helper;

/**
 * Фрагмент с авторизацией
 */
public class AuthFragment extends Fragment implements View.OnClickListener, TextWatcher {

    public static final boolean FAST = true;
    private TextView errorView;
    private EditText loginView;
    private EditText pwdView;
    private Button loginButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_auth, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // получсем ссылки на вьюхи
        loginView = (EditText) view.findViewById(R.id.login);
        pwdView = (EditText) view.findViewById(R.id.password);
        loginButton = (Button) view.findViewById(R.id.enter);
        errorView = (TextView) view.findViewById(R.id.error);

        // устанавлиавем слушателей на ввод данных
        loginView.addTextChangedListener(this);
        pwdView.addTextChangedListener(this);
        // устанавливаем слушателя на нажатие кнопки
        loginButton.setOnClickListener(this);

        checkEnabledButton();
    }

    @Override
    public void onClick(View v) {
        // проверяем логн
        final String login = loginView.getText().toString();
        if (!FAST && !Helper.isLoginCorrect(login)) {
            errorView.setVisibility(View.VISIBLE);
            errorView.setText(R.string.err_incorrect_login);
            return;
        }
        // проверяем пароль
        final String password = pwdView.getText().toString();
        if (!FAST && !Helper.isPasswordCorrect(password)) {
            errorView.setVisibility(View.VISIBLE);
            errorView.setText(R.string.err_incorrect_password);
            return;
        }
        // если все правильно, то отображаем следующую форму
        errorView.setVisibility(View.GONE);
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra(MainActivity.USERNAME, login);
        startActivity(intent);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        checkEnabledButton();
    }

    private void checkEnabledButton() {
        loginButton.setEnabled(FAST || loginView.getText().length() > 0 && pwdView.getText().length() > 0);
    }
}
