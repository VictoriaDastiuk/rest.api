package com.myNotes.rest.api;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.PrivateKey;
import java.util.*;

public class Note {
    String nameNote;
    String titleNote;
    String textNote;

    String addDate;
    String modifyDate;
    private String statusNote;
    private UUID id;

    int userID;

    public String getNameNote() {
        return nameNote;
    }

    public void setNameNote(String nameNote) {
        this.nameNote = nameNote;
    }
    public String getTitleNote() {
        return titleNote;
    }

    public void setTitleNote(String titleNote) {
        this.titleNote = titleNote;
    }

    public String getTextNote() {
        return textNote;
    }

    public void setTextNote(String textNote) {
        this.textNote = textNote;
    }

    public String getStatusNote() {
        return statusNote;
    }

    public void setStatusNote(String statusNote) {
        this.statusNote = statusNote;
    }

    public String getAddDate() {
        return addDate;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }
    public UUID getId() {
        return id;
    }
    public Note(int userID) {
        this.statusNote = "Created";
        this.id = UUID.randomUUID();
        this.userID = userID;
    }


}