package ar.com.iorioweb.service;
import ar.com.iorioweb.repository.VideoRepository;
import ar.com.iorioweb.service.VideoService;
import ar.com.iorioweb.mapper.*;
import ar.com.iorioweb.dto.VideoDto;      // <-- IMPORTAR DTO
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VideoServiceImplementation implements VideoService {

    @Autowired
    private VideoRepository videoRepository;
    
    @Autowired
    private VideoMapper videoMapper; // <-- Inyectar Mapper

    @Override
    public List<VideoDto> obtenerTodosLosVideos() {
        return videoMapper.toDTO(videoRepository.findAll()); // <-- Usar Mapper
    }

    @Override
    public VideoDto obtenerVideoPorId(Long id) {
        return videoRepository.findById(id)
            .map(videoMapper::toDTO) // <-- Mapear a DTO
            .orElse(null);
    }
    
    @Override
    public List<VideoDto> obtenerVideosPorTipo(String tipo) {
        // Asumiendo que has creado 'findByTipo' en VideoRepository
        // return videoMapper.toDTO(videoRepository.findByTipo(tipo)); 
        
        // O si no tienes findByTipo, simplemente devuelve todos (menos eficiente)
        return obtenerTodosLosVideos();
    }
}