package ar.com.iorioweb.service;
import ar.com.iorioweb.model.Disco;
public class DiscoService {

	Disco disco = new Disco();
	
	public Disco crearDisco(Disco disco) {
	    
	    disco.setNombreDisco(disco.getNombreDisco().toUpperCase());
	    return disco;
	}
	
}
