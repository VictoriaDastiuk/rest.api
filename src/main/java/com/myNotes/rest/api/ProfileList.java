package com.myNotes.rest.api;

import java.util.ArrayList;
import java.util.List;

public class ProfileList {
    public static List<Profile> Profiles = new ArrayList<>();

    public static List<Profile> getProfileList() {
        return Profiles;
    }

    public static void setProfiles(List<Profile> profiles) {
        Profiles = profiles;
    }


}
