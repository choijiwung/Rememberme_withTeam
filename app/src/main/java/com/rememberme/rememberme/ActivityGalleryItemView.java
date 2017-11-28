package com.rememberme.rememberme;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by samsung on 2017-11-28.
 */

public class ActivityGalleryItemView extends LinearLayout {
    TextView textView;
    TextView textView2;
    ImageView imageView;
    public ActivityGalleryItemView(Context context) {
        super(context);
        init(context);
    }

    public ActivityGalleryItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.activity_gallery_item,this,true);

        textView = (TextView) findViewById(R.id.textView);
        textView2= (TextView) findViewById(R.id.textView2);
        imageView = (ImageView) findViewById(R.id.imageView);

    }

    public void setTourTitle(String TourTitle){
        textView.setText(TourTitle);
    }
    public void setTourHash(String TourHash){
        textView2.setText(TourHash);
    }

    public void setImage(int imageId){
        imageView.setImageResource(imageId);
    }


}
