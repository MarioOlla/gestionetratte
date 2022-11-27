package it.prova.gestionetratte.web.api;

import java.util.ArrayList;
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

import it.prova.gestionetratte.dto.airbus.AirbusDTO;
import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.model.Tratta;
import it.prova.gestionetratte.service.AirbusService;
import it.prova.gestionetratte.web.api.exception.AirbusNotFoundException;
import it.prova.gestionetratte.web.api.exception.IdNotNullForInsertionException;
import it.prova.gestionetratte.web.api.exception.StillHasLinkedTratteException;

@RestController
@RequestMapping("api/airbus")
public class AirbusController {

	@Autowired
	private AirbusService airbusService;

	@GetMapping
	public List<AirbusDTO> listAll() {
		return AirbusDTO.buildAirbusDTOListFromModelList(airbusService.listAllEager(), true);
	}

	@GetMapping("/listaAirbusEvidenziandoSovrapposizioni")
	public List<AirbusDTO> listAllConSovrapposizione() {
		List<Airbus> tutti = airbusService.listAllEager();
		List<AirbusDTO> result = new ArrayList<>();

		tutti.stream().forEach(airbus -> {
			AirbusDTO temp;
			if (this.hasSovrapposizioni(airbus)) {
				temp = AirbusDTO.buildAirbusDTOFromModelConSovrapposizioni(airbus, false);
			} else {
				temp = AirbusDTO.buildAirbusDTOFromModel(airbus, false);
			}
			result.add(temp);
		});
		return result;
	}

	@GetMapping("/{id}")
	public AirbusDTO findById(@PathVariable(value = "id", required = true) Long id) {
		Airbus airbus = airbusService.findByIdEager(id);

		if (airbus == null)
			throw new AirbusNotFoundException("Airbus not found with id:" + id);

		return AirbusDTO.buildAirbusDTOFromModel(airbus, true);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AirbusDTO insertNew(@Valid @RequestBody AirbusDTO input) {
		if (input.getId() != null)
			throw new IdNotNullForInsertionException("L'elemento da inserire non deve avere il campo id valorizzato.");
		Airbus nuovoAirbus = airbusService.inserisciNuovo(input.buildAirbusModel(false));
		return AirbusDTO.buildAirbusDTOFromModel(nuovoAirbus, false);
	}

	@PutMapping("/{id}")
	public AirbusDTO aggiorna(@Valid @RequestBody AirbusDTO input, @PathVariable(required = true) Long id) {
		Airbus airbus = airbusService.findById(id);

		if (airbus == null)
			throw new AirbusNotFoundException("No Airbus found with id:" + id);

		input.setId(id);
		Airbus aggiornato = airbusService.update(input.buildAirbusModel(false));
		return AirbusDTO.buildAirbusDTOFromModel(aggiornato, false);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(required = true) Long id) {
		Airbus toDelete = airbusService.findByIdEager(id);
		
		if(toDelete.getTratte()!=null && !toDelete.getTratte().isEmpty() )
			throw new StillHasLinkedTratteException("Cannot delete, there are still tratte linked to this Airbus.");
		
		airbusService.rimuovi(id);
	}

	@PostMapping("/search")
	public List<AirbusDTO> search(@RequestBody AirbusDTO example) {
		return AirbusDTO.buildAirbusDTOListFromModelList(airbusService.findByExample(example.buildAirbusModel(false)),
				false);
	}

	// metodo di utilità che controlla se ci sono sovrapposizioni tra le tratte di
	// un airbus:
	// per ogni tratta dell'airbus controlla se l'ora di decollo o l'ora di
	// atterraggio cadono
	// tra l' ora di decollo e di atterraggio delle altre tratte; se viene
	// verificata questa
	// condizione la funzione ritorna true, altrimenti passa all' elemento
	// successivo, se
	// terminano gli elementi da analizzare senza che la condizione si sia
	// verificata la funzione
	// restituisce false.
	private boolean hasSovrapposizioni(Airbus airbus) {
		System.out.println("entro in hasSovrapposizioni");
		List<Tratta> tratte = airbus.getTratte();

		for (int i = 0; i < tratte.size(); i++) {
			Tratta tratta1 = tratte.get(i);
			for (int j = i; j < tratte.size(); j++) {
				Tratta tratta2 = tratte.get(j);
				if (i != j) {
					if (((tratta1.getOraDecollo().isBefore(tratta2.getOraDecollo()))
							&& (tratta1.getOraAtterraggio().isAfter(tratta2.getOraDecollo())))
							|| ((tratta1.getOraDecollo().isBefore(tratta2.getOraAtterraggio()))
									&& (tratta1.getOraAtterraggio().isAfter(tratta2.getOraAtterraggio())))) {
						System.out.println("ho trovato un caso incriminato");
						return true;
					}
				}
			}
		}
		System.out.println("sto uscendo dalla funzione senza aver trovato nulla");
		return false;
	}

}
