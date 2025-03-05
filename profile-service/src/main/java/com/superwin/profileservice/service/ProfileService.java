package com.superwin.profileservice.service;

import com.superwin.profileservice.dto.ProfileDTO;
import com.superwin.profileservice.dto.ProfileFilterDTO;
import com.superwin.profileservice.exception.GeneralException;
import com.superwin.profileservice.exception.NoReferralFoundException;
import com.superwin.profileservice.exception.ProfileNotFoundException;
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

    public ProfileDTO getById(UUID id){
        try {
            Optional<Profile> profile = profileRepository.findById(id);
            if (profile.isEmpty()) throw new ProfileNotFoundException("Profile not found");
            return mapper.map(profile, ProfileDTO.class);
        }catch (Exception e){
            throw new GeneralException("Unhandled Issue in: ProfileDTO getById(UUID id), ProfileService", e);
        }
    }

    public ProfileFilterDTO getByReferralCode(Long referralCode){
        try{
            Optional<ProfileFilterDTO> profileFilterDTO = profileRepository.getByReferralCode(referralCode);
            if (profileFilterDTO.isEmpty()) throw new NoReferralFoundException("No referral found for the given code");
            return profileFilterDTO.get();
        }catch (Exception e){
            throw new GeneralException("Unhandled Issue: ProfileFilterDTO getByReferredCode(Long referredCode), ProfileService", e);
        }
    }
}
