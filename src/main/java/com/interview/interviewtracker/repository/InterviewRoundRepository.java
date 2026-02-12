package com.interview.interviewtracker.repository;

import com.interview.interviewtracker.domain.entity.InterviewRound;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterviewRoundRepository extends JpaRepository<InterviewRound, Long> {

    List<InterviewRound> findByApplicationId(Long applicationId);
}
