package com.upvisionmedia.delivmap10.model;

public class User {
    private final int profileImage;
    private final String firstName;
    private final String lastName;
    private final String email;

    public User(int profileImage, String firstName, String lastName, String email) {
        this.profileImage = profileImage;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
