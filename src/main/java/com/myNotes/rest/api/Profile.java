package com.myNotes.rest.api;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Profile {
    String Name;
    String email;
    static int userID;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getUserID() {
        return userID;
    }

    public Profile(){
        userID = ProfileList.getProfileList().size()+1;
    }

   }
