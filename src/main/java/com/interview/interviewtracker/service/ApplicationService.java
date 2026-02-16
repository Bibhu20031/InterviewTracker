package com.interview.interviewtracker.service;

import com.interview.interviewtracker.domain.entity.Application;
import com.interview.interviewtracker.domain.enums.ApplicationStatus;
import com.interview.interviewtracker.dto.ApplicationResponse;
import com.interview.interviewtracker.dto.CreateApplicationRequest;
import com.interview.interviewtracker.dto.UpdateApplicationRequest;
import com.interview.interviewtracker.repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public ApplicationResponse create(CreateApplicationRequest request) {
        Application application =
                new Application(request.getCompanyName(), request.getRole());

        application.setNotes(request.getNotes());

        Application saved = applicationRepository.save(application);
        return mapToResponse(saved);
    }

    public List<ApplicationResponse> getAll() {
        return applicationRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ApplicationResponse getById(Long id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        return mapToResponse(application);
    }

    public ApplicationResponse update(Long id, UpdateApplicationRequest request) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        if (request.getCompanyName() != null)
            application.setCompanyName(request.getCompanyName());

        if (request.getRole() != null)
            application.setRole(request.getRole());

        if (request.getNotes() != null)
            application.setNotes(request.getNotes());

        return mapToResponse(applicationRepository.save(application));
    }

    public void delete(Long id) {
        applicationRepository.deleteById(id);
    }

    public ApplicationResponse updateStatus(Long id, ApplicationStatus newStatus) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        if (application.getStatus() == ApplicationStatus.REJECTED ||
                application.getStatus() == ApplicationStatus.OFFER) {
            throw new RuntimeException("Cannot change status from terminal state");
        }

        application.setStatus(newStatus);
        return mapToResponse(applicationRepository.save(application));
    }

    private ApplicationResponse mapToResponse(Application application) {
        return new ApplicationResponse(
                application.getId(),
                application.getCompanyName(),
                application.getRole(),
                application.getStatus(),
                application.getNotes(),
                application.getCreatedAt(),
                application.getUpdatedAt()
        );
    }
}
