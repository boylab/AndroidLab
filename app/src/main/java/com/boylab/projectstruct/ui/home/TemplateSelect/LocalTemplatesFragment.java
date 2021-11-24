package com.boylab.projectstruct.ui.home.TemplateSelect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.boylab.mybasepopup.PopupInput;
import com.boylab.projectstruct.R;

public class LocalTemplatesFragment extends Fragment {

    private static final String TAG = "LocalTemplatesFragment";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_local_templates, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final TextView editText = view.findViewById(R.id.edittext);
        editText.clearFocus();

        final TextView editText1 = view.findViewById(R.id.edittext1);
        editText1.clearFocus();

        final TextView editText2 = view.findViewById(R.id.edittext2);
        editText2.clearFocus();

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupInput popupInput = new PopupInput(getActivity());
                popupInput//.numberType(editText.getText().toString(), 6)
                        .showPopupWindow(true, new PopupInput.OnPopupInputListener() {
                            @Override
                            public void onInputText(String text) {
                                editText.setText(text);
                            }
                        });
            }
        });

        editText1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PopupInput popupInput = new PopupInput(getActivity());
                popupInput//.decimalType(editText1.getText().toString(), 6,2)
                        .showPopupWindow(true, new PopupInput.OnPopupInputListener() {
                            @Override
                            public void onInputText(String text) {
                                editText1.setText(text);
                            }
                        });
            }
        });

        editText2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PopupInput popupInput = new PopupInput(getContext());
                popupInput//.textType(editText2.getText().toString(), 10)
                        .showPopupWindow(true, new PopupInput.OnPopupInputListener() {
                            @Override
                            public void onInputText(String text) {
                                editText2.setText(text);
                            }
                        });
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

}