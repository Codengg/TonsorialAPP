package com.conversion.sbx.barbershop;

import android.app.ProgressDialog;
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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class EventFragment extends Fragment {

    private TextView tvDescription, tvTitle, tvDate, tvPrice;
    //private ImageView ivPoster;
    //private Button btnLink;
    private Button btnOpenEvents;

    private String eventURL;
    private Boolean hasButtonClicked = false;
    private WebView wv_Event;
    private ProgressDialog dialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_event, container, false);
        dialog  = new ProgressDialog(v.getContext());

        tvDescription = v.findViewById(R.id.tv_description);
     /*   tvTitle = v.findViewById(R.id.tv_title);
        tvDate = v.findViewById(R.id.tv_date);
        tvPrice = v.findViewById(R.id.tv_price);
        ivPoster = v.findViewById(R.id.iv_poster);
        btnLink = v.findViewById(R.id.btn_link);
        */
        btnOpenEvents = v.findViewById(R.id.btn_openEvents);
        wv_Event = v.findViewById(R.id.wb_Events);

        //Load events from EventBrite into WebView
        btnOpenEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!hasButtonClicked) {
                    wbEvents();
                    hasButtonClicked = true;
                    btnOpenEvents.setText(R.string.goBack);
                }
                else {
                    wv_Event.goBack();
                }
            }
        });

        //Parse Date for Events
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
    private void wbEvents(){
        //Setup WebView
        wv_Event.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (dialog.isShowing())
                    dialog.dismiss();
            }
        });
        dialog.setMessage("Loading..Please wait.");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        wv_Event.setVerticalScrollBarEnabled(true);
        wv_Event.setHorizontalScrollBarEnabled(true);
        wv_Event.requestFocus();

        //Settings
        WebSettings webSettings = wv_Event.getSettings();
        webSettings.setUserAgentString("BarberShop");
        webSettings.setJavaScriptEnabled(true);

        //Load Up web
        wv_Event.loadUrl("https://www.eventbrite.com/o/lic-tonsorial-24930644401");
    }

    private void openEvent(){
        Uri uri = Uri.parse(eventURL);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(intent);
        } catch (Exception e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(eventURL)));
        }
    }

    private class fetchData extends AsyncTask<Void, Void, Void> {
        String title, date, description, price, link, image;

        @Override
        protected Void doInBackground(Void... voids) {
            Log.v("WEB", "STARTED");
            try {
                String url = "https://www.eventbrite.com/o/lic-tonsorial-24930644401";
                Connection conn = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
                Document doc = conn.get();
                //Elements element = doc.select(".list-card__venue");
                //Get Elements from Eventbrite
                title = doc.select(".list-card__title").first().text();
                date = doc.select(".list-card__date").text();
                description = doc.select(".list-card__venue").text();
                price = doc.select(".list-card__label").first().text();
                link = doc.select(".list-card__main").attr("href");
                image = doc.select("img.js-poster-image").attr("src");

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
            tvTitle.setText(title);
            tvDate.setText(date);
            tvDescription.setText(description);
            tvPrice.setText(price);

            //btnLink.setVisibility(View.VISIBLE);
            eventURL = link;

            //Glide.with(getContext()).load(image).into(ivPoster);
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
