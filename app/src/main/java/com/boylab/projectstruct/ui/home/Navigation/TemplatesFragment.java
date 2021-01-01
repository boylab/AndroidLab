package com.boylab.projectstruct.ui.home.Navigation;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.boylab.projectstruct.R;
import com.boylab.projectstruct.ui.home.TemplateSelect.LocalTemplatesFragment;
import com.boylab.projectstruct.ui.home.TemplateSelect.PrinterTemplatesFragment;
import com.boylab.projectstruct.ui.home.TemplateSelect.SharedTemplatesFragment;
import com.google.android.material.tabs.TabLayout;


public class TemplatesFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_templates, container, false);
        TemplatesPagerAdapter templatesPagerAdapter = new TemplatesPagerAdapter(getContext(), getChildFragmentManager());
        ViewPager viewPager = root.findViewById(R.id.view_pager);
        viewPager.setAdapter(templatesPagerAdapter);
        TabLayout tabs = root.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        return root;
    }

    public class TemplatesPagerAdapter extends FragmentPagerAdapter {

        @StringRes
        private final int[] TAB_TITLES = new int[]{R.string.template_local_template, R.string.template_shared_template, R.string.template_printer_template};
        private final Context mContext;

        public TemplatesPagerAdapter(Context context, FragmentManager fm) {
            super(fm);
            mContext = context;
        }

        @Override
        public Fragment getItem(int position) {
            switch (TAB_TITLES[position]) {
                case R.string.template_local_template:
                    return new LocalTemplatesFragment();
                case R.string.template_shared_template:
                    return new SharedTemplatesFragment();
                case R.string.template_printer_template:
                    return new PrinterTemplatesFragment();
            }
            return null;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mContext.getResources().getString(TAB_TITLES[position]);
        }

        @Override
        public int getCount() {
            // Show total pages.
            return TAB_TITLES.length;
        }
    }

}