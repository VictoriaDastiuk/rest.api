package com.myNotes.rest.api.services;

import com.myNotes.rest.api.model.Profile;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service

public class Auth {

    @Autowired
    ProfilesController profilesContr;
    @Autowired
    FilesNotes filesNotes;
    @Autowired
    ProfileList ProfileListInst;

    public String startAuth(String mail, String user) throws IOException {
        String result = null;
        if (!ProfileListInst.getProfileList().isEmpty()) {
            String nullProfiles = profilesContr.findInProfileList(mail, user);
            if (nullProfiles.equals("new")) {
                // Cтворення користувача
                Profile profile = profilesContr.createProfile();
                profilesContr.addProfile(profile);
                profilesContr.changeProfile(profile.getUserID(), user, mail);
                List<Profile> newProfiles = ProfileListInst.getProfileList();

                filesNotes.AddProfileInFile(newProfiles);

                result = "Авторизація успішна";
                return result;
            } else {
                if (nullProfiles.equals("ok")) {
                    result = "Авторизація успішна";
                    return result;
                }
                if (nullProfiles.equals("error")) {
                    result = "Авторизація не успішна";
                    return result;
                }
            }
        } else {
            // Cтворення користувача
            Profile profile = profilesContr.createProfile();
            profilesContr.addProfile(profile);
            profile = profilesContr.changeProfile(profile.getUserID(), user, mail);

            filesNotes.AddProfileInFile(ProfileListInst.getProfileList());

            result = "Авторизація успішна";
            return result;
        }
        return result;
    }
}
