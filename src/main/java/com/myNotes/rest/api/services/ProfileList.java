package com.myNotes.rest.api.services;

import com.myNotes.rest.api.model.Profile;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
@Getter
@Setter
public class ProfileList implements Serializable {

//        private static ProfileList instance;

    private List<Profile> profileList;

    private ProfileList() {
        profileList = new ArrayList<>();
    }

    public void addProfile(Profile Profile) {
        profileList.add(Profile);
    }

    @PostConstruct
    private void addToListProfilesFromFile() throws IOException {
        String NameFile = "Profiles.txt";
        String[] listProfiles = FilesNotes.FindAndReturnInfoFromFile(NameFile);
        if (listProfiles != null) {
            // Створити список об'єктів
            // Перетворити інформацію з файлу на об'єкти
            for (String line : listProfiles) {
                // Розбити рядок на поля
                String[] fields = line.split(" ", 3);

                // Створити новий об'єкт
                Profile profile = new Profile();
                profile.setName(fields[0]);
                profile.setEmail(fields[1]);
                profile.setUserID(Integer.parseInt(fields[2]));

                // Додати новий об'єкт до списку
                addProfile(profile);
            }
        }

    }

}
