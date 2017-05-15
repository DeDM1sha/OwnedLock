package com.example.owned.ownedlock;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class PreviewFragment extends Fragment implements View.OnClickListener {

    private TextView TimeView;
    private TextView PasswordView;
    private Button[] buttons = new Button[10];
    private Button DeleteButton;
    private String Password = "";
    private String Time;
    private boolean Click = false;
    public PreviewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_preview, container, false);
        TimeView = (TextView) view.findViewById (R.id.TimeView);
        PasswordView = (TextView) view.findViewById (R.id.PasswordView);
        buttons[0] = (Button) view.findViewById (R.id.Button_0);
        buttons[1] = (Button) view.findViewById (R.id.Button_1);
        buttons[2] = (Button) view.findViewById (R.id.Button_2);
        buttons[3] = (Button) view.findViewById (R.id.Button_3);
        buttons[4] = (Button) view.findViewById (R.id.Button_4);
        buttons[5] = (Button) view.findViewById (R.id.Button_5);
        buttons[6] = (Button) view.findViewById (R.id.Button_6);
        buttons[7] = (Button) view.findViewById (R.id.Button_7);
        buttons[8] = (Button) view.findViewById (R.id.Button_8);
        buttons[9] = (Button) view.findViewById (R.id.Button_9);
        DeleteButton = (Button) view.findViewById (R.id.Button_Delete);
            for (short i = 0; i < 10; i++)
                buttons[i].setOnClickListener(this);
        DeleteButton.setOnClickListener(this);

        return view;
    }
    @Override
    public void onClick(View v) {

        if (Click){
            Password = "";
            Click = false;
            PasswordView.setTextColor(Color.WHITE);
        }
        Button button = (Button) v;
            if (button.getText().equals("delete")) {
                char [] Massive = Password.toCharArray();
                short C = (short) Password.length();
                Password = "";
                for (short i = 0; i < C-1; i++)
                    Password = Password + Massive[i];
                if (Click) {
                    Click = false;
                    PasswordView.setTextColor(Color.WHITE);
                }
            }
            else {
                Password += button.getText();
                PasswordView.setText(Password);

            }
    }
}
