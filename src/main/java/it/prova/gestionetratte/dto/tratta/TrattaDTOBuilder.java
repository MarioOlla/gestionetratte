package it.prova.gestionetratte.dto.tratta;

import java.time.LocalDate;
import java.time.LocalTime;

import it.prova.gestionetratte.dto.airbus.AirbusDTO;
import it.prova.gestionetratte.model.Stato;

public class TrattaDTOBuilder {
	
	private TrattaDTO dto = new TrattaDTO();
	
	public TrattaDTOBuilder id(Long id) {
		this.dto.setId(id);
		return this;
	}
	
	public TrattaDTOBuilder codice(String codice) {
		this.dto.setCodice(codice);
		return this;
	}
	
	public TrattaDTOBuilder descrizione(String descrizione) {
		this.dto.setDescrizione(descrizione);
		return this;
	}
	
	public TrattaDTOBuilder data(LocalDate data) {
		this.dto.setData(data);
		return this;
	}
	
	public TrattaDTOBuilder oraDecollo(LocalTime oraDecollo) {
		this.dto.setOraDecollo(oraDecollo);
		return this;
	}
	
	public TrattaDTOBuilder oraAtterraggio(LocalTime oraAtterraggio) {
		this.dto.setOraAtterraggio(oraAtterraggio);
		return this;
	}
	
	public TrattaDTOBuilder stato(Stato stato) {
		this.dto.setStato(stato);
		return this;
	}
	
	public TrattaDTOBuilder airbus(AirbusDTO airbus) {
		this.dto.setAirbus(airbus);
		return this;
	}
	
	public TrattaDTO build() {
		return this.dto;
	}
}
