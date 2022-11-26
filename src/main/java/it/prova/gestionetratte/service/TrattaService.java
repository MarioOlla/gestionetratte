package it.prova.gestionetratte.service;

import java.util.List;

import it.prova.gestionetratte.model.Tratta;

public interface TrattaService {
	public List<Tratta> listAll();

	public Tratta findById(Long id);

	public Tratta findByIdEager(Long id);

	public List<Tratta> findByExample(Tratta example);

	public void inserisciNuovo(Tratta input);

	public void update(Tratta input);

	public void rimuovi(Long id);
	
	public void concludiTratte();
	 
}
