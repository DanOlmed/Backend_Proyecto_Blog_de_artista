package ar.com.iorioweb.repository;
import ar.com.iorioweb.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    // <Clase de la Entidad, Tipo de la clave primaria (ID)>
}