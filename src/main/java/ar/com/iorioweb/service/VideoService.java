package ar.com.iorioweb.service;
import ar.com.iorioweb.dto.VideoDto; 
import ar.com.iorioweb.model.Video;
import java.util.List;

public interface VideoService {
    List<VideoDto> obtenerTodosLosVideos(); // <-- CAMBIO
    VideoDto obtenerVideoPorId(Long id);      // <-- CAMBIO
    List<VideoDto> obtenerVideosPorTipo(String tipo); // <-- CAMBIO
}