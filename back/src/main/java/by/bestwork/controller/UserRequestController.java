package by.bestwork.controller;

import by.bestwork.domain.User;
import by.bestwork.dto.Mail;
import by.bestwork.dto.Role;
import by.bestwork.service.EmailService;
import by.bestwork.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/request-user")
public class UserRequestController {

    private final BCryptPasswordEncoder encoder;
    private final UserService service;
    private final JavaMailSender javaMailSender;

    private final EmailService emailService;

    public UserRequestController(BCryptPasswordEncoder encoder, UserService service, JavaMailSender javaMailSender, EmailService emailService) {
        this.encoder = encoder;
        this.service = service;
        this.javaMailSender = javaMailSender;
        this.emailService = emailService;
    }

    @PostMapping
    public User saveUserFromRequest(@RequestBody User user, @AuthenticationPrincipal User userSession) throws IOException, MessagingException {
        if (userSession != null) {
            return userSession;
        }
        if (user.getEmail() != null
                && user.getTel() != null
                && user.getFirstName() != null
                && user.getLastName() != null) {
            String generatedString = generateCommonLangPassword();
            user.setPassword(encoder.encode(generatedString));
            user.setRoles(Collections.singleton(Role.USER));
            User userCreated = service.save(user);
            if (userCreated.getId() != null) {
                Mail mail = new Mail();
                mail.setFrom("no-reply@bestwork.by");
                mail.setTo(user.getEmail());
                mail.setSubject("Спасибо за регистрацию");

                Map<String, Object> model = new HashMap<>();
                model.put("username", user.getEmail());
                model.put("password", generatedString);
                mail.setModel(model);

                emailService.sendSimpleMessage(mail);
            }
            return userCreated;
        }
        return null;
    }

    @PutMapping("{id}")
    public User updateUser(@RequestBody User user, @PathVariable("id") Long userId) {
        return service.updateUser(user, service.loadUserById(userId));
    }

    public String generateCommonLangPassword() {
        String upperCaseLetters = RandomStringUtils.random(2, 65, 90, true, true);
        String lowerCaseLetters = RandomStringUtils.random(2, 97, 122, true, true);
        String numbers = RandomStringUtils.randomNumeric(2);
        String specialChar = RandomStringUtils.random(2, 33, 47, false, false);
        String totalChars = RandomStringUtils.randomAlphanumeric(2);
        String combinedChars = upperCaseLetters.concat(lowerCaseLetters)
                .concat(numbers)
//                .concat(specialChar)
                .concat(totalChars);
        List<Character> pwdChars = combinedChars.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());
        Collections.shuffle(pwdChars);
        String password = pwdChars.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
        return password;
    }


}
