package com.boylab.projectstruct.ui.home.TemplateSelect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.boylab.projectstruct.R;

public class LocalTemplatesFragment extends Fragment {

    private static final String TAG = "LocalTemplatesFragment";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_local_templates, container, false);
        return root;
    }
}