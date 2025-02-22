package com.superwin.profileservice.service;

import com.superwin.profileservice.model.Profile;
import com.superwin.profileservice.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProfileService {

    private ProfileRepository profileRepository;

    public ResponseEntity<Profile> getById(UUID id){
        try {
            Optional<Profile> profile = profileRepository.findById(id);
            return profile.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}
