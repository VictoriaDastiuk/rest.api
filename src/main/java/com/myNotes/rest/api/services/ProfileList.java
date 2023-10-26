package com.myNotes.rest.api.services;

import com.myNotes.rest.api.model.Profile;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Service
public class ProfileList implements Serializable {

//        private static ProfileList instance;

        private List<Profile> profileList;

        private ProfileList() {
            profileList = new ArrayList<>();
        }

//        public static ProfileList getInstance() {
//            if (instance == null) {
//                instance = new ProfileList();
//            }
//            return instance;
//        }

        public List<Profile> getProfileList() {
            return profileList;
        }

        public void setProfileList(List<Profile> profileList) {
            this.profileList = profileList;
        }

        public void addProfile(Profile Profile) {
            profileList.add(Profile);
        }
    }
