package com.mostafa.bittask.ui;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mostafa.bittask.Adapter.AdapterPhotos;
import com.mostafa.bittask.Base.BaseActivity;
import com.mostafa.bittask.Model.Info.ResponseProfile;
import com.mostafa.bittask.Model.Photos.DataItem;
import com.mostafa.bittask.R;
import com.mostafa.bittask.ViewModel.ProfileViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainActivity extends BaseActivity {

    private TextView fullname;
    private TextView location;
    private TextView bio;
    private ImageView imageProfile;
    private TextView posts;
    private TextView followers;
    private TextView following;
    private RecyclerView recyclerViewPhotos;
    private AdapterPhotos adapter;
    private TextView ListEmpty;


    private ProfileViewModel profileViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        fullname = findViewById(R.id.id_tv_fullName);
        location = findViewById(R.id.id_tv_location);
        bio = findViewById(R.id.id_tv_bio);
        posts = findViewById(R.id.id_tv_posts);
        followers = findViewById(R.id.id_tv_followers);
        following = findViewById(R.id.id_tv_following);
        imageProfile = findViewById(R.id.UserImageProfile);
        ListEmpty = findViewById(R.id.id_tv_no_photo);
        recyclerViewPhotos = findViewById(R.id.id_rec_photo);
        recyclerViewPhotos.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new AdapterPhotos(null);


        profileViewModel.ShowProfile(1574083);
        profileViewModel.ShowPhotos();

        ////////////////////////////////
        profileViewModel.getErrorMassage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String message) {
                if (message != null) {
                    //errorMessage from api if not working
                    Toast.makeText(getBaseContext(), "No Data", Toast.LENGTH_LONG).show();
                }
            }
        });
        profileViewModel.getProfileLiveData().observe(this, new Observer<ResponseProfile>() {
            @Override
            public void onChanged(@Nullable ResponseProfile responseProfile) {
                if ("true".equals(String.valueOf(responseProfile.isStatus()))) {
                    fullname.setText(responseProfile.getData().getFullName());
                    location.setText(responseProfile.getData().getLocation());
                    bio.setText(responseProfile.getData().getBio());
                    Picasso.get().load(responseProfile.getData().getProfilePicture()).into(imageProfile);
                    posts.setText(String.valueOf(responseProfile.getData().getCounts().getPosts()));
                    followers.setText(String.valueOf(responseProfile.getData().getCounts().getFollowers()));
                    following.setText(String.valueOf(responseProfile.getData().getCounts().getFollowing()));
                }
            }
        });

        profileViewModel.getPhotosLiveData().observe(this, new Observer<List<DataItem>>() {
            @Override
            public void onChanged(@Nullable List<DataItem> dataItems) {
                if (dataItems == null) {
                    //errorMessage if data coming is null;
                    ListEmpty.setVisibility(View.VISIBLE);
                } else {
                    //show data in recyclerView
                    adapter = new AdapterPhotos(dataItems);
                    recyclerViewPhotos.setAdapter(adapter);
                    adapter.setOnItemClickListener(new AdapterPhotos.OnItemClickListener() {
                        @Override
                        public void onItemClick(int pos, DataItem Message) {
                            Intent intent = new Intent(MainActivity.this, DetailsPhotoActivity.class);
                            intent.putExtra("Title", String.valueOf(Message.getTitle()));
                            intent.putExtra("Photo", String.valueOf(Message.getImage()));
                            startActivity(intent);
                        }
                    });
                }
            }
        });


    }
}
