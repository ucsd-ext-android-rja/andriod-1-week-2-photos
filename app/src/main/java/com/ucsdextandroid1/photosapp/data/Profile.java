package com.ucsdextandroid1.photosapp.data;

/**
 * Created by rjaylward on 4/13/19
 */
public class Profile {

    private String fullName;
    private String username;
    private String profileImageUrl;

    private int postCount;
    private int followersCount;
    private int followingCount;

    public Profile(String fullName, String username, String profileImageUrl,
                   int postCount, int followersCount, int followingCount) {
        this.fullName = fullName;
        this.username = username;
        this.profileImageUrl = profileImageUrl;
        this.postCount = postCount;
        this.followersCount = followersCount;
        this.followingCount = followingCount;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public int getPostCount() {
        return postCount;
    }

    public int getFollowingCount() {
        return followingCount;
    }
}
