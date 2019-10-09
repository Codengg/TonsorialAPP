package com.conversion.sbx.barbershop;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class BookFragment extends Fragment {

    private ProgressDialog dialog;
    private WebView wv_book;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_book, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        dialog = new ProgressDialog(view.getContext());
        wv_book = view.findViewById(R.id.wv_book);
        //Setup WebView
        wv_book.setWebViewClient(new WebViewClient(){
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

        //Settings
        WebSettings webSettings = wv_book.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUserAgentString("BarberShop");

        //Launch Web
        wv_book.loadUrl("https://lictonsorial.resurva.com/book");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        wv_book.clearHistory();
        wv_book.clearCache(true);
        wv_book.loadUrl("about:blank");
        wv_book.clearView();

        wv_book.onPause();
        wv_book.removeAllViews();
        wv_book.destroyDrawingCache();

        wv_book.destroy();
        wv_book = null;
        getFragmentManager().beginTransaction().remove(BookFragment.this).commitAllowingStateLoss();
    }
}


