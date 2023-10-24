package com.myNotes.rest.api;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class Auth {
    static boolean checkEmail;
    String result;
    private static Auth instance;

    private Auth() {
    }
    public static Auth getInstance() {
        if (instance == null) {
            instance = new Auth();
        }
        return instance;
    }

    ProfilesController profilesContr = ProfilesController.getInstance();
    ProfileList ProfileListInst = ProfileList.getInstance();

    public String startAuth(String mail, String user) throws IOException, ClassNotFoundException {

        List<Profile> profiles = FilesNotes.returnProfilesFromFile(mail,user);
        ProfileListInst.setProfileList(profiles);
        String nullProfiles = profilesContr.findInProfileList(mail, user);
        if (nullProfiles.equals("new")){
            // Cтворення користувача
            int userId = profilesContr.createProfile();
//            Profile newProfile = profilesContr.changeProfile(userId, user, mail);
            FilesNotes.AddProfileInFile();
            FilesNotes.NewNotesFile(userId);

            result = "Авторизація успішна";
        }

        return result;
    }



}
