package it.prova.gestionetratte.service;

import java.util.List;

import it.prova.gestionetratte.model.Airbus;

public interface AirbusService {
	
	public List<Airbus> listAll();
	
	public List<Airbus> listAllEager();
	
	public Airbus findById(Long id);
	
	public Airbus findByIdEager(Long id);
	
	public List<Airbus> findByExample(Airbus example);
	
	public Airbus inserisciNuovo(Airbus input);
	
	public Airbus update(Airbus input);
	
	public void rimuovi(Long id);
	
}
