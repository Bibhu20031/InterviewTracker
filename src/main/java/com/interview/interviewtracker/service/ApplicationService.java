package com.interview.interviewtracker.service;

import com.interview.interviewtracker.domain.entity.Application;
import com.interview.interviewtracker.domain.enums.ApplicationStatus;
import com.interview.interviewtracker.dto.ApplicationResponse;
import com.interview.interviewtracker.dto.CreateApplicationRequest;
import com.interview.interviewtracker.dto.PagedResponse;
import com.interview.interviewtracker.dto.UpdateApplicationRequest;
import com.interview.interviewtracker.repository.ApplicationRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


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

    public PagedResponse<ApplicationResponse> getPaginated(
            ApplicationStatus status,
            Pageable pageable) {

        Page<Application> page;

        if (status != null) {
            page = applicationRepository.findByStatus(status, pageable);
        } else {
            page = applicationRepository.findAll(pageable);
        }

        Page<ApplicationResponse> mapped = page.map(this::mapToResponse);

        return new PagedResponse<>(
                mapped.getContent(),
                mapped.getNumber(),
                mapped.getSize(),
                mapped.getTotalElements(),
                mapped.getTotalPages()
        );
    }
}
