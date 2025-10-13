package ar.com.iorioweb.repository;

import ar.com.iorioweb.model.Biografia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiografiaRepository extends JpaRepository<Biografia, Long> {
}