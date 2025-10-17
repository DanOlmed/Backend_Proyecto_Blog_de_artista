package ar.com.iorioweb.mapper;
import ar.com.iorioweb.model.Disco;
import ar.com.iorioweb.dto.DiscoDto;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DiscoMapper {

    public DiscoDto toDTO(Disco disco) {
        if (disco == null) {
            return null;
        }
        DiscoDto dto = new DiscoDto();
        dto.setId(disco.getId());
        dto.setNombreDisco(disco.getNombreDisco());
        dto.setListaTemas(disco.getListaTemas());
        dto.setImagenUrl(disco.getImagenUrl());
        
        // Mapeo de la relación (solo los IDs y nombres)
        if (disco.getArtista() != null) {
            dto.setArtistaId(disco.getArtista().getId());
            dto.setNombreArtista(disco.getArtista().getNombreReal());
        }
        return dto;
    }

    public List<DiscoDto> toDTO(List<Disco> discos) {
        return discos.stream().map(this::toDTO).collect(Collectors.toList());
    }
}