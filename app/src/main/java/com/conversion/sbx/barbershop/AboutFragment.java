package com.conversion.sbx.barbershop;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.transition.Slide;
import androidx.transition.TransitionManager;
import androidx.transition.TransitionSet;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.conversion.sbx.barbershop.Extra.ViewPageAdapter;

public class AboutFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        transitionAction(container);
        return inflater.inflate(R.layout.fragment_about, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ViewPager viewPager;;
        viewPager = view.findViewById(R.id.viewPager);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        View productBanner = view.findViewById(R.id.aboutBanner);
        TextView textView = productBanner.findViewById(R.id.tv_backgroundText);
        ImageView imageView = productBanner.findViewById(R.id.iv_backgroundImage);

        textView.setText(R.string.about_us);
        Glide.with(getContext()).load(R.drawable.aboutpic).into(imageView);
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getContext());
        viewPager.setAdapter(viewPageAdapter);
    }

    private void transitionAction(ViewGroup viewGroup){
        viewGroup.setVisibility(View.INVISIBLE);
        TransitionSet set = new TransitionSet()
                .addTransition(new Slide(Gravity.LEFT))
                .setDuration(400);
        TransitionManager.beginDelayedTransition(viewGroup, set);
        viewGroup.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Glide.get(getContext()).clearMemory();
        getFragmentManager().beginTransaction().remove(AboutFragment.this).commitAllowingStateLoss();
    }
}
