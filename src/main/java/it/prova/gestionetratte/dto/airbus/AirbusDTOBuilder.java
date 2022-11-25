package it.prova.gestionetratte.dto.airbus;

import java.time.LocalDate;
import java.util.List;

import it.prova.gestionetratte.dto.tratta.TrattaDTO;

public class AirbusDTOBuilder {
	
	private AirbusDTO dto = new AirbusDTO();
	
	public AirbusDTOBuilder() {
		
	}
	
	public AirbusDTOBuilder id(Long id) {
		this.dto.setId(id);
		return this;
	}
	
	public AirbusDTOBuilder codice(String codice) {
		this.dto.setCodice(codice);
		return this;
	}
	
	public AirbusDTOBuilder descrizione(String descrizione) {
		this.dto.setDescrizione(descrizione);
		return this;
	}
	
	public AirbusDTOBuilder dataInizioServizio(LocalDate date) {
		this.dto.setDataInizioServizio(date);
		return this;
	}
	
	public AirbusDTOBuilder numeroPasseggeri(Integer numeroPasseggeri) {
		this.dto.setNumeroPasseggeri(numeroPasseggeri);
		return this;
	}
	
	public AirbusDTOBuilder tratte(List<TrattaDTO> tratte) {
		this.dto.setTratte(tratte);
		return this;
	}
	
	public AirbusDTO build() {
		return this.dto;
	}
	
	
}
