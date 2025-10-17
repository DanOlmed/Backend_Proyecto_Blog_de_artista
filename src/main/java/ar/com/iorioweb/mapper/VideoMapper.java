package ar.com.iorioweb.mapper;
import ar.com.iorioweb.model.Video;
import ar.com.iorioweb.dto.VideoDto;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VideoMapper {

    public VideoDto toDTO(Video video) {
        if (video == null) return null;

        VideoDto dto = new VideoDto();
        dto.setId(video.getId());
        dto.setTitulo(video.getTitulo());
        dto.setYoutubeUrl(video.getYoutubeUrl());
        dto.setDescripcion(video.getDescripcion());
        
        
        // Mapeo de relaciones
        if (video.getArtista() != null) {
            dto.setArtistaId(video.getArtista().getId());
            dto.setNombreArtista(video.getArtista().getNombreReal());
        }
        // Asumiendo que Video tiene una relación con Disco (video.getDisco())
        if (video.getDisco() != null) { 
            dto.setDiscoId(video.getDisco().getId());
        }
        return dto;
    }

    public List<VideoDto> toDTO(List<Video> videos) {
        return videos.stream().map(this::toDTO).collect(Collectors.toList());
    }
}