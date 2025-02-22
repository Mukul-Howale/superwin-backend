package com.superwin.profileservice.controller;

import com.superwin.profileservice.model.Profile;
import com.superwin.profileservice.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("profile")
@AllArgsConstructor
public class ProfileController {

    private ProfileService profileService;

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getById(@PathVariable UUID id){
        return profileService.getById(id);
    }
}
