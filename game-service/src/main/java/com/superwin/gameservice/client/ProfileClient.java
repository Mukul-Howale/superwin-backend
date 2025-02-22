package com.superwin.gameservice.client;

\import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.UUID;

@HttpExchange
public interface ProfileClient {

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> getById(@PathVariable UUID id);
}
