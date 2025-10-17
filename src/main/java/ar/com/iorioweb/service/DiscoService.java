package ar.com.iorioweb.service;
import ar.com.iorioweb.dto.*;

import java.util.List;

public interface DiscoService {
	List<DiscoDto> obtenerTodosLosDiscos();
	DiscoDto obtenerDiscoPorId(Long id);

}
