package com.superwin.gameservice.service;

//import com.superwin.superwin.game.colortrading.wingo.dto.WinGoBetRequestDTO;
//import com.superwin.superwin.exception.ProfileNotFoundException;
//import com.superwin.superwin.exception.SessionNotFoundException;
//import com.superwin.superwin.model.Profile;
//import com.superwin.superwin.model.Session;
//import com.superwin.superwin.game.colortrading.wingo.repository.WinGoBetRepository;
//import com.superwin.superwin.repository.ProfileRepository;
//import com.superwin.superwin.repository.SessionRepository;
import com.superwin.gameservice.dto.WinGoBetRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WinGoService {

//    private WinGoBetRepository winGoBetRepository;
//    private ProfileRepository profileRepository;
//    private SessionRepository sessionRepository;
//
    public Boolean bet(WinGoBetRequestDTO winGoBetRequestDTO){
//        try {
//            Profile profile = profileRepository.findById(winGoBetRequestDTO.profileId())
//                    .orElseThrow(() -> new ProfileNotFoundException("Profile not found"));
//
//            Session session = sessionRepository.findById(winGoBetRequestDTO.sessionId())
//                    .orElseThrow(() -> new SessionNotFoundException("Session not found"));
//
//
//
//            return true;
//
//        } catch (Exception e){
//            throw new RuntimeException();
//        }
        return true;
    }
}
