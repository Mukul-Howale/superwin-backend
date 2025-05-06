package com.superwin.gameservice.client;

import com.superwin.gameservice.dto.ProfileDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.UUID;

@HttpExchange("/profile")
public interface ProfileClient {

    @GetExchange("/{id}")
    ResponseEntity<ProfileDTO> getById(@PathVariable UUID id);
}
