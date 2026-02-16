package com.interview.interviewtracker.service;

import com.interview.interviewtracker.domain.entity.Application;
import com.interview.interviewtracker.domain.entity.InterviewRound;
import com.interview.interviewtracker.domain.enums.InterviewRoundType;
import com.interview.interviewtracker.dto.InterviewRoundResponse;
import com.interview.interviewtracker.repository.ApplicationRepository;
import com.interview.interviewtracker.repository.InterviewRoundRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InterviewRoundService {

    private final InterviewRoundRepository roundRepository;
    private final ApplicationRepository applicationRepository;

    public InterviewRoundService(InterviewRoundRepository roundRepository,
                                 ApplicationRepository applicationRepository) {
        this.roundRepository = roundRepository;
        this.applicationRepository = applicationRepository;
    }

    public InterviewRoundResponse addRound(Long applicationId,
                                           InterviewRoundType type,
                                           LocalDateTime scheduledAt) {

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        InterviewRound round = new InterviewRound(type, application);
        round.setScheduledAt(scheduledAt);

        InterviewRound saved = roundRepository.save(round);

        return mapToResponse(saved);
    }


    public List<InterviewRoundResponse> getRounds(Long applicationId) {
        return roundRepository.findByApplicationId(applicationId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private InterviewRoundResponse mapToResponse(InterviewRound round) {
        return new InterviewRoundResponse(
                round.getId(),
                round.getRoundType(),
                round.getPassed(),
                round.getFeedback(),
                round.getScheduledAt(),
                round.getApplication().getId()
        );
    }

}
