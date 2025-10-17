package ar.com.iorioweb.service;
import ar.com.iorioweb.dto.ArtistaDto; 
import ar.com.iorioweb.dto.BiografiaDto;       
import ar.com.iorioweb.model.Artista;

public interface ArtistaService {
    // Los métodos ahora devuelven DTOs
    ArtistaDto obtenerArtistaPrincipalDTO(); 
    BiografiaDto obtenerBiografiaDTO();             
    
    // Mantenemos este para uso interno (ej: guardar)
    Artista guardarArtista(Artista artista);
    Artista obtenerArtistaPorId(Long id); 
}