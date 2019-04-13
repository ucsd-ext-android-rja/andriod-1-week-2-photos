package com.ucsdextandroid1.photosapp.profile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.ucsdextandroid1.photosapp.R;
import com.ucsdextandroid1.photosapp.data.Profile;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by rjaylward on 4/13/19
 */
public class ProfileViewHolder extends RecyclerView.ViewHolder {

    private CircleImageView iconImage;

    private TextView followingCountView;
    private TextView followersCountView;
    private TextView postsCountView;

    private Button editButton;
    private TextView nameView;

    private ImageView taggedIcon;
    private ImageView gridIcon;
    private ImageView feedIcon;

    @Nullable private Profile currentProfile;
    @Nullable private ProfileCallback currentCallback;

    public static ProfileViewHolder inflate(@NonNull ViewGroup parent) {
        return new ProfileViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_profile_header, parent, false));
    }

    public ProfileViewHolder(@NonNull View itemView) {
        super(itemView);
        taggedIcon = itemView.findViewById(R.id.vsp_tagged_icon);
        editButton = itemView.findViewById(R.id.vsp_edit_button);
        followingCountView = itemView.findViewById(R.id.vsp_third_title);
        followersCountView = itemView.findViewById(R.id.vsp_second_title);
        iconImage = itemView.findViewById(R.id.vsp_icon);
        gridIcon = itemView.findViewById(R.id.vsp_grid_icon);
        postsCountView = itemView.findViewById(R.id.vsp_first_title);
        feedIcon = itemView.findViewById(R.id.vsp_feed_icon);
        nameView = itemView.findViewById(R.id.vsp_name);

        setUpButtons();
    }

    public void bind(Profile profile) {
        currentProfile = profile;

        Picasso.get()
                .load(profile.getProfileImageUrl())
                .error(android.R.color.black)
                .into(iconImage);
        nameView.setText(profile.getFullName());
        postsCountView.setText(String.valueOf(profile.getPostCount()));
        followersCountView.setText(String.valueOf(profile.getFollowersCount()));
        followingCountView.setText(String.valueOf(profile.getFollowingCount()));
    }

    private void setUpButtons() {
        iconImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentCallback != null && currentProfile != null)
                    currentCallback.onAddToStoryClicked(currentProfile);
            }
        });

        gridIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentCallback != null)
                    currentCallback.onGidClicked();
            }
        });

        feedIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentCallback != null)
                    currentCallback.onFeedClicked();
            }
        });

        taggedIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentCallback != null)
                    currentCallback.onTaggedClicked();
            }
        });
    }

    public ProfileViewHolder setCallback(ProfileCallback callback) {
        currentCallback = callback;
        return this;
    }

    public interface ProfileCallback {
        void onAddToStoryClicked(Profile profile);
        void onGidClicked();
        void onFeedClicked();
        void onTaggedClicked();
    }
}
