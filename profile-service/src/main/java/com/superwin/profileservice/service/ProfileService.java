package com.superwin.profileservice.service;

import com.superwin.profileservice.dto.ProfileDTO;
import com.superwin.profileservice.model.Profile;
import com.superwin.profileservice.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProfileService {

    private ProfileRepository profileRepository;
    private ModelMapper mapper;

    public ResponseEntity<ProfileDTO> getById(UUID id){
        try {
            Optional<Profile> profile = profileRepository.findById(id);
            if (profile.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(mapper.map(profile, ProfileDTO.class), HttpStatus.OK);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}
