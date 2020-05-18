package by.bestwork.service;

import by.bestwork.dao.PractiseRequestDao;
import by.bestwork.domain.PractiseRequest;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PractiseRequestService {

    private final PractiseRequestDao dao;

    public PractiseRequestService(PractiseRequestDao dao) {
        this.dao = dao;
    }

    public Set<PractiseRequest> getpractiseRequestsForUser(Long userId) {
        return dao.findByUserId(userId);
    }

    public Set<PractiseRequest> findAll() {
        return dao.findAll().stream().collect(Collectors.toSet());
    }
}
