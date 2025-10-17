package ar.com.iorioweb.service;
import ar.com.iorioweb.model.Artista;
import ar.com.iorioweb.repository.ArtistaRepository;
import ar.com.iorioweb.service.ArtistaService;
import ar.com.iorioweb.mapper.*;

import ar.com.iorioweb.dto.ArtistaDto;    
import ar.com.iorioweb.dto.BiografiaDto;           
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistaServiceImplementation implements ArtistaService {

    @Autowired
    private ArtistaRepository artistaRepository;
    
    @Autowired
    private ArtistaMapper artistaMapper; 
    
    @Autowired
    private BiografiaMapper biografiaMapper; 

    @Override
    public ArtistaDto obtenerArtistaPrincipalDTO() {
        Artista artista = artistaRepository.findById(1L).orElse(null);
        return artistaMapper.toPrincipalDTO(artista); 
    }
    
    @Override
    public BiografiaDto obtenerBiografiaDTO() {
        Artista artista = artistaRepository.findById(1L).orElse(null);
        return (artista != null && artista.getBiografia() != null) ? 
               biografiaMapper.toDTO(artista.getBiografia()) : 
               null; 
    }

    
    @Override
    public Artista guardarArtista(Artista artista) {
        return artistaRepository.save(artista);
    }

    @Override
    public Artista obtenerArtistaPorId(Long id) {
        return artistaRepository.findById(id).orElse(null);
    }
}