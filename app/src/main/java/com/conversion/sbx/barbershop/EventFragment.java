package com.conversion.sbx.barbershop;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.conversion.sbx.barbershop.Extra.ViewPageAdapter;

public class EventFragment  extends Fragment {

    private static String FACEBOOK_URL = "https://www.facebook.com/longislandcitybarber";
    private static String FACEBOOK_PAGE_ID = "longislandcitybarber";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.fragment_event, container, false);

        //FACEBOOK LINK
        v.findViewById(R.id.iv_Facebook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFacebook(v.getContext());
            }
        });

        //TWITTER LINK
        v.findViewById(R.id.iv_twitter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTwitter();
            }
        });

        //INSTAGRAM LINK
        v.findViewById(R.id.iv_Instagram).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInstagram();
            }
        });

        return v;
    }

    private void openTwitter(){
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("twitter://user?screen_name=Lic_tonsorial"));
            startActivity(intent);
        } catch (Exception e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://twitter.com/Lic_tonsorial")));
        }
    }

    private void openInstagram(){
        Uri uri = Uri.parse("http://instagram.com/_u/lic_tonsorial");
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.instagram.android");

        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/lic_tonsorial")));
        }
    }

    private void openFacebook(Context context){
        Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
        String facebookUrl = getFacebookPageURL(context);
        facebookIntent.setData(Uri.parse(facebookUrl));
        startActivity(facebookIntent);
    }

    //method to get the right URL to use in the intent
    private String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }
}
