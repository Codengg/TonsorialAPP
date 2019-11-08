package com.conversion.sbx.barbershop;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.transition.ChangeBounds;
import androidx.transition.Fade;
import androidx.transition.TransitionManager;
import androidx.transition.TransitionSet;

public class EventFragment extends Fragment {
    private Button btnOpenEvents;
    private WebView wv_Event;
    private ProgressDialog dialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        transitionAction(container);
        View v = inflater.inflate(R.layout.fragment_event, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        dialog = new ProgressDialog(v.getContext());

        btnOpenEvents = v.findViewById(R.id.btn_openEvents);
        wv_Event = v.findViewById(R.id.wb_Events);

        wbEvents();
        btnOpenEvents.setText(R.string.goBack);

        //Load events from EventBrite into WebView
        btnOpenEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wv_Event.goBack();
            }
        });

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

    private void transitionAction(ViewGroup viewGroup) {
        viewGroup.setVisibility(View.INVISIBLE);
        TransitionSet set = new TransitionSet()
                .addTransition(new ChangeBounds())
                .addTransition(new Fade())
                .setDuration(750);
        TransitionManager.beginDelayedTransition(viewGroup, set);
        viewGroup.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        wv_Event.clearHistory();
        wv_Event.clearCache(true);
        wv_Event.loadUrl("about:blank");
        wv_Event.clearView();

        wv_Event.onPause();
        wv_Event.removeAllViews();
        wv_Event.destroyDrawingCache();

        wv_Event.destroy();
        wv_Event = null;
        getFragmentManager().beginTransaction().remove(EventFragment.this).commitAllowingStateLoss();
    }

    private void wbEvents() {
        //Setup WebView
        wv_Event.setWebViewClient(new WebViewClient() {
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
