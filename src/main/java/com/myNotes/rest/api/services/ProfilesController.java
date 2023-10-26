package com.myNotes.rest.api.services;

import com.myNotes.rest.api.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProfilesController {
    String result;
    @Autowired
    ProfileList ProfileListInst;


//    public boolean checkEmail(String email) {
//        return ProfileListInst.getProfileList().stream().anyMatch(profile -> profile.getEmail().equals(email));
//    }

    public Profile createProfile() {
        Profile person = new Profile();
        return person;
    }

    public void addProfile(Profile Profile) {
        Profile.setUserID(ProfileListInst.getProfileList().size());
        ProfileListInst.addProfile(Profile);
    }

    public Profile changeProfile(int UserId, String Name, String email) {
        for (Profile pr : ProfileListInst.getProfileList()) {
            if (UserId == pr.getUserID()) {
                pr.setName(Name);
                pr.setEmail(email);
                return pr;
            }
        }
        return null;
    }

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

    public String getUserIDFromList(String email) {
        for (Profile pr : ProfileListInst.getProfileList()) {
            if (Objects.equals(email, pr.getEmail())) {
                return String.valueOf(pr.getUserID());
            }
        }
        return "error";
    }
public String checkEmailInProfilesList (String email) throws IOException, ClassNotFoundException {
            ;
    if (ShowProfileList().isEmpty()) {
        return "Can`t find profile";
    } else {
        String userIDSring = getUserIDFromList(email);
        if (userIDSring.equals("error")) {
            return "Can`t find profile";
        }
    }
    return "ok";
    }
    public List<Profile> ShowProfileList() throws IOException, ClassNotFoundException {

            try {
                List<Profile> profiles = FilesNotes.getInfoProfilesFile();
                if (profiles!= null) {
                    ProfileListInst.setProfileList(profiles);
                }
                    return ProfileListInst.getProfileList();
            }
            catch (IOException e) {
                return null;
            }
        }}

