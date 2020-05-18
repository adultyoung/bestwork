package by.bestwork.service;

import by.bestwork.dao.RaspredRequestDao;
import by.bestwork.domain.RaspredRequest;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RaspredRequestService {

    private final RaspredRequestDao dao;

    public RaspredRequestService(RaspredRequestDao dao) {
        this.dao = dao;
    }

    public Set<RaspredRequest> getraspredRequestsForUser(Long userId) {
        return dao.findByUserId(userId);
    }

    public Set<RaspredRequest> findAll() {
        return dao.findAll().stream().collect(Collectors.toSet());
    }

    public RaspredRequest save(RaspredRequest request) {
        return dao.save(request);
    }

    public RaspredRequest findById(String id) {
        return dao.findById(id).get();
    }
}
