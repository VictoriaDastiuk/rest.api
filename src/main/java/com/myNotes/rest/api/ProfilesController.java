package com.myNotes.rest.api;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ProfilesController {
    String result;
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
        addProfile(person);
        return person.getUserID();
    }

    public void addProfile(Profile Profile) {
        List<Profile> updatedProfiles = ProfileListInst.getProfileList();
        updatedProfiles.add(Profile);
        ProfileListInst.setProfileList(updatedProfiles);
    }

    public void changeProfile(int UserId, String Name, String email) {
        for (Profile pr : ProfileListInst.getProfileList()) {
            if (UserId == pr.getUserID()) {
                pr.setName(Name);
                pr.setEmail(email);
                break;
            }
        }
    }

//    public Profile findProfinProfileList(String email, String name) {
//        ProfileListInst.setProfileList(profileList);
//        List<Note> infoFromFile = FilesNotes.returnNotesFromFile();
//        for (Profile pr : ProfileListInst.getProfileList()) {
//            if (Objects.equals(email, pr.getEmail())) {
//                return pr;
//            }
//        }
//        return null;
//    }

    public String findInProfileList(String email, String name) {

        for (Profile pr : ProfileListInst.getProfileList()) {
            if (Objects.equals(email, pr.getEmail())) {
                if (Objects.equals(name, pr.getName())) {
                    result = "ok";
                } else {
                    result = "error";
                }
            } else {
                result = "new";
            }
        }
        return result;
    }

    public int getUserIDFromList(String email) {
        for (Profile pr : ProfileListInst.getProfileList()) {
            if (Objects.equals(email, pr.getEmail())) {
                return pr.getUserID();
            }
        }
        return 0;
    }
}

