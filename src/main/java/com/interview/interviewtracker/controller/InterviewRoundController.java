package com.interview.interviewtracker.controller;


import com.interview.interviewtracker.domain.enums.InterviewRoundType;
import com.interview.interviewtracker.dto.InterviewRoundResponse;
import com.interview.interviewtracker.service.InterviewRoundService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/applications/{applicationId}/rounds")
public class InterviewRoundController {

    private final InterviewRoundService roundService;

    public InterviewRoundController(InterviewRoundService roundService) {
        this.roundService = roundService;
    }

    @PostMapping
    public ResponseEntity<InterviewRoundResponse> addRound(
            @PathVariable Long applicationId,
            @RequestParam InterviewRoundType type,
            @RequestParam(required = false) LocalDateTime scheduledAt) {

        return ResponseEntity.ok(
                roundService.addRound(applicationId, type, scheduledAt)
        );
    }

    @GetMapping
    public ResponseEntity<List<InterviewRoundResponse>> getRounds(
            @PathVariable Long applicationId) {

        return ResponseEntity.ok(roundService.getRounds(applicationId));
    }
}
