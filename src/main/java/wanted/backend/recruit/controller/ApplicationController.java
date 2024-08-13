package wanted.backend.recruit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import wanted.backend.recruit.dto.application.ApplicationRequest;
import wanted.backend.recruit.dto.application.ApplicationResponse;
import wanted.backend.recruit.service.ApplicationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/application")
public class ApplicationController {
    private final ApplicationService applicationService;

    // 채용공고 지원
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/create")
    public ApplicationResponse createApplication(@RequestBody ApplicationRequest request) {
        return applicationService.createApplication(request);
    }
}
