package com.ucsdextandroid1.photosapp.profile;

import android.content.res.Resources;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.ucsdextandroid1.photosapp.R;
import com.ucsdextandroid1.photosapp.data.Post;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by rjaylward on 4/12/19
 */
public class PostViewHolder extends RecyclerView.ViewHolder {

    private ImageView userIconView;
    private TextView usernameView;
    private TextView locationView;
    private ImageView heartIcon;
    private TextView datetimeLabel;
    private TextView captionView;
    private ImageView bookmarkIcon;
    private ImageView imageView;
    private ImageView menuIcon;
    private ImageView commentIcon;
    private ImageView sendIcon;

    @Nullable private Post currentPost;
    @Nullable private PostCallback currentCallback;

    private static final long MILLISECONDS_PER_DAY = TimeUnit.DAYS.toMillis(1);
    private static final long MILLISECONDS_PER_HOUR = TimeUnit.HOURS.toMillis(1);

    public static PostViewHolder inflate(ViewGroup parent) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_post, parent, false));
    }

    private PostViewHolder(@NonNull View itemView) {
        super(itemView);
        // header
        userIconView = itemView.findViewById(R.id.vp_icon_image);
        usernameView = itemView.findViewById(R.id.vp_title);
        locationView = itemView.findViewById(R.id.vp_subtitle);

        menuIcon = itemView.findViewById(R.id.vp_menu_icon);

        // image
        imageView = itemView.findViewById(R.id.vp_image);

        // buttons
        heartIcon = itemView.findViewById(R.id.vp_heart_icon);
        bookmarkIcon = itemView.findViewById(R.id.vp_bookmark_icon);
        commentIcon = itemView.findViewById(R.id.vp_comment_icon);
        sendIcon = itemView.findViewById(R.id.vp_send_icon);

        // footer
        captionView = itemView.findViewById(R.id.vp_caption);
        datetimeLabel = itemView.findViewById(R.id.vp_bottom_label);

        setUpClickListeners();
    }

    /**
     * Displays the Post
     * @param post post
     */
    public void bindPost(@Nullable Post post, boolean isGridMode) {
        currentPost = post;

        if(currentPost != null) {
            usernameView.setText(currentPost.getUsername());
            locationView.setText(currentPost.getLocationName());
            heartIcon.setSelected(currentPost.isLikedByUser());
            captionView.setText(currentPost.getCaption());
            datetimeLabel.setText(formatDatetime(itemView.getResources(), post.getTimestamp()));

            Picasso.get().load(currentPost.getImageUrl())
                    .placeholder(android.R.color.holo_blue_dark)
                    .into(imageView);

            Picasso.get().load(currentPost.getProfileImageUrl()).into(userIconView);
        }
        else {
            usernameView.setText("");
            locationView.setText("");
            heartIcon.setSelected(false);
            captionView.setText("");
            datetimeLabel.setText("");

            imageView.setImageResource(android.R.color.white);
            userIconView.setImageResource(android.R.color.white);
        }
        int visiblity = isGridMode ? View.GONE : View.VISIBLE;

        userIconView.setVisibility(visiblity);
        usernameView.setVisibility(visiblity);
        locationView.setVisibility(visiblity);
        heartIcon.setVisibility(visiblity);
        datetimeLabel.setVisibility(visiblity);
        captionView.setVisibility(visiblity);
        bookmarkIcon.setVisibility(visiblity);
        commentIcon.setVisibility(visiblity);
        sendIcon.setVisibility(visiblity);
        menuIcon.setVisibility(visiblity);

    }

    /**
     * Here we want to turn a timestamp into a label like 5 hours ago or 3 days ago. To do that we
     * pass a timestamp with is a representation of a date in milliseconds. We subtract that
     * timestamp from today's timestamp
     *
     * @param resources resources from context
     * @param timestamp timestamp in milliseconds
     * @return either # hours ago or # days ago
     */
    private String formatDatetime(Resources resources, long timestamp) {
        long millisecondsAgo = new Date().getTime() - timestamp;

        if(millisecondsAgo > MILLISECONDS_PER_DAY) {
            int daysAgo = (int) (millisecondsAgo / MILLISECONDS_PER_DAY);
            return resources.getQuantityString(R.plurals.days_ago, daysAgo, daysAgo);
        }
        else {
            int hoursAgo = (int) (millisecondsAgo / MILLISECONDS_PER_HOUR);
            return resources.getQuantityString(R.plurals.hours_ago, hoursAgo, hoursAgo);
        }
    }

    /**
     * Here we add click listeners to all the icons that we want to be clickable. In our click
     * listeners we forward the clicks to the PostCallbacks which represents all the possible
     * actions that a user can take on this view.
     */
    private void setUpClickListeners() {
        heartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentCallback != null && currentPost != null)
                    currentCallback.onHeartClicked(currentPost);
            }
        });

        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(currentCallback != null && currentPost != null)
                    currentCallback.onHeartClicked(currentPost);

                return true;
            }
        });

        sendIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentCallback != null && currentPost != null)
                    currentCallback.onSendClicked(currentPost);
            }
        });

        bookmarkIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentCallback != null && currentPost != null)
                    currentCallback.onBookmarkClicked(currentPost);
            }
        });

        commentIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentCallback != null && currentPost != null)
                    currentCallback.onCommentClicked(currentPost);
            }
        });

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentCallback != null && currentPost != null)
                    currentCallback.onMenuClicked(currentPost);
            }
        });
    }

    /**
     * Callbacks are basically a list of the actions that a user can perform on this view. When the
     * user performs one of those actions the callbacks class will fire off an event and the app can
     * do something.
     *
     * Here we also return the current viewholder because allows us to chain the setCallback method
     * together with the view inflation.
     *
     * @param callbacks callbacks
     * @return current viewholder
     */
    public PostViewHolder setCallback(@Nullable PostCallback callbacks) {
        currentCallback = callbacks;
        return this;
    }

    /**
     * A callback with all the possible actions that can be done on this view
     */
    public interface PostCallback {

        void onHeartClicked(@NonNull Post post);

        void onSendClicked(@NonNull Post post);

        void onCommentClicked(@NonNull Post post);

        void onBookmarkClicked(@NonNull Post post);

        void onMenuClicked(@NonNull Post post);

    }

}
