package ar.com.iorioweb.mapper;
import ar.com.iorioweb.model.Biografia;
import ar.com.iorioweb.dto.BiografiaDto;
import org.springframework.stereotype.Component;

@Component
public class BiografiaMapper {

    public BiografiaDto toDTO(Biografia biografia) {
        if (biografia == null) return null;

        BiografiaDto dto = new BiografiaDto();
        dto.setId(biografia.getId());
        dto.setBiografiaCompleta(biografia.getBiografiaCompleta());
        dto.setImagenFondoUrl(biografia.getImagenFondoUrl());
        
        // Mapeo de la relación
        if (biografia.getArtista() != null) {
            dto.setArtistaId(biografia.getArtista().getId());
            dto.setNombreArtista(biografia.getArtista().getNombreReal());
        }
        return dto;
    }
}