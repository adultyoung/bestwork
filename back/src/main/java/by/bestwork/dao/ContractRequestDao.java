package by.bestwork.dao;

import by.bestwork.domain.ContractRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ContractRequestDao extends JpaRepository<ContractRequest, String> {

    Set<ContractRequest> findByUserId(Long userId);

    ContractRequest findById(Long id);

}
