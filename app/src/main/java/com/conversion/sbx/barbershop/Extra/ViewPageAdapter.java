package com.conversion.sbx.barbershop.Extra;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.conversion.sbx.barbershop.R;


public class ViewPageAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private Integer [] images = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5
                                ,R.drawable.image6, R.drawable.image7, R.drawable.image8, R.drawable.image9};

    public ViewPageAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.imageinsert, null);
        ImageView imageView  = view.findViewById(R.id.imageViewslide);
        Glide.with(context).load(images[position]).into(imageView);
        ViewPager vp = (ViewPager) container;
        vp.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }
}
