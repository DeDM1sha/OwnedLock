package com.example.owned.ownedlock;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;


public class ChangingPasswordsFragment extends Fragment {

    private String Type;
    private String UserPassword;
    private final String SAVED_PASSWORD = "PASSWORD";

    private EditText CurrentPassword;
    private EditText NewPassword;
    private EditText RepeatPassword;
    private Button Enter;
    private TextView Title;

    SharedPreferences sharedPreferences;

    public ChangingPasswordsFragment(String type) {
        this.Type = type;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_changing_defaultpasswords, container, false);
        CurrentPassword = (EditText) view.findViewById(R.id.Current_Password);
        NewPassword = (EditText) view.findViewById(R.id.New_Password);
        RepeatPassword = (EditText) view.findViewById(R.id.Repeat_Password);
        Enter = (Button) view.findViewById(R.id.Enter);
        Title = (TextView) view.findViewById(R.id.Title);

            if (Type.equals("Default")) {
                Title.setText("Изменение стартового пароля");
                sharedPreferences =  (this.getActivity()).getSharedPreferences(SAVED_PASSWORD,MODE_PRIVATE);
                UserPassword =  sharedPreferences.getString(SAVED_PASSWORD, "");
                Toast.makeText(getActivity(),UserPassword, Toast.LENGTH_SHORT).show();

                Enter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View e) {
                            if (CurrentPassword.getText().toString().equals(UserPassword)) {
                                if (NewPassword.getText().toString().length() > 3) {
                                    if (NewPassword.getText().toString().equals(RepeatPassword.getText().toString())) {
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString(SAVED_PASSWORD, NewPassword.getText().toString());
                                        editor.commit();
                                        Toast.makeText(getActivity(), "Пароль успешно изменен!", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                        Toast.makeText(getActivity(), "Пароли не совпадают!", Toast.LENGTH_SHORT).show();
                                }
                                else
                                    Toast.makeText(getActivity(), "Пароль должен содержать более 3-ех цифр!", Toast.LENGTH_SHORT).show();
                            }
                            else
                                Toast.makeText(getActivity(), "Текущий пароль введён неверно!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else if (Type.equals("Alternate")) {
                Title.setText("Изменение альтернативного пароля");

            }
        return view;
    }

}
