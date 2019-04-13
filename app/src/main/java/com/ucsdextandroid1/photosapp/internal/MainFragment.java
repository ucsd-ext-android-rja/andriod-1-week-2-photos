package com.ucsdextandroid1.photosapp.internal;

import android.content.res.ColorStateList;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ucsdextandroid1.photosapp.R;
import com.ucsdextandroid1.photosapp.profile.ProfileFragment;

import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by rjaylward on 4/8/19
 */
public class MainFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        BottomNavigationView navigation = rootView.findViewById(R.id.fm_navigation);
        ColorStateList navColorStateList = ContextCompat
                .getColorStateList(requireContext(), R.color.navigation_tint_list);
        navigation.setItemIconTintList(navColorStateList);
        navigation.setItemTextColor(navColorStateList);
        navigation.setOnNavigationItemSelectedListener(this::onNavigationItemClicked);

        getChildFragmentManager().beginTransaction()
                .replace(R.id.fm_frame_layout, new ProfileFragment())
                .commitAllowingStateLoss();

        return rootView;
    }

    private boolean onNavigationItemClicked(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.navigation_home:
                return true;
            case R.id.navigation_search:
                return true;
            case R.id.navigation_add_photo:
                return true;
            case R.id.navigation_notifications:
                return true;
            case R.id.navigation_profile:
                return true;
            default:
                return false;
        }
    }

}
