package com.conversion.sbx.barbershop;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private Integer[] listPhoto = {R.drawable.luis, R.drawable.geraldo, R.drawable.justin, R.drawable.samy};
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
            //Put the data into the card
            name.setText(listNames[i]);
            Glide.with(getContext()).load(listPhoto[i]).into(image);
        }
    }
}
