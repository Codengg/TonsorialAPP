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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class HomeFragment extends Fragment {

    private Button btn_bookappoint;
    private Button btn_contact;

    //TIME
    public static final String inputFormat = "HH:mm";

    private Date date;
    private Date dateCompareOne;
    private Date dateCompareTwo;

    private String compareStringOne = "10:00";
    private String compareStringTwo = "8:00";

    private SimpleDateFormat inputParser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btn_bookappoint = view.findViewById(R.id.btn_bookappoint);
        btn_contact = view.findViewById(R.id.btn_contact);

        inputParser = new SimpleDateFormat(inputFormat, Locale.US);
        //compareDates();

        btn_bookappoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new BookFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        btn_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new ContactFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }


    private void compareDates(){
        Calendar now = Calendar.getInstance();

        int hour = now.get(Calendar.HOUR);
        int minute = now.get(Calendar.MINUTE);

        date = parseDate(hour + ":" + minute);
        dateCompareOne = parseDate(compareStringOne);
        dateCompareTwo = parseDate(compareStringTwo);

        if ( dateCompareOne.before( date ) && dateCompareTwo.after(date)) {
          //  tv_ISOpen.setText("OPEN");
        }
        else{
          //  tv_ISOpen.setText("CLOSED");
         //   tv_ISOpen.setBackground(Drawable.createFromPath("@color/colorAccent"));
        }
    }

    private Date parseDate(String date) {

        try {
            return inputParser.parse(date);
        } catch (java.text.ParseException e) {
            return new Date(0);
        }
    }
}
