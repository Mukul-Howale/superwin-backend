package com.superwin.superWin.game.lottery.winGo.service;

import com.superwin.superWin.dto.WinGoBetRequestDTO;
import com.superwin.superWin.exception.ProfileNotFoundException;
import com.superwin.superWin.exception.SessionNotFoundException;
import com.superwin.superWin.model.Profile;
import com.superwin.superWin.model.Session;
import com.superwin.superWin.game.lottery.winGo.repository.WinGoBetRepository;
import com.superwin.superWin.repository.ProfileRepository;
import com.superwin.superWin.repository.SessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WinGoBetService {

    private WinGoBetRepository winGoBetRepository;
    private ProfileRepository profileRepository;
    private SessionRepository sessionRepository;

    public Boolean bet(WinGoBetRequestDTO winGoBetRequestDTO){
        try {
            Profile profile = profileRepository.findById(winGoBetRequestDTO.profileId())
                    .orElseThrow(() -> new ProfileNotFoundException("Profile not found"));

            Session session = sessionRepository.findById(winGoBetRequestDTO.sessionId())
                    .orElseThrow(() -> new SessionNotFoundException("Session not found"));



            return true;

        } catch (Exception e){
            throw new RuntimeException();
        }
    }
}
