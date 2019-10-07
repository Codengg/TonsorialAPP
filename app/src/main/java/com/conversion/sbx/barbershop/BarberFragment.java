package com.conversion.sbx.barbershop;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BarberFragment extends Fragment {
    private CardView CVLuis, CVGeraldo, CVJustin, CVSamy;
    private Integer[] listNames = {R.string.luis, R.string.geraldo, R.string.justin, R.string.samy};
    private Integer[] listPhoto = {R.drawable.picluis, R.drawable.picgeraldo, R.drawable.picjustin, R.drawable.samy};
    private Button[] listButton = new Button[4];
    //private List<Integer> listDescription = Arrays.asList();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_barbers, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        CVLuis = view.findViewById(R.id.BarberLuis);
        //CVLuis.findViewById(R.id.tv_BarberName);

        //Insert unique data into the cards
        LinearLayout  llBarbers = view.findViewById(R.id.LL_Barbers);
        View vBar = null;
        TextView name;
        ImageView image;
        //Run through the list of items
        for(int i = 0; i < llBarbers.getChildCount(); i++){
            vBar = llBarbers.getChildAt(i);
            name = vBar.findViewById(R.id.tv_BarberName);
            image = vBar.findViewById(R.id.iv_BarberImage);
            listButton[i] = vBar.findViewById(R.id.btn_gotoProfile);
            //Put the data into the card
            name.setText(listNames[i]);
            Glide.with(getContext()).load(listPhoto[i]).into(image);
        }

        listButton[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ProfileFragment();
                Bundle args = new Bundle();
                args.putString("Barber", "luis");
                fragment.setArguments(args);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        listButton[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ProfileFragment();
                Bundle args = new Bundle();
                args.putString("Barber", "geraldo");
                fragment.setArguments(args);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        listButton[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ProfileFragment();
                Bundle args = new Bundle();
                args.putString("Barber", "justin");
                fragment.setArguments(args);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        listButton[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new ProfileFragment();
                Bundle args = new Bundle();
                args.putString("Barber", "samy");
                fragment.setArguments(args);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }
}
