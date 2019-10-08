package com.conversion.sbx.barbershop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class HomeFragment extends Fragment {

    private Button btn_bookappoint;
    private Button btn_contact, btn_Address, btn_Contact;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        ImageView imageView = view.findViewById(R.id.barberbackground);
        btn_bookappoint = view.findViewById(R.id.btn_bookappoint);
        btn_Address = view.findViewById(R.id.btn_Address);
        btn_Contact = view.findViewById(R.id.btn_contact);

        Glide.with(getContext()).load(R.drawable.barberbackgrounnd).into(imageView);

        btn_bookappoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookAppointment();
            }
        });

        btn_Contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeText();
            }
        });

        btn_Address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Glide.get(getContext()).clearMemory();
        getFragmentManager().beginTransaction().remove(HomeFragment.this).commitAllowingStateLoss();
        Toast.makeText(getContext(), "Deleted the page", Toast.LENGTH_SHORT).show();
    }

    /**
     * Open up bookFragment
     */
    private void bookAppointment() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new BookFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * Open up phone to make a call
     */
    private void makeCall() {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:13476546386"));
        startActivity(callIntent);
    }

    /**
     * Open up text messaging
     */
    private void makeText() {
        Uri sms_uri = Uri.parse("smsto:+13476546386");
        Intent sms_intent = new Intent(Intent.ACTION_SENDTO, sms_uri);
        startActivity(sms_intent);
    }

    /**
     *
     */
    private void openMap(){
        String URLmap = "https://www.google.com/maps/place/32-86+Steinway+St,+Astoria,+NY+11103/@40.7567727,-73.9212617,18z/data=!4m5!3m4!1s0x89c25f3b24799959:0xada63e6e78ea3666!8m2!3d40.7570534!4d-73.9208482";
        Uri uri = Uri.parse(URLmap);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    /**
     * Open up Contact Page
     */
    private void openContact() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new ContactFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
