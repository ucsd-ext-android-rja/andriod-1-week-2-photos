package com.ucsdextandroid1.photosapp.data;

/**
 * Created by rjaylward on 4/12/19
 */
public class Post {

    private int id;
    private long timestamp;
    private String username;
    private String profileImageUrl;
    private String locationName;
    private String imageUrl;
    private String caption;
    private boolean isLikedByUser;

    public Post(int id,
                long timestamp,
                String username,
                String profileImageUrl,
                String locationName,
                String imageUrl,
                String caption,
                boolean isLikedByUser) {

        this.id = id;
        this.timestamp = timestamp;
        this.username = username;
        this.profileImageUrl = profileImageUrl;
        this.locationName = locationName;
        this.imageUrl = imageUrl;
        this.caption = caption;
        this.isLikedByUser = isLikedByUser;
    }

    public int getId() {
        return id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getUsername() {
        return username;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getLocationName() {
        return locationName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCaption() {
        return caption;
    }

    public boolean isLikedByUser() {
        return isLikedByUser;
    }

}
