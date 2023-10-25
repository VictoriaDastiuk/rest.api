package com.myNotes.rest.api.model;

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



    public Profile(){
    }

   }
