//package by.bestwork.controller;
//
//import by.bestwork.dto.HelpBody;
//import by.bestwork.service.ServiceDriveService;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@RequestMapping("/help-controller")
//@Controller
//public class HelpController {
//
//    private final ServiceDriveService driveService;
//
//    public HelpController(ServiceDriveService driveService) {
//        this.driveService = driveService;
//    }
//
//    @PostMapping
//    public String saveHelp(@RequestBody HelpBody help) {
//        driveService.saveHelp(help);
//        return "index";
//    }
//}
