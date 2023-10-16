package com.myNotes.rest.api;

import java.util.List;

public class ProfilesController {

    public boolean checkEmail(String email) {
        return ProfileList.getProfileList().stream().anyMatch(profile -> profile.getEmail().equals(email));
    }

    public int createProfile(){
        Profile person = new Profile();
        ProfilesController.addProfile(person);
    return person.getUserID();
    }

    public static void addProfile(Object Profile) {
        List<Profile> updatedProfiles = ProfileList.getProfileList();
        updatedProfiles.add((Profile) Profile);
        ProfileList.setProfiles(updatedProfiles);
    }

    public static void changeProfile(int UserId, String Name, String email){
        for (Profile pr : ProfileList.getProfileList()){
            if (UserId== pr.getUserID())
            {
                pr.setName(Name);
                pr.setEmail(email);
                WrittingForClient.printProfileMade();
                break;
            }
        }
       WrittingForClient.printProfileDontMade();
    }


}
