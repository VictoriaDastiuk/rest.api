package com.myNotes.rest.api.controller;

import com.myNotes.rest.api.model.AuthDto;
import com.myNotes.rest.api.model.Note;
import com.myNotes.rest.api.model.NoteDto;
import com.myNotes.rest.api.model.Profile;
import com.myNotes.rest.api.services.Auth;
import com.myNotes.rest.api.services.NotesController;
import com.myNotes.rest.api.services.ProfilesController;
import netscape.javascript.JSObject;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.*;

@Controller
@RestController
public class ModelOfProject {
    String result;
    @Autowired
    NotesController notesControl;
    @Autowired
    ProfilesController profilesContr;
    @Autowired
    Auth authControl;

    @RequestMapping(value = "/api/auth", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String Auth(@RequestBody AuthDto authDto) throws IOException, ClassNotFoundException {
        if (authDto.getEmail().isEmpty() || authDto.getName().isEmpty()) {
            return "Message: error. Missing parametr";
        } else {
            result = authControl.startAuth(authDto.getEmail(), authDto.getName());
            return result;
        }
    }

    @RequestMapping(value = "/api/newNote", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String newNote(@RequestBody NoteDto noteDto) throws IOException, ClassNotFoundException, JSONException {
        if (noteDto.getEmail().isEmpty() || noteDto.getNameNote().isEmpty()) {
            return "Message: error. Missing parametr for email or name fo Note";
        } else {
            result = notesControl.makeNote(noteDto.getNameNote(), noteDto.getTitle(), noteDto.getText(), noteDto.getEmail());
            return result;
        }
    }

    @RequestMapping(value = "/api/changeNote", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String changeNote(@RequestBody NoteDto noteDto) throws IOException, ClassNotFoundException, JSONException {
        if (noteDto.getEmail().isEmpty() || noteDto.getValueParamFind().isEmpty() || noteDto.getHowFind().isEmpty()) {
            return "Message: error. Missing parametr for email or name fo Note";
        } else {
            return notesControl.WantChangeNote(noteDto.getEmail(), noteDto.getNameNote(), noteDto.getTitle(), noteDto.getText(), noteDto.getHowFind(), noteDto.getValueParamFind());
        }
    }

    @RequestMapping(value = "/api/showAllNotes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Note> showAllNotes(@RequestBody NoteDto noteDto) throws IOException, ClassNotFoundException {
        return notesControl.ShowNoteList(noteDto.getEmail());
    }

//    @RequestMapping(value = "/api/showNote", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public String showNote(@RequestBody NoteDto noteDto) throws IOException, ClassNotFoundException {
//        if (noteDto.getEmail().isEmpty() || noteDto.getValueParamFind().isEmpty() || noteDto.getHowFind().isEmpty()) {
//            return "Message: error. Missing parametr for email or name fo Note";
//        } else {
//            return (notesControl.WantShowNote(noteDto.getEmail(), noteDto.getHowFind(), noteDto.getValueParamFind()));
//        }
//    }

    @RequestMapping(value = "/api/delNote", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String delNote(@RequestBody NoteDto noteDto) throws IOException, ClassNotFoundException, JSONException {
        if (noteDto.getEmail().isEmpty() || noteDto.getValueParamFind().isEmpty() || noteDto.getHowFind().isEmpty()) {
            return "Message: error. Missing parametr for email or name fo Note";
        } else {
            return notesControl.deleteNote(noteDto.getHowFind(), noteDto.getValueParamFind(), noteDto.getEmail());
        }
    }

    @RequestMapping(value = "/api/showAllProfiles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Profile> showAllProfiles() throws IOException, ClassNotFoundException {
        return profilesContr.ShowProfileList();
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> handleAuthenticationError() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized.");
    }
}


