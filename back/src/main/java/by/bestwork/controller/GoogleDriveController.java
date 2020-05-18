package by.bestwork.controller;

import by.bestwork.domain.ContractRequest;
import by.bestwork.service.AuthorizationService;
import by.bestwork.service.DriveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RestController
@RequestMapping("/google")
public class GoogleDriveController {

    private Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    AuthorizationService authorizationService;

    @Autowired
    DriveService driveService;


    @GetMapping("/googlesignin")
    public void doGoogleSignIn(HttpServletResponse response) throws Exception {
        logger.debug("SSO Called...");
        response.sendRedirect(authorizationService.authenticateUserViaGoogle());
    }

    @GetMapping("/oauth/callback")
    public void saveAuthorizationCode(@RequestParam String code, HttpServletResponse response) throws Exception {
        logger.debug("SSO Callback invoked...");
        logger.debug("SSO Callback Code Value..., " + code);

        if (code != null) {
            authorizationService.exchangeCodeForTokens(code);
            response.sendRedirect("/cabinet/requests");
            return;
        }
        response.sendRedirect("/google/googlesignin");
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws Exception {
        logger.debug("Logout invoked...");
        authorizationService.removeUserSession(request);
        return "index";
    }

    @PostMapping("/save-request")
    public ContractRequest saveRequestToDrive(HttpServletResponse response, @RequestBody ContractRequest contractRequest) throws Exception {
        if (authorizationService.isUserAuthenticated()) {
            return driveService.saveContractRequest(contractRequest);
        } else {
            response.sendRedirect("/google/googlesignin");
            return null;
        }
    }
}
