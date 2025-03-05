package com.superwin.referralservice.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.superwin.referralservice.dto.AddReferralRequestDTO;
import com.superwin.referralservice.exception.GeneralException;
import com.superwin.referralservice.exception.NoReferralFoundException;
import com.superwin.referralservice.model.Referral;
import com.superwin.referralservice.repository.ReferralRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ReferralService {

    private ReferralRepository referralRepository;

    // Utility methods to handle JSON serialization
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public void addReferral(AddReferralRequestDTO addReferralRequestDTO){
        // Adding levels asynchronously
        addReferralToLevels(addReferralRequestDTO.referredTo(), getReferralDetailsByReferralCode(addReferralRequestDTO.referredCode()), BigInteger.ONE);
    }

    @Async
    protected void addReferralToLevels(UUID referredTo, Referral referredBy, BigInteger level) {
        try {
            // After 5 iterations the recursion will stop
            if (level.equals(BigInteger.valueOf(6))) return;

            // Get referral list of the referredBy user based on the level
            List<UUID> referralList = getLevelAsList(referredBy.getLevelByNumber(level.intValue()));

            // Append new referral if not already present
            if (!referralList.contains(referredTo)) referralList.add(referredTo);

            // Update the JSON field
            setLevelAsList(referredBy.getLevelByNumber(level.intValue()), referralList);

            // Save updated referral entity
            referralRepository.save(referredBy);

            // In case there is no referredCode for the particular user
            if(referredBy.getReferredCode() == null) return;

            addReferralToLevels(referredTo, getReferralDetailsByReferralCode(referredBy.getReferredCode()), BigInteger.ONE.add(BigInteger.ONE));
        } catch (Exception e) {
            throw new GeneralException("Unhandled Exception: void addReferralToLevels(UUID referredTo, Referral referredBy, BigInteger level), ReferralService", e);
        }
    }

    private Referral getReferralDetailsByReferralCode(Long referralCode){
        try{
            Optional<Referral> referralDetails = referralRepository.getReferralDetailsByReferralCode(referralCode);
            if (referralDetails.isEmpty()) throw new NoReferralFoundException("Referral not found");
            return referralDetails.get();
        } catch (Exception e){
            throw new GeneralException("Unhandled Exception: Referral getReferralDetailsByReferralCode(Long referralCode), ReferralService", e);
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
