package com.conversion.sbx.barbershop;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class EventFragment extends Fragment {

    private TextView tvWebTest;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_event, container, false);

        tvWebTest = v.findViewById(R.id.tv_webstest);

        //new fetchData().execute();

        //SOCIAL MEDIA SECTION/////////////////////////////////////////////////////////////////////
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

    private class fetchData extends AsyncTask<Void, Void, Void> {
        String words;

        @Override
        protected Void doInBackground(Void... voids) {
            Log.v("WEB", "STARTED");
            try {
                String url = "https://www.instagram.com/lic_tonsorial/";
                Connection conn = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
                Document doc = conn.get();
                Elements element = doc.select("p.about_description");
                words = element.text();
                Log.v("WEB", "words");
            } catch (Exception e) {
                e.printStackTrace();
                Log.v("WEB", "FAIL");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void avoid) {
            super.onPostExecute(avoid);
            tvWebTest.setText(words);
        }
    }

    //SOCIAL MEDIA CALL METHODS/////////////////////////////////////////////////////////////
    private void openTwitter() {
        Uri uri = Uri.parse("twitter://user?screen_name=Lic_tonsorial");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(intent);
        } catch (Exception e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://twitter.com/Lic_tonsorial")));
        }
    }

    private void openInstagram() {
        Uri uri = Uri.parse("http://instagram.com/_u/lic_tonsorial");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.instagram.android");
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/lic_tonsorial")));
        }
    }

    private void openFacebook(Context context) {
        String facebookUrl = getFacebookPageURL(context);
        Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
        facebookIntent.setData(Uri.parse(facebookUrl));
        startActivity(facebookIntent);
    }

    //method to get the right URL to use in the intent
    private String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        String FACEBOOK_URL = "https://www.facebook.com/longislandcitybarber";
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                String FACEBOOK_PAGE_ID = "longislandcitybarber";
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }
}
