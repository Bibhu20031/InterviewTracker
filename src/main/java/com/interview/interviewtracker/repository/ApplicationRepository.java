package com.interview.interviewtracker.repository;

import com.interview.interviewtracker.domain.entity.Application;
import com.interview.interviewtracker.domain.enums.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findByStatus(ApplicationStatus status);

    List<Application> findByCompanyNameContainingIgnoreCase(String companyName);
}
