package com.myNotes.rest.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ProfilesController {
    private static ProfilesController instance;

    ProfilesController() {
    }
    public static ProfilesController getInstance() {
        if (instance == null) {
            instance = new ProfilesController();
        }
        return instance;
    }
    public boolean checkEmail(String email) {
        return ProfileList.getProfileList().stream().anyMatch(profile -> profile.getEmail().equals(email));
    }

    public int createProfile() {
        Profile person = new Profile();
        ProfilesController.addProfile(person);
        return person.getUserID();
    }

    public static void addProfile(Object Profile) {
        List<Profile> updatedProfiles = ProfileList.getProfileList();
        updatedProfiles.add((Profile) Profile);
        ProfileList.setProfiles(updatedProfiles);
    }

    public void changeProfile(int UserId, String Name, String email) {
        for (Profile pr : ProfileList.getProfileList()) {
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
        for (Profile pr : ProfileList.getProfileList()) {
            if (Objects.equals(email, pr.getEmail())) {
                return pr.getUserID();
            }
        }
        return 0;
    }
}
