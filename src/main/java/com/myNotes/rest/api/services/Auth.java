package com.myNotes.rest.api.services;

import com.myNotes.rest.api.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service

public class Auth {
    String result;
    @Autowired
    ProfilesController profilesContr;
    @Autowired
    ProfileList ProfileListInst;
//ProfileList profileList = new ProfileList();

    public String startAuth(String mail, String user) throws IOException, ClassNotFoundException {

//        List<Profile> profiles = FilesNotes.getInfoProfilesFile();
//        if (profiles != null) {
//            ProfileListInst.setProfileList(profiles);
        if (!ProfileListInst.getProfileList().isEmpty()){
            String nullProfiles = profilesContr.findInProfileList(mail, user);
            if (nullProfiles.equals("new")) {
                // Cтворення користувача
                Profile profile = profilesContr.createProfile();
                profilesContr.addProfile(profile);
                profilesContr.changeProfile(profile.getUserID(), user, mail);
                List<Profile> newProfiles = ProfileListInst.getProfileList();

                FilesNotes.AddProfileInFile(newProfiles);

                result = "Авторизація успішна";
            } else {
                if (nullProfiles.equals("ok")) {
                    result = "Авторизація успішна";
                }
                if (nullProfiles.equals("error")) {
                    result = "Авторизація не успішна";
                }
            }
        } else {
            // Cтворення користувача
            Profile profile = profilesContr.createProfile();
            profilesContr.addProfile(profile);
            profile = profilesContr.changeProfile(profile.getUserID(), user, mail);

            FilesNotes.AddProfileInFile(ProfileListInst.getProfileList());

            result = "Авторизація успішна";
        }
        return result;
    }
}
