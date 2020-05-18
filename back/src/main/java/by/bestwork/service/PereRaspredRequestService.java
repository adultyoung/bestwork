package by.bestwork.service;

import by.bestwork.dao.PereRaspredRequestDao;
import by.bestwork.domain.PereRaspredRequest;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PereRaspredRequestService {

    private final PereRaspredRequestDao dao;

    public PereRaspredRequestService(PereRaspredRequestDao dao) {
        this.dao = dao;
    }

    public Set<PereRaspredRequest> getPereRaspredRequestsForUser(Long userId) {
        return dao.findByUserId(userId);
    }

    public Set<PereRaspredRequest> findAll() {
        return dao.findAll().stream().collect(Collectors.toSet());
    }

    public PereRaspredRequest findById(String id) {
        return dao.getOne(id);
    }
}
