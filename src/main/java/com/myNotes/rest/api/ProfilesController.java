package com.myNotes.rest.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ProfilesController {
    private static ProfilesController instance;
    ProfileList ProfileListInst = ProfileList.getInstance();


    ProfilesController() {
    }
    public static ProfilesController getInstance() {
        if (instance == null) {
            instance = new ProfilesController();
        }
        return instance;
    }
    public boolean checkEmail(String email) {
        return ProfileListInst.getProfileList().stream().anyMatch(profile -> profile.getEmail().equals(email));
    }

    public int createProfile() {
        Profile person = new Profile();
        getInstance().addProfile(person);
        return person.getUserID();
    }

    public void addProfile(Object Profile) {
        List<Profile> updatedProfiles = ProfileListInst.getProfileList();
        updatedProfiles.add((Profile) Profile);
        ProfileListInst.setProfileList(updatedProfiles);
    }

    public void changeProfile(int UserId, String Name, String email) {
        for (Profile pr : ProfileListInst.getProfileList()) {
            if (UserId == pr.getUserID()) {
                pr.setName(Name);
                pr.setEmail(email);
                WrittingForClient.printProfileMade();
                break;
            }
        }
        WrittingForClient.printProfileDontMade();
    }

    public int findInProfileList(String email) {
        for (Profile pr : ProfileListInst.getProfileList()) {
            if (Objects.equals(email, pr.getEmail())) {
                return pr.getUserID();
            }
        }
        return 0;
    }
}
