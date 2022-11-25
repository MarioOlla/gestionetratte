package it.prova.gestionetratte.dto.tratta;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.gestionetratte.dto.airbus.AirbusDTO;
import it.prova.gestionetratte.model.Stato;
import it.prova.gestionetratte.model.Tratta;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrattaDTO {

	private Long id;
	@NotBlank(message = "{codice.notblank}")
	private String codice;
	@NotBlank(message = "{descrizione.notblank}")
	private String descrizione;
	@NotNull(message = "{data.notnull}")
	private LocalDate data;
	@NotNull(message = "{oraDecollo.notnull}")
	private LocalTime oraDecollo;
	@NotNull(message = "{oraAtterraggio.notnull}")
	private LocalTime oraAtterraggio;
	@NotNull(message = "{stato.notnull}")
	private Stato stato;
	@JsonIgnoreProperties(value = { "tratte" })
	private AirbusDTO airbus;
	
	public TrattaDTO() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getOraDecollo() {
		return oraDecollo;
	}

	public void setOraDecollo(LocalTime oraDecollo) {
		this.oraDecollo = oraDecollo;
	}

	public LocalTime getOraAtterraggio() {
		return oraAtterraggio;
	}

	public void setOraAtterraggio(LocalTime oraAtterraggio) {
		this.oraAtterraggio = oraAtterraggio;
	}

	public Stato getStato() {
		return stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}

	public AirbusDTO getAirbus() {
		return airbus;
	}

	public void setAirbus(AirbusDTO airbus) {
		this.airbus = airbus;
	}
	
	public Tratta buildTrattaModel(boolean includeAirbus) {
		Tratta result = new Tratta(this.id, this.codice, this.descrizione, this.data, this.oraDecollo, this.oraAtterraggio, this.stato);
		if(includeAirbus) {
			result.setAirbus(this.airbus.buildAirbusModel(false));
		}
		return result;
	}
	
	public static TrattaDTO buildTrattaDTOFromModel(Tratta model,boolean includeAirbus	) {
		TrattaDTOBuilder builder = new TrattaDTOBuilder()
				.id(model.getId())
				.codice(model.getCodice())
				.descrizione(model.getDescrizione())
				.data(model.getData())
				.oraDecollo(model.getOraDecollo())
				.oraAtterraggio(model.getOraAtterraggio())
				.stato(model.getStato());
		if(includeAirbus) {
			builder.airbus(AirbusDTO.buildAirbusDTOFromModel(model.getAirbus(), false) );
		}
		return builder.build();
	}
	
	public static List<Tratta> buildTrattaModelListFromDTOList(List<TrattaDTO> dtos , boolean includeAirbus){
		return dtos.stream()
				.map(item -> item.buildTrattaModel(includeAirbus))
				.collect(Collectors.toList());
	}
	
	public static List<TrattaDTO> buildTrattaDTOListFromModelList(List<Tratta> models , boolean includeAirbus){
		return models.stream()
				.map(item -> TrattaDTO.buildTrattaDTOFromModel(item, includeAirbus))
				.collect(Collectors.toList());
	}
}
