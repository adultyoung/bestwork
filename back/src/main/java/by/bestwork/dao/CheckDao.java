package by.bestwork.dao;

import by.bestwork.domain.Check;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckDao extends JpaRepository<Check, String> {

    List<Check> findAllByUploadedIsFalse();

    List<Check> findAllByUserId(Long userId);
}
