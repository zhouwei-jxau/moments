package com.example.zhouwei.comments;

/**
 * Created by zhouwei on 2018/12/4.
 */

public class CommitItem {

    private String username;
    private String text;
    private String[] imageUri;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getImageUri() {
        return imageUri;
    }

    public void setImageUri(String[] imageUri) {
        this.imageUri = imageUri;
    }
}
