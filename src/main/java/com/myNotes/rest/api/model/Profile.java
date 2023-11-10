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

public class Profile {

    String Name;

    String email;
    int userID;
}
