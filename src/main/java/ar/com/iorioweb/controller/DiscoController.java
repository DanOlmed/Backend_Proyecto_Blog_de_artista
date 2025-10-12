package ar.com.iorioweb.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ar.com.iorioweb.model.Disco;
import ar.com.iorioweb.repository.DiscoRepository;
import java.util.List;

@RestController
@RequestMapping("/api/discos")
public class DiscoController {
	 @Autowired
	    private DiscoRepository discoRepository;

	    @GetMapping
	    public List<Disco> listar() {
	        return discoRepository.findAll();
	    }

	    @PostMapping
	    public Disco crear(@RequestBody Disco disco) {
	        return discoRepository.save(disco);
	    }

}
