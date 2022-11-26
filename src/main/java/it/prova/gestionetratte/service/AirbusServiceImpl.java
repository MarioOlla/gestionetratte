package it.prova.gestionetratte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.repository.airbus.AirbusRepository;
import it.prova.gestionetratte.repository.tratta.TrattaRepository;

@Service
@Transactional
public class AirbusServiceImpl implements AirbusService {
	
	@Autowired
	private AirbusRepository repository;
	
	@Autowired
	private TrattaRepository trattaRepository;
	
	@Override
	public List<Airbus> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Airbus findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Airbus findByIdEager(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Airbus> findByExample(Airbus example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void inserisciNuovo(Airbus input) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Airbus input) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rimuovi(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Airbus> listAllConSovrapposizioni() {
		// TODO Auto-generated method stub
		return null;
	}

}
