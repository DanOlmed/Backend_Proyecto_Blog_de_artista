package ar.com.iorioweb.service;
import ar.com.iorioweb.model.Disco;
import ar.com.iorioweb.repository.DiscoRepository;
import ar.com.iorioweb.mapper.*;
import ar.com.iorioweb.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
public class DiscoServiceImplementation implements DiscoService {
	
	@Autowired // Inyectamos el Repositorio (acceso a DB)
    private DiscoRepository discoRepository;
	
	@Autowired // Inyecta el Mapper
    private DiscoMapper discoMapper;

    @Override
    public List<DiscoDto> obtenerTodosLosDiscos() {
    	List<Disco> discos= discoRepository.findAll();
    	return discoMapper.toDTO(discos);
    }

    @Override
    public DiscoDto obtenerDiscoPorId(Long id) {
    	return discoRepository.findById(id)
                .map(discoMapper::toDTO) // <--- Mapea si existe
                .orElse(null);
    }
	

}
