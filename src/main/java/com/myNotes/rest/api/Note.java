package com.myNotes.rest.api;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.PrivateKey;
import java.util.*;
@Getter
@Setter
public class Note {

   private String nameNote;

    private String titleNote;

    private String textNote;

    private String addDate;

    private String modifyDate;

    private String statusNote;
    @Getter
    private UUID id;

    int userID;

    public Note(int userID) {
        this.statusNote = "Created";
        this.id = UUID.randomUUID();
        this.userID = userID;
    }


}