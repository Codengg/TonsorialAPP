package com.conversion.sbx.barbershop;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.conversion.sbx.barbershop.Extra.ViewPageAdapter;

public class AboutFragment extends Fragment {
    ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_about, container, false);

        return  v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewPager = view.findViewById(R.id.viewPager);

        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getContext());
        viewPager.setAdapter(viewPageAdapter);
    }
}
