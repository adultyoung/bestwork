package by.bestwork.controller;

import by.bestwork.dao.CheckDao;
import by.bestwork.domain.Check;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

@Controller
@RequestMapping("save-check")
public class CheckController {

    private final CheckDao checkDao;

    public CheckController(CheckDao checkDao) {
        this.checkDao = checkDao;
    }

    @PostMapping
    public String saveCheck(@RequestParam("file") MultipartFile file, @RequestParam("sum") double sum, @RequestParam("id") Long userId) {
        File dir = new File("checks");
        if (!dir.exists()) dir.mkdirs();
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                String filename = "checks/" + "check_" + userId + "_" + sum;
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(filename)));
                stream.write(bytes);
                stream.close();
                Check check = new Check();
                check.setDate(new Date(System.currentTimeMillis()));
                check.setFile(filename);
                check.setSum(sum);
                check.setToDelete(false);
                check.setVerified(false);
                check.setUploaded(false);
                checkDao.save(check);
                return "Найс";
            } catch (Exception e) {
                return "Не найс => " + e.getMessage();
            }
        } else {
            return "Не найс без ошибки";
        }
    }
}
