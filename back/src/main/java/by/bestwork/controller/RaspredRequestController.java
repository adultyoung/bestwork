package by.bestwork.controller;

import by.bestwork.domain.RaspredRequest;
import by.bestwork.domain.User;
import by.bestwork.service.RaspredRequestService;
import by.bestwork.service.ServiceDriveService;
import by.bestwork.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/raspred-request")
public class RaspredRequestController {

    private final ServiceDriveService driveService;
    private final UserService userService;
    private final RaspredRequestService requestService;

    public RaspredRequestController(ServiceDriveService driveService, UserService userService, RaspredRequestService requestService) {
        this.driveService = driveService;
        this.userService = userService;
        this.requestService = requestService;
    }

    @PostMapping
    public Long saveRequest(@RequestBody RaspredRequest request, @AuthenticationPrincipal User user) throws Exception {

        if (
                request.getRequest().getFacultet() != null
                        && request.getRequest().getRectorName() != null
                        && request.getRequest().getSpecialization() != null
                        && request.getRequest().getYoName() != null
        ) {
            if (user != null) {
                request.setUserId(user.getId());
            }
            request = requestService.save(request);
            driveService.saveStudentRequest(request);
        }
        return request.getId();
    }
}
