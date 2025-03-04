package com.superwin.referralservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.superwin.referralservice.dto.AddReferralRequest;
import com.superwin.referralservice.model.Referral;
import com.superwin.referralservice.repository.ReferralRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ReferralService {

    private ReferralRepository referralRepository;

    // Utility methods to handle JSON serialization
    private static final ObjectMapper objectMapper = new ObjectMapper();


    public Boolean addReferral(AddReferralRequest addReferralRequest){
        try{
            Referral referral = referralRepository.findById(addReferralRequest.referredTo())
                    .orElseThrow(() -> new RuntimeException("Referral not found"));

            String levelColumn = "level_" + level;  // Determine which level to update

            // Get existing referrals
            List<UUID> referralList = getLevelAsList(levelColumn);

            // Append new referral if not already present
            if (!referralList.contains(newReferralId)) {
                referralList.add(newReferralId);
            }

            // Update the JSON field
            referral.setLevelAsList(levelColumn, referralList);

            // Save updated referral entity
            referralRepository.save(referral);

            return true;
        }catch (Exception e) {
            throw new RuntimeException(e);
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
