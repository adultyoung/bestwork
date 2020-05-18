package by.bestwork.controller;

import by.bestwork.domain.ContractRequest;
import by.bestwork.domain.User;
import by.bestwork.service.ContractRequestService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@RestController
@RequestMapping(value = "/contract-request-api")
public class ContractRequestController {

    private final ContractRequestService service;

    public ContractRequestController(ContractRequestService service) {
        this.service = service;
    }

    @PostMapping
    public ContractRequest saveContractRequest(@RequestBody ContractRequest contractRequest, @AuthenticationPrincipal User user) {
        if (user != null) {
            contractRequest.setUserId(user.getId());
        }
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Moscow"));
        contractRequest.setCreatedAt(new SimpleDateFormat("dd.MM.yy HH:mm").format(new Date()));
        return service.saveRequest(contractRequest);
    }

    @PutMapping
    public ContractRequest updateContractRequest(@RequestBody ContractRequest request) {
        if (request.getPeriod() != null && request.getStartOfContract() != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(request.getStartOfContract());
            cal.add(Calendar.DATE, -1);
            cal.add(Calendar.YEAR, request.getPeriod().intValue());
            request.setEndOfContract(cal.getTime());
        }
        return service.updateRequest(request);
    }

    @GetMapping
    public ContractRequest getOne(@RequestParam("id") Long id) {
        return service.findById(id);
    }
}
