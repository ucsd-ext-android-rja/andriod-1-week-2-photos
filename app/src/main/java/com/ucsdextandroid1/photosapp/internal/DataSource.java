package com.ucsdextandroid1.photosapp.internal;

import com.ucsdextandroid1.photosapp.data.Post;
import com.ucsdextandroid1.photosapp.data.Profile;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by rjaylward on 4/13/19
 */
public class DataSource {

    private static DataSource instance;

    public static DataSource getInstance() {
        if(instance == null)
            instance = new DataSource();

        return instance;
    }

    public void fetchPosts(String username, DataSourceCallback<List<Post>> callback) {
        callback.onDataFetched(Arrays.asList(
                new Post(
                        1,
                        System.currentTimeMillis() - TimeUnit.HOURS.toMillis(1),
                        username,
                        "https://ucsd-ext-android-rja-1.firebaseapp.com/images/orange_profile.png",
                        "Space",
                        "https://ucsd-ext-android-rja-1.firebaseapp.com/images/space_1.jpg",
                        "space is neat",
                        false
                ),new Post(
                        2,
                        System.currentTimeMillis() - TimeUnit.DAYS.toMillis(2),
                        username,
                        "https://ucsd-ext-android-rja-1.firebaseapp.com/images/orange_profile.png",
                        "Space",
                        "https://ucsd-ext-android-rja-1.firebaseapp.com/images/space_2.jpg",
                        "space is cool,",
                        false
                ),
                new Post(
                        3,
                        System.currentTimeMillis() - TimeUnit.DAYS.toMillis(2),
                        username,
                        "https://ucsd-ext-android-rja-1.firebaseapp.com/images/orange_profile.png",
                        "Space",
                        "https://ucsd-ext-android-rja-1.firebaseapp.com/images/space_3.jpg",
                        "space is cool,",
                        false
                ),
                new Post(
                        4,
                        System.currentTimeMillis() - TimeUnit.DAYS.toMillis(2),
                        username,
                        "https://ucsd-ext-android-rja-1.firebaseapp.com/images/orange_profile.png",
                        "Space",
                        "https://ucsd-ext-android-rja-1.firebaseapp.com/images/space_4.jpg",
                        "space is cool,",
                        false
                ),
                new Post(
                        5,
                        System.currentTimeMillis() - TimeUnit.DAYS.toMillis(2),
                        username,
                        "https://ucsd-ext-android-rja-1.firebaseapp.com/images/orange_profile.png",
                        "Space",
                        "https://ucsd-ext-android-rja-1.firebaseapp.com/images/space_5.jpg",
                        "space is cool,",
                        false
                ),
                new Post(
                        6,
                        System.currentTimeMillis() - TimeUnit.DAYS.toMillis(2),
                        username,
                        "https://ucsd-ext-android-rja-1.firebaseapp.com/images/orange_profile.png",
                        "Space",
                        "https://ucsd-ext-android-rja-1.firebaseapp.com/images/space_6.jpg",
                        "space is cool,",
                        false
                ),
                new Post(
                        7,
                        System.currentTimeMillis() - TimeUnit.DAYS.toMillis(2),
                        username,
                        "https://ucsd-ext-android-rja-1.firebaseapp.com/images/orange_profile.png",
                        "Space",
                        "https://ucsd-ext-android-rja-1.firebaseapp.com/images/space_7.jpg",
                        "space is cool,",
                        false
                ),
                new Post(
                        8,
                        System.currentTimeMillis() - TimeUnit.DAYS.toMillis(2),
                        username,
                        "https://ucsd-ext-android-rja-1.firebaseapp.com/images/orange_profile.png",
                        "Space",
                        "https://ucsd-ext-android-rja-1.firebaseapp.com/images/space_8.jpg",
                        "space is cool,",
                        false
                )
        ));
    }

    public void fetchProfile(String username, DataSourceCallback<Profile> callback) {
        callback.onDataFetched(new Profile(
                username.toUpperCase(),
                username,
                "https://ucsd-ext-android-rja-1.firebaseapp.com/images/orange_profile.png",
                230,
                300,
                260

        ));
    }

}
