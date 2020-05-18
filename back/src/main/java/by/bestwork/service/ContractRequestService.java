package by.bestwork.service;

import by.bestwork.dao.ContractRequestDao;
import by.bestwork.domain.ContractRequest;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ContractRequestService {

    private final ContractRequestDao dao;

    public ContractRequestService(ContractRequestDao dao) {
        this.dao = dao;
    }

    public Set<ContractRequest> getContractRequestsForUser(Long userId) {
        return dao.findByUserId(userId);
    }

    public ContractRequest saveRequest(ContractRequest request) {
        return dao.save(request);
    }

    public ContractRequest updateRequest(ContractRequest request) {
        return dao.save(request);
    }

    public ContractRequest findById(Long id) {
        return dao.findById(id);
    }

    Set<ContractRequest> findAll() {
        return new HashSet<>(dao.findAll());
    }
}
