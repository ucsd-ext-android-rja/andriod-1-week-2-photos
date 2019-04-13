package com.ucsdextandroid1.photosapp.profile;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ucsdextandroid1.photosapp.data.Post;
import com.ucsdextandroid1.photosapp.data.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rjaylward on 4/12/19
 */
public class FeedAdapter extends RecyclerView.Adapter  {

    private List<FeedAdapterItem> items = new ArrayList<>();
    private List<Post> currentPosts;
    private Profile currentProfile;
    private boolean isGridMode = false;

    public void setPosts(List<Post> posts) {
        currentPosts = posts;
        setItems(currentPosts, currentProfile);
//        items.clear();
//        items.addAll(posts);
//        notifyDataSetChanged();
    }
    public void setGridMode(boolean isGridMode){
        this.isGridMode = isGridMode;
        notifyDataSetChanged();
    }
    public void setItems(List<Post> posts, @Nullable Profile profile) {
        items.clear();
        if(profile != null)
            items.add(new FeedAdapterItem(profile));

        for(Post post : posts) {
            items.add(new FeedAdapterItem(post));
        }

        notifyDataSetChanged();
    }
    public void setProfile(Profile profile) {
        currentProfile = profile;
        setItems(currentPosts, currentProfile);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch(viewType){
            case FeedAdapterItem.TYPE_POST:
                return PostViewHolder.inflate(viewGroup);
            case FeedAdapterItem.TYPE_PROFILE:
                return ProfileViewHolder.inflate(viewGroup);
            default :
                throw new IllegalArgumentException("Undefined argument");
        }
     //   return PostViewHolder.inflate(viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if(viewHolder instanceof PostViewHolder)
            ((PostViewHolder) viewHolder).bindPost(getPost(position),isGridMode);
        else if(viewHolder instanceof ProfileViewHolder)
            ((ProfileViewHolder) viewHolder).bind(getProfile(position));
    }

    private Post getPost(int index) {
        return items.get(index).getPost();
    }
    private Profile getProfile(int index) {
        return items.get(index).getProfile();
    }
    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }
    public int getSpanSize(int position){
        switch(getItemViewType(position)){
            case FeedAdapterItem.TYPE_POST:
                return 1;
            case FeedAdapterItem.TYPE_PROFILE:
                return 3;
        }
        return 0;
    }

    private static class FeedAdapterItem {

        public static final int TYPE_POST = 1;
        public static final int TYPE_PROFILE = 2;

        @Nullable private final Post post;
        @Nullable private final Profile profile;
        final private int type;

        public FeedAdapterItem(@Nullable Post post) {
            this.post = post;
            this.profile = null;
            this.type = TYPE_POST;
        }

        public FeedAdapterItem(@NonNull Profile profile) {
            this.profile = profile;
            this.post = null;
            this.type = TYPE_PROFILE;
        }

        @Nullable
        public Profile getProfile() {
            return profile;
        }

        @Nullable
        public Post getPost() {
            return post;
        }

        public int getType() {
            return type;
        }

    }

}
