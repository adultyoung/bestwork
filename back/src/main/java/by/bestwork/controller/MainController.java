package by.bestwork.controller;

import by.bestwork.dao.CheckDao;
import by.bestwork.domain.ContractRequest;
import by.bestwork.domain.User;
import by.bestwork.dto.PersonInfo;
import by.bestwork.dto.Role;
import by.bestwork.dto.SocialNetwork;
import by.bestwork.service.AuthorizationService;
import by.bestwork.service.ContractRequestService;
import by.bestwork.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {
    private final UserService userService;
    private final ContractRequestService contractRequestService;
    private final AuthorizationService authorizationService;
    private final CheckDao checkDao;

    public MainController(UserService userService, ContractRequestService contractRequestService, AuthorizationService authorizationService, CheckDao checkDao) {
        this.userService = userService;
        this.contractRequestService = contractRequestService;
        this.authorizationService = authorizationService;
        this.checkDao = checkDao;
    }

    @GetMapping
    public String index(Model model, @AuthenticationPrincipal User user) throws Exception {
        HashMap<Object, Object> data = new HashMap<>();
        if (user != null) {
            data.put("profile", user);
            data.put("googleAuth", authorizationService.isUserAuthenticated());
            data.put("requests", userService.getUserRequests(user.getId()));
            data.put("docsRequests", userService.getDocsList(user.getId()));
            data.put("checks", checkDao.findAllByUserId(user.getId()));
            if (user.getRoles().contains(Role.MODERATOR)) {
                data.put("allRequests", userService.getAllRequests());
            }
        } else {
            data.put("profile", null);
            data.put("requests", null);
        }
        ContractRequest contractRequest = new ContractRequest();

        data.put("contractRequest", contractRequest);
        data.put("personInfo", new PersonInfo());
        data.put("social", new SocialNetwork());
        model.addAttribute("frontendData", data);
        return "index";
    }
}
