package com.conversion.sbx.barbershop;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BookFragment extends Fragment {
    private WebView wv_book;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_book, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        wv_book = view.findViewById(R.id.wv_book);
        wv_book.setWebViewClient(new WebViewClient());

        WebSettings webSettings = wv_book.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUserAgentString("BarberShop");

        wv_book.loadUrl("https://lictonsorial.resurva.com/book");


    }

}


