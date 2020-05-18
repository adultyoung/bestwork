package by.bestwork.dao;

import by.bestwork.domain.RaspredRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RaspredRequestDao extends JpaRepository<RaspredRequest, String> {
    Set<RaspredRequest> findByUserId(Long userId);

}
