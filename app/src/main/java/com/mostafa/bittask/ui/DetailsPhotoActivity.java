package com.mostafa.bittask.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.mostafa.bittask.R;
import com.squareup.picasso.Picasso;

public class DetailsPhotoActivity extends AppCompatActivity {

    private TextView showTitle;
    private ImageView showImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_photo);

        showTitle=findViewById(R.id.id_tv_title);
        showImageView=findViewById(R.id.id_iv_show_photo);

        showTitle.setText(getIntent().getStringExtra("Title"));
        Log.e("title ", getIntent().getStringExtra("Title"));

        Picasso.get()
                .load(getIntent().getStringExtra("Photo"))
                .placeholder(R.drawable.user_defult)
                .error(android.R.drawable.stat_notify_error)
                .into(showImageView);
    }
}
