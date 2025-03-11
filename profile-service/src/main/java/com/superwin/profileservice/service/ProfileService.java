package com.superwin.profileservice.service;

import com.superwin.profileservice.dto.ProfileDTO;
import com.superwin.profileservice.dto.ProfileFilterDTO;
import com.superwin.profileservice.exception.GeneralException;
import com.superwin.profileservice.exception.NoReferralFoundException;
import com.superwin.profileservice.exception.ProfileNotFoundException;
import com.superwin.profileservice.model.MainWallet;
import com.superwin.profileservice.model.Profile;
import com.superwin.profileservice.model.ReferralWallet;
import com.superwin.profileservice.model.SavingsWallet;
import com.superwin.profileservice.repository.MainWalletRepository;
import com.superwin.profileservice.repository.ProfileRepository;
import com.superwin.profileservice.repository.ReferralWalletRepository;
import com.superwin.profileservice.repository.SavingsWalletRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final MainWalletRepository mainWalletRepository;
    private final ReferralWalletRepository referralWalletRepository;
    private final SavingsWalletRepository savingsWalletRepository;

    private final ModelMapper mapper;
    private final SecureRandom secureRandom;


    public ProfileDTO getById(UUID id){
        try {
            Optional<Profile> profile = profileRepository.findById(id);
            if (profile.isEmpty()) throw new ProfileNotFoundException("Profile not found");
            return mapper.map(profile, ProfileDTO.class);
        }catch (Exception e){
            throw new GeneralException("Unhandled Issue in: ProfileDTO getById(UUID id), ProfileService", e);
        }
    }

    public ProfileFilterDTO getProfileFilterByReferralCode(Long referralCode){
        try{
            Optional<ProfileFilterDTO> profileFilterDTO = profileRepository.getProfileFilterByReferralCode(referralCode);
            if (profileFilterDTO.isEmpty()) throw new NoReferralFoundException("No referral found for the given code");
            return profileFilterDTO.get();
        }catch (Exception e){
            throw new GeneralException("Unhandled Issue: ProfileFilterDTO getByReferredCode(Long referredCode), ProfileService", e);
        }
    }

    @Transactional
    public ProfileDTO createProfile(UUID userId, Long referredCode){
        try {
            Profile profile = Profile.builder()
                    .id(UUID.randomUUID())
                    .userId(userId)
                    .userName(generateUsername())
                    .referralCode(generateReferralCode())
                    .referredCode(referredCode)
                    .mainWallet(createMainWallet())
                    .referralWallet(createReferralWallet())
                    .savingsWallet(createSavingsWallet())
                    .build();
            profileRepository.save(profile);
            return mapper.map(profile, ProfileDTO.class);
        }catch (Exception e){
            throw new GeneralException("Unhandled Issue: ProfileDTO createProfile(UUID userId), ProfileService", e);
        }
    }

    // Generate random usernames using SecureRandom
    private String generateUsername() {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder("user_");
        for (int i = 0; i < 6; i++) {
            sb.append(CHARACTERS.charAt(secureRandom.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    // Generate random referral code using SecureRandom
    private Long generateReferralCode(){
        long min = 100_000_000L; // Minimum 9-digit number
        long max = 999_999_999_999L; // Maximum 12-digit number
        return min + (long) (secureRandom.nextDouble() * (max - min));
    }

    // Create and save MainWallet
    private MainWallet createMainWallet(){
        try{
            MainWallet mainWallet = MainWallet.builder()
                    .id(UUID.randomUUID())
                    .totalBalance(0L)
                    .mainBalance(0L)
                    .efwBalance(0L)
                    .bonus(0L)
                    .depositNo(0L)
                    .build();
            mainWalletRepository.save(mainWallet);
            return mainWallet;
        }catch (Exception e){
            throw new GeneralException("Unhandled Issue: MainWallet createMainWallet(), ProfileService", e);
        }
    }

    // Create and save ReferralWallet
    private ReferralWallet createReferralWallet(){
        try {
            ReferralWallet referralWallet = ReferralWallet.builder()
                    .id(UUID.randomUUID())
                    .build();
            referralWalletRepository.save(referralWallet);
            return referralWallet;
        } catch (Exception e) {
            throw new GeneralException("Unhandled Issue: ReferralWallet createReferralWallet(), ProfileService", e);
        }
    }

    // Create and save SavingsWallet
    private SavingsWallet createSavingsWallet(){
        try {
            SavingsWallet savingsWallet = SavingsWallet.builder()
                    .id(UUID.randomUUID())
                    .build();
            savingsWalletRepository.save(savingsWallet);
            return savingsWallet;
        } catch (Exception e) {
            throw new GeneralException("Unhandled Issue: SavingsWallet createSavingsWallet(), ProfileService", e);
        }
    }
}
