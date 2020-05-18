package by.bestwork.dao;

import by.bestwork.domain.PereRaspredRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PereRaspredRequestDao extends JpaRepository<PereRaspredRequest, String> {


    Set<PereRaspredRequest> findByUserId(Long userId);

}
