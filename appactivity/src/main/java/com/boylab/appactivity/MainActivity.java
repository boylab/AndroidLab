package com.boylab.appactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.boylab.mybasepopup.PopupInput;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text_input = findViewById(R.id.text_input);

        text_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupInput popupInput = new PopupInput(MainActivity.this);
                popupInput.showPopupWindow(true, new PopupInput.OnPopupInputListener() {
                    @Override
                    public void onInputText(String text) {
                        Log.i("___boylab>>>___", "onInputText: "+text);
                    }
                });
            }
        });
    }
}