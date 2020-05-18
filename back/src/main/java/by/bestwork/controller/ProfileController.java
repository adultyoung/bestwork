package by.bestwork.controller;

import by.bestwork.domain.User;
import by.bestwork.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile-api")
public class ProfileController {

    private final UserService service;

    public ProfileController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public String updateProfile(@RequestBody User user) {
        if (user.getId() != null) {
            if (service.loadUserById(user.getId()) == null) {
                return "Данного пользователя не существует";
            }
        } else {
            return "Произошла ошибка сохранения профиля";
        }
        User userFromDb = service.getUser(user.getId());
        userFromDb.setFirstName(user.getFirstName());
        userFromDb.setLastName(user.getLastName());
        userFromDb.setOtchestvo(user.getOtchestvo());
        userFromDb.setEmail(user.getEmail());
        userFromDb.setTel(user.getTel());
        service.save(userFromDb);
        return "Сохранено успешно";
    }
}
