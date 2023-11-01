package com.myNotes.rest.api.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.PrivateKey;
import java.util.*;
@Getter
@Setter
@Component
public class Note implements Serializable {

   private String nameNote;

    private String titleNote;

    private String textNote;

    private String addDate;

    private String modifyDate;

    private String statusNote;
    private String id;

    int userID;

    public Note() {

    }
}