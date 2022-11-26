package it.prova.gestionetratte.service;

import java.util.List;

import it.prova.gestionetratte.model.Airbus;

public interface AirbusService {
	
	public List<Airbus> listAll();
	
	public Airbus findById(Long id);
	
	public Airbus findByIdEager(Long id);
	
	public List<Airbus> findByExample(Airbus example);
	
	public void inserisciNuovo(Airbus input);
	
	public void update(Airbus input);
	
	public void rimuovi(Long id);
	
	public List<Airbus> listAllConSovrapposizioni();
	
}
