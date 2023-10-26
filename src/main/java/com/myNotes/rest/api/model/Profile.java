package com.myNotes.rest.api.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.io.ObjectOutputStream;


@Getter
@Setter
@Component
public class Profile implements Serializable {

    String Name;

    String email;
    int userID;



    public Profile(){
    }

   }
