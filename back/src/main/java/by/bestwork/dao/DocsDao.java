package by.bestwork.dao;

import by.bestwork.domain.Docs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocsDao extends JpaRepository<Docs, Long> {
    List<Docs> findAllByUserId(Long userId);
}
