package com.myNotes.rest.api;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProfileList implements Serializable {
    @Getter
    @Setter
    public List<Profile> ProfileList = new ArrayList<>();

    private static ProfileList instance;
    ProfileList() {
    }
    public static ProfileList getInstance() {
        if (instance == null) {
            instance = new ProfileList();
        }
        return instance;
    }
}
