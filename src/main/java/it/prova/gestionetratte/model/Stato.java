package it.prova.gestionetratte.model;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Stato {
	ATTIVA("ATT"),CONCLUSA("CON"),ANNULLATA("ANN");
	
	private String abbreviazione;
	
	Stato(String abbreviazione){
		this.abbreviazione=abbreviazione;
	}
	
	public String getAbbreviazione() {
		return abbreviazione;
	}
	
	@JsonCreator
	public static Stato getSessoFromCode(String input) {
		if(StringUtils.isBlank(input))
			return null;
		
		for (Stato statoItem : Stato.values()) {
			if (statoItem.equals(Stato.valueOf(input))) {
				return statoItem;
			}
		}
		return null;
	}
}
