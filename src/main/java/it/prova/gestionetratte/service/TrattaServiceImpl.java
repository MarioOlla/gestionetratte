package it.prova.gestionetratte.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionetratte.model.Stato;
import it.prova.gestionetratte.model.Tratta;
import it.prova.gestionetratte.repository.tratta.TrattaRepository;
import it.prova.gestionetratte.web.api.exception.TrattaNotFoundException;

@Service
@Transactional
public class TrattaServiceImpl implements TrattaService {
	
	@Autowired
	private TrattaRepository repository;
	
	@Override
	public List<Tratta> listAll() {
		return (List<Tratta>) repository.findAll();
	}

	@Override
	public Tratta findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Tratta findByIdEager(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tratta> findByExample(Tratta example) {
		return repository.findByExample(example);
	}

	@Override
	public Tratta inserisciNuovo(Tratta input) {
		return repository.save(input);
	}

	@Override
	public Tratta update(Tratta input) {
		return repository.save(input);
	}

	@Override
	public void rimuovi(Long id) {
		if(repository.findById(id)==null)
			throw new TrattaNotFoundException("Non è stata trovata nessuna Tratta con id:"+id);
		repository.deleteById(id);
	}

	@Override
	public void concludiTratte() {
		List<Tratta> daChiudere = new ArrayList<>();
		List<Tratta> tutte = (List<Tratta>) repository.findAll();
		tutte.stream().forEach(tratta ->{
			if(tratta.getStato().equals(Stato.ATTIVA) 
					&& (tratta.getOraAtterraggio().isBefore(LocalTime.now()) 
							&& !tratta.getData().isAfter(LocalDate.now()))) {
				tratta.setStato(Stato.CONCLUSA);
				daChiudere.add(tratta);
			}
		});
		repository.saveAll(daChiudere);
	}

}
