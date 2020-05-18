package by.bestwork.dao;

import by.bestwork.domain.PractiseRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PractiseRequestDao extends JpaRepository<PractiseRequest, String> {
    Set<PractiseRequest> findByUserId(Long userId);

}
