package com.conversion.sbx.barbershop;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;

public class ProfileFragment extends Fragment {
    private String Barber = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Barber = getArguments().getString("Barber");
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        TextView name, bio;
        TextView sunday, monday, tuesday, wednesday, thrusday, friday, saturday;
        ImageView picture, appTime;
        Button button;

        name = view.findViewById(R.id.tv_pageBarberName);
        bio = view.findViewById(R.id.tv_pageBiography);
        picture = view.findViewById(R.id.iv_pageBarberBackground);
        button = view.findViewById(R.id.btn_profileAppoint);

        switch (Barber){
            case "luis":
                name.setText(R.string.luis);
                bio.setText(R.string.profileluisbio);
                sunday = view.findViewById(R.id.tv_profileSunday);
                tuesday = view.findViewById(R.id.tv_profileTuesday);
                wednesday = view.findViewById(R.id.tv_profileWednesday);
                thrusday = view.findViewById(R.id.tv_profileThrusday);
                friday = view.findViewById(R.id.tv_profileFriday);
                saturday = view.findViewById(R.id.tv_profileSaturday);

                sunday.setText(R.string.eleventoseven);
                tuesday.setText(R.string.notavailable);
                wednesday.setText(R.string.notavailable);
                thrusday.setText(R.string.eleventoeight);
                friday.setText(R.string.eleventoeight);
                saturday.setText(R.string.tentoeight);

                Glide.with(getContext()).load(R.drawable.picluis).into(picture);
                break;
            case "geraldo":
                name.setText(R.string.geraldo);
                bio.setText(R.string.profilegeraldobio);
                Glide.with(getContext()).load(R.drawable.picgeraldo).into(picture);
                break;
            case "samy":
                name.setText(R.string.samy);
                bio.setText(R.string.profilesamybio);
                Glide.with(getContext()).load(R.drawable.samy).into(picture);
                break;
            case "justin":
                name.setText(R.string.justin);
                bio.setText(R.string.profilejustinbio);
                sunday = view.findViewById(R.id.tv_profileSunday);
                monday = view.findViewById(R.id.tv_profileMonday);
                tuesday = view.findViewById(R.id.tv_profileTuesday);
                wednesday = view.findViewById(R.id.tv_profileWednesday);
                thrusday = view.findViewById(R.id.tv_profileThrusday);
                friday = view.findViewById(R.id.tv_profileFriday);
                saturday = view.findViewById(R.id.tv_profileSaturday);

                sunday.setText(R.string.eleventoseven);
                monday.setText(R.string.notavailable);
                tuesday.setText(R.string.eleventoeight);
                wednesday.setText(R.string.notavailable);
                thrusday.setText(R.string.eleventoeight);
                friday.setText(R.string.eleventoeight);
                saturday.setText(R.string.tentoeight);

                Glide.with(getContext()).load(R.drawable.picjustin).into(picture);
                break;
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new BookFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }
    @Override
    public void onDetach() {
        super.onDetach();
        Glide.get(getContext()).clearMemory();
        getFragmentManager().beginTransaction().remove(ProfileFragment.this).commitAllowingStateLoss();
    }
}
