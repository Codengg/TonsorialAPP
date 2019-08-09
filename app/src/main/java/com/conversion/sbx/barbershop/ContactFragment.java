package com.conversion.sbx.barbershop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.net.URI;

public class ContactFragment extends Fragment {

    private ImageView ivgooglemap;
    private Button btncallbarbershop;
    private static String URLmap = "https://www.google.com/maps/place/32-86+Steinway+St,+Astoria,+NY+11103/@40.7567727,-73.9212617,18z/data=!4m5!3m4!1s0x89c25f3b24799959:0xada63e6e78ea3666!8m2!3d40.7570534!4d-73.9208482";
    private Uri uri;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.fragment_contact, container, false);

        ivgooglemap = v.findViewById(R.id.ivgooglemsp);
        btncallbarbershop = v.findViewById(R.id.btn_callbarbershop);

        uri = Uri.parse(URLmap); // missing 'http://' will cause crashed

        //OPEN GOOGLE MAPS
        ivgooglemap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        //CALL
        btncallbarbershop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:13476546386"));
                startActivity(callIntent);
            }
        });

        //TEXT MESSAGE
        v.findViewById(R.id.btn_textbarbershop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri sms_uri = Uri.parse("smsto:+13476546386");
                Intent sms_intent = new Intent(Intent.ACTION_SENDTO, sms_uri);
                startActivity(sms_intent);
            }
        });

        return v;
    }


}
