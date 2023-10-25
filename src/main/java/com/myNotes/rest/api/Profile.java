package com.myNotes.rest.api;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.io.ObjectOutputStream;


@Getter
@Setter
public class Profile implements Serializable {

    String Name;

    String email;
    int userID;

    ProfileList ProfileListInst = ProfileList.getInstance();

    public Profile(){
        userID = ProfileListInst.getProfileList().size()+1;
    }

   }
