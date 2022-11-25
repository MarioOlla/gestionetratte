package it.prova.gestionetratte.dto.tratta;

import java.time.LocalDate;
import java.time.LocalTime;

import it.prova.gestionetratte.dto.airbus.AirbusDTO;
import it.prova.gestionetratte.model.Stato;

public class TrattaDTOBuilder {
	
	private TrattaDTO dto = new TrattaDTO();
	
	public TrattaDTOBuilder id(Long id) {
		return this;
	}
	
	public TrattaDTOBuilder codice(String codice) {
		return this;
	}
	
	public TrattaDTOBuilder descrizione(String descrizione) {
		return this;
	}
	
	public TrattaDTOBuilder data(LocalDate data) {
		return this;
	}
	
	public TrattaDTOBuilder oraDecollo(LocalTime oraDecollo) {
		return this;
	}
	
	public TrattaDTOBuilder oraAtterraggio(LocalTime oraAtterraggio) {
		return this;
	}
	
	public TrattaDTOBuilder stato(Stato stato) {
		return this;
	}
	
	public TrattaDTOBuilder airbus(AirbusDTO airbus) {
		return this;
	}
	
	public TrattaDTO build() {
		return this.dto;
	}
}
