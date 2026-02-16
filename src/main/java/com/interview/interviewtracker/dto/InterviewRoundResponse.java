package com.interview.interviewtracker.dto;

import com.interview.interviewtracker.domain.enums.InterviewRoundType;

import java.time.LocalDateTime;

public class InterviewRoundResponse {

    private Long id;
    private InterviewRoundType roundType;
    private Boolean passed;
    private String feedback;
    private LocalDateTime scheduledAt;
    private Long applicationId;

    public InterviewRoundResponse(Long id,
                                  InterviewRoundType roundType,
                                  Boolean passed,
                                  String feedback,
                                  LocalDateTime scheduledAt,
                                  Long applicationId) {
        this.id = id;
        this.roundType = roundType;
        this.passed = passed;
        this.feedback = feedback;
        this.scheduledAt = scheduledAt;
        this.applicationId = applicationId;
    }

    public Long getId() { return id; }
    public InterviewRoundType getRoundType() { return roundType; }
    public Boolean getPassed() { return passed; }
    public String getFeedback() { return feedback; }
    public LocalDateTime getScheduledAt() { return scheduledAt; }
    public Long getApplicationId() { return applicationId; }
}
