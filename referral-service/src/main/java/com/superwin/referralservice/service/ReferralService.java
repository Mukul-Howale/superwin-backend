package com.superwin.referralservice.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.superwin.referralservice.client.ProfileClient;
import com.superwin.referralservice.dto.AddReferralRequestDTO;
import com.superwin.referralservice.dto.ProfileFilterDTO;
import com.superwin.referralservice.exception.GeneralException;
import com.superwin.referralservice.exception.NoReferralFoundException;
import com.superwin.referralservice.model.Referral;
import com.superwin.referralservice.repository.ReferralRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ReferralService {

    private ReferralRepository referralRepository;
    private ProfileClient profileClient;

    // Utility methods to handle JSON serialization
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public Boolean addReferral(AddReferralRequestDTO addReferralRequestDTO){
        try{
            ResponseEntity<ProfileFilterDTO> profileFilterDTOResponseEntity = profileClient.getByReferralCode(addReferralRequestDTO.referredCode());
            ProfileFilterDTO profileFilterDTO = profileFilterDTOResponseEntity.getBody();

            Referral referral = referralRepository.findById(profileFilterDTO.profileId())
                    .orElseThrow(() -> new NoReferralFoundException("Referral not found"));

            List<UUID> referralList = getLevelAsList(referral.getLevel1());

            // Append new referral if not already present
            if (!referralList.contains(addReferralRequestDTO.referredTo())) {
                referralList.add(addReferralRequestDTO.referredTo());
            }

            // Update the JSON field
            setLevelAsList(referral.getLevel1(), referralList);

            // Save updated referral entity
            referralRepository.save(referral);

            return true;
        }catch (Exception e) {
            throw new GeneralException("Something went wrong!!", e);
        }
    }

    private List<UUID> getLevelAsList(String level) throws Exception {
        String jsonData = (String) Referral.class.getDeclaredField(level).get(this);
        return jsonData != null ? objectMapper.readValue(jsonData, new TypeReference<List<UUID>>() {}) : List.of();
    }

    private void setLevelAsList(String level, List<UUID> referrals) throws Exception {
        String json = objectMapper.writeValueAsString(referrals);
        Referral.class.getDeclaredField(level).set(this, json);
    }
}
