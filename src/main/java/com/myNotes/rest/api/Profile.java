package com.myNotes.rest.api;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Profile {
    @Getter
    @Setter
    String Name;
    @Getter
    @Setter
    String email;
    @Getter
    int userID;

    ProfileList ProfileListInst = ProfileList.getInstance();

    public Profile(){
        userID = ProfileListInst.getProfileList().size()+1;
    }

   }
