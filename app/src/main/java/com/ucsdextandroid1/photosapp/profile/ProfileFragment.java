package com.ucsdextandroid1.photosapp.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ucsdextandroid1.photosapp.R;
import com.ucsdextandroid1.photosapp.data.Post;
import com.ucsdextandroid1.photosapp.data.Profile;
import com.ucsdextandroid1.photosapp.internal.DataSource;
import com.ucsdextandroid1.photosapp.internal.DataSourceCallback;

import java.util.List;
import java.util.Objects;

/**
 * Created by rjaylward on 4/12/19
 */
public class ProfileFragment extends Fragment {

    private FeedAdapter feedAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        feedAdapter = new FeedAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.fp_recycler_view);
//      recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){
            public int getSpanSize(int position){
                return feedAdapter.getSpanSize(position);
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        feedAdapter.setGridMode(true);
        recyclerView.setAdapter(feedAdapter);

        String username = "user-1";

        DataSource.getInstance().fetchPosts(username, new DataSourceCallback<List<Post>>() {
            @Override
            public void onDataFetched(List<Post> posts) {
                feedAdapter.setPosts(posts);
            }
        });

        DataSource.getInstance().fetchProfile(username, new DataSourceCallback<Profile>() {
            @Override
            public void onDataFetched(Profile profile) {
                feedAdapter.setProfile(profile);

            }
        });

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.profile_fragment_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.pf_menu_history:
            case R.id.pf_menu_menu:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
