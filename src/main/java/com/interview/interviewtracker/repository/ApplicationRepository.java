package com.interview.interviewtracker.repository;

import com.interview.interviewtracker.domain.entity.Application;
import com.interview.interviewtracker.domain.enums.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Page<Application> findByStatus(ApplicationStatus status, Pageable pageable);


    List<Application> findByCompanyNameContainingIgnoreCase(String companyName);
}
