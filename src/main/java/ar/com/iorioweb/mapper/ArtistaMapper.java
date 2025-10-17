package ar.com.iorioweb.mapper;
import ar.com.iorioweb.model.Artista;
import ar.com.iorioweb.dto.ArtistaDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArtistaMapper {

    @Autowired
    private DiscoMapper discoMapper;
    
    @Autowired
    private VideoMapper videoMapper;
    
    @Autowired
    private BiografiaMapper biografiaMapper;

    public ArtistaDto toPrincipalDTO(Artista artista) {
        if (artista == null) return null;

        ArtistaDto dto = new ArtistaDto();
        dto.setId(artista.getId());
        dto.setNombreReal(artista.getNombreReal());
        dto.setFotoPerfilPrincipal(artista.getFotoPerfilPrincipal());
        
        // Mapeo de colecciones y relaciones usando los otros mappers
        if (artista.getBiografia() != null) {
            dto.setBiografia(biografiaMapper.toDTO(artista.getBiografia()));
        }
        if (artista.getDiscos() != null) {
            dto.setDiscos(discoMapper.toDTO(artista.getDiscos()));
        }
        if (artista.getVideos() != null) {
            dto.setVideos(videoMapper.toDTO(artista.getVideos()));
        }

        return dto;
    }
}