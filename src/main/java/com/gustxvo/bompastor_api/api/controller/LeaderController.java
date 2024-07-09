package com.gustxvo.bompastor_api.api.controller;

import com.gustxvo.bompastor_api.api.model.event.EventDto;
import com.gustxvo.bompastor_api.api.model.sector.SectorDto;
import com.gustxvo.bompastor_api.domain.repository.EventRepository;
import com.gustxvo.bompastor_api.domain.repository.SectorRepository;
import com.gustxvo.bompastor_api.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/leader")
@RequiredArgsConstructor
public class LeaderController {

    private final UserRepository userRepository;
    private final SectorRepository sectorRepository;
    private final EventRepository eventRepository;

    @GetMapping("/sector")
    public ResponseEntity<SectorDto> sector(JwtAuthenticationToken token) {
        UUID leaderId = UUID.fromString(token.getName());
        SectorDto sector = sectorRepository.findByLeaderId(leaderId).stream()
                .findFirst()
                .map(SectorDto::fromEntity)
                .orElseThrow(() -> new IllegalStateException("Sector with no leader"));

        return ResponseEntity.ok(sector);
    }

    @GetMapping("/events")
    public List<EventDto> events(JwtAuthenticationToken token) {
        UUID leaderId = UUID.fromString(token.getName());

        return eventRepository.findAllBySectorLeaderId(leaderId).stream()
                .map(EventDto::fromEntity)
                .toList();
    }

}