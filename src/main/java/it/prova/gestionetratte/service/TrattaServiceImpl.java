package it.prova.gestionetratte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionetratte.model.Tratta;
import it.prova.gestionetratte.repository.tratta.TrattaRepository;

@Service
@Transactional
public class TrattaServiceImpl implements TrattaService {
	
	@Autowired
	private TrattaRepository repository;
	
	@Override
	public List<Tratta> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tratta findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tratta findByIdEager(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tratta> findByExample(Tratta example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void inserisciNuovo(Tratta input) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Tratta input) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rimuovi(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void concludiTratte() {
		// TODO Auto-generated method stub

	}

}
