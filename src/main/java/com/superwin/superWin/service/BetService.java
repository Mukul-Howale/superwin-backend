package com.superwin.superWin.service;

import com.superwin.superWin.dto.BetRequestDTO;
import com.superwin.superWin.exception.ProfileNotFoundException;
import com.superwin.superWin.exception.SessionNotFoundException;
import com.superwin.superWin.model.Profile;
import com.superwin.superWin.model.Session;
import com.superwin.superWin.repository.BetRepository;
import com.superwin.superWin.repository.ProfileRepository;
import com.superwin.superWin.repository.SessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BetService {

    private BetRepository betRepository;
    private ProfileRepository profileRepository;
    private SessionRepository sessionRepository;

    public Boolean bet(BetRequestDTO betRequestDTO){
        try {
            Profile profile = profileRepository.findById(betRequestDTO.profileId())
                    .orElseThrow(() -> new ProfileNotFoundException("Profile not found"));

            Session session = sessionRepository.findById(betRequestDTO.sessionId())
                    .orElseThrow(() -> new SessionNotFoundException("Session not found"));

            return true;

        } catch (Exception e){
            throw new RuntimeException();
        }
    }
}
