package com.myNotes.rest.api.services;

import com.myNotes.rest.api.model.Profile;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
public class ProfilesController {
    @Autowired
    ProfileList ProfileListInst;

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
                    return "ok";
                } else {
                    return "error";
                }
            } else {
                return "new";
            }
        }
        return null;
    }

    public String getUserIDFromList(String email) {
        for (Profile pr : ProfileListInst.getProfileList()) {
            if (Objects.equals(email, pr.getEmail())) {
                return String.valueOf(pr.getUserID());
            }
        }
        return "error";
    }

    public String checkEmailInProfilesList(String email) throws IOException, ClassNotFoundException {
        if (!ProfileListInst.getProfileList().isEmpty()) {
            String userIDSring = getUserIDFromList(email);

            if (userIDSring.equals("error")) {
                return "Can`t find profile";
            }
        } else {
            return "Can`t find profile";
        }
        return "ok";
    }

    public JSONObject ShowProfileList() throws JSONException {
        JSONObject answ = new JSONObject();
        if (!ProfileListInst.getProfileList().isEmpty()) {
            answ.put("Message:", "Don`t` have profiles");
            return answ;
        } else {

            JSONArray Array = new JSONArray();
            Array.put(ProfileListInst.getProfileList());
            answ.put("profiles",Array);

            return answ;
        }
    }
}

