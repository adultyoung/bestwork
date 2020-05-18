package by.bestwork.service;

import by.bestwork.dao.CheckDao;
import by.bestwork.domain.Check;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckService {
    private final CheckDao checkDao;

    public CheckService(CheckDao checkDao) {
        this.checkDao = checkDao;
    }

    public List<Check> findAll() {
        return checkDao.findAll();
    }

    public List<Check> findAllbyUploadedIsFalse() {
        return checkDao.findAllByUploadedIsFalse();
    }

    public void deleteAll() {
        checkDao.deleteAll();
    }

    public void saveCheck(Check check) {
        checkDao.save(check);
    }
}
