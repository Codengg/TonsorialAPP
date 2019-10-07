package com.conversion.sbx.barbershop;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;


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
        TextView name, bio;
        ImageView picture, appTime;
        Button button;

        name = view.findViewById(R.id.tv_pageBarberName);
        bio = view.findViewById(R.id.tv_pageBiography);
        picture = view.findViewById(R.id.iv_pageBarberBackground);
        appTime = view.findViewById(R.id.iv_profileAppTime);
        button = view.findViewById(R.id.btn_profileAppoint);

        switch (Barber){
            case "luis":
                name.setText(R.string.luis);
                bio.setText(R.string.profileluisbio);
                Glide.with(getContext()).load(R.drawable.picluis).into(picture);
                Glide.with(getContext()).load(R.drawable.bookingluis).into(appTime);
                break;
            case "geraldo":
                name.setText(R.string.geraldo);
                bio.setText(R.string.profilegeraldobio);
                Glide.with(getContext()).load(R.drawable.picgeraldo).into(picture);
                Glide.with(getContext()).load(R.drawable.bookinggeraldo).into(appTime);
                break;
            case "samy":
                name.setText(R.string.samy);
                bio.setText(R.string.profilesamybio);
                Glide.with(getContext()).load(R.drawable.samy).into(picture);
                Glide.with(getContext()).load(R.drawable.bookingsamy).into(appTime);
                break;
            case "justin":
                name.setText(R.string.justin);
                bio.setText(R.string.profilejustinbio);
                Glide.with(getContext()).load(R.drawable.picjustin).into(picture);
                Glide.with(getContext()).load(R.drawable.bookingjustin).into(appTime);
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
}
