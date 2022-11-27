package it.prova.gestionetratte.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.gestionetratte.dto.tratta.TrattaDTO;
import it.prova.gestionetratte.model.Tratta;
import it.prova.gestionetratte.service.TrattaService;
import it.prova.gestionetratte.web.api.exception.AirbusNotFoundException;
import it.prova.gestionetratte.web.api.exception.IdNotNullForInsertionException;

@RestController
@RequestMapping("api/tratta")
public class TrattaController {
	
	@Autowired
	private TrattaService trattaService;
	
	@GetMapping
	public List<TrattaDTO> listAll(){
		return TrattaDTO.buildTrattaDTOListFromModelList(trattaService.listAll() , true);
	}
	
	@GetMapping("/{id}")
	public TrattaDTO findById(@PathVariable(value = "id",required = true) Long id) {
		Tratta tratta = trattaService.findByIdEager(id);
		
		if(tratta == null)
			throw new AirbusNotFoundException("Tratta not found with id:"+id);
		
		return TrattaDTO.buildTrattaDTOFromModel(tratta, true);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TrattaDTO insertNew(@Valid@RequestBody TrattaDTO input) {
		if(input.getId() != null)
			throw new IdNotNullForInsertionException("L'elemento da inserire non deve avere il campo id valorizzato.");
		Tratta nuovaTratta = trattaService.inserisciNuovo(input.buildTrattaModel(false));
		return TrattaDTO.buildTrattaDTOFromModel(nuovaTratta, false);
	}
	
	@PutMapping("/{id}")
	public TrattaDTO aggiorna(@Valid @RequestBody TrattaDTO input, @PathVariable(required = true) Long id) {
		Tratta tratta = trattaService .findById(id);
		
		if(tratta == null)
			throw new AirbusNotFoundException("No Tratta found with id:"+id);
		
		input.setId(id);
		Tratta aggiornata = trattaService.update(input.buildTrattaModel(false));
		return TrattaDTO.buildTrattaDTOFromModel(aggiornata, false);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(required = true) Long id) {
		trattaService.rimuovi(id);
	}
	
	@PostMapping("/search")
	public List<TrattaDTO> search(@RequestBody TrattaDTO example) {
		return TrattaDTO.buildTrattaDTOListFromModelList(trattaService.findByExample(example.buildTrattaModel(false)),
				false);
	}
	
	@GetMapping("/concludiTratte")
	public List<TrattaDTO> concludiTratte(){
		return TrattaDTO.buildTrattaDTOListFromModelList(trattaService.concludiTratte(), false); 
	}
}
