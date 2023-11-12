package com.myNotes.rest.api.services;

import com.myNotes.rest.api.model.Profile;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
@Getter
@Setter
public class ProfileList {

    private List<Profile> profileList;

    private ProfileList() {
        profileList = new ArrayList<>();
    }

    public void addProfile(Profile Profile) {
        profileList.add(Profile);
    }

}
