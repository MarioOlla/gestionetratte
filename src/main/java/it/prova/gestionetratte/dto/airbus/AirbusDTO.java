package it.prova.gestionetratte.dto.airbus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.gestionetratte.dto.tratta.TrattaDTO;
import it.prova.gestionetratte.model.Airbus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AirbusDTO {

	private Long id;

	@NotBlank(message = "{codice.notblank}")
	private String codice;

	@NotBlank(message = "{descrizione.notblank}")
	private String descrizione;

	@NotNull(message = "{dataInizioServizio.notnull}")
	private LocalDate dataInizioServizio;

	@NotNull(message = "{numeroPasseggeri.notnull}")
	@Min(1)
	private Integer numeroPasseggeri;

	@JsonIgnoreProperties(value = { "airbus" })
	private List<TrattaDTO> tratte = new ArrayList<>();
	
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

	public LocalDate getDataInizioServizio() {
		return dataInizioServizio;
	}

	public void setDataInizioServizio(LocalDate dataInizioServizio) {
		this.dataInizioServizio = dataInizioServizio;
	}

	public Integer getNumeroPasseggeri() {
		return numeroPasseggeri;
	}

	public void setNumeroPasseggeri(Integer numeroPasseggeri) {
		this.numeroPasseggeri = numeroPasseggeri;
	}

	public List<TrattaDTO> getTratte() {
		return tratte;
	}

	public void setTratte(List<TrattaDTO> tratte) {
		this.tratte = tratte;
	}

	public Airbus buildAirbusModel(boolean includeTratte) {
		Airbus result = new Airbus(this.id, this.codice, this.descrizione, this.dataInizioServizio, this.numeroPasseggeri);
		if(includeTratte) {
			result.setTratte(TrattaDTO.buildTrattaModelListFromDTOList(tratte,false));
		}
		return result;
	}

	public static AirbusDTO buildAirbusDTOFromModel(Airbus model,boolean includeTratte) {
		AirbusDTOBuilder builder = new AirbusDTOBuilder()
				.id(model.getId())
				.codice(model.getCodice())
				.descrizione(model.getDescrizione())
				.dataInizioServizio(model.getDataInizioServizio())
				.numeroPasseggeri(model.getNumeroPasseggeri());
		if(includeTratte)
			builder.tratte(TrattaDTO.buildTrattaDTOListFromModelList( model.getTratte(),false));
		return builder.build();
	}
	
	public static List<Airbus> buildAirbusModelListFromDTOList(List<AirbusDTO> listaDTO,boolean includeTratte){
		return listaDTO
				.stream()
				.map(listItem -> listItem.buildAirbusModel(includeTratte))
				.collect(Collectors.toList());
	}
	
	public static List<AirbusDTO> buildAirbusDTOListFromModelList(List<Airbus> models,boolean includeTratte){
		return models
				.stream()
				.map(airbus -> AirbusDTO.buildAirbusDTOFromModel(airbus,includeTratte))
				.collect(Collectors.toList());
	}
 }
