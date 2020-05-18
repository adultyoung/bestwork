package by.bestwork.controller;

import by.bestwork.dao.CosmeticsDao;
import by.bestwork.domain.Cosmetics;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parfume-api")
public class CosmeticsController {

    private final CosmeticsDao dao;

    public CosmeticsController(CosmeticsDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Cosmetics> getCosmetics() {
        return dao.findAll();
    }
}
