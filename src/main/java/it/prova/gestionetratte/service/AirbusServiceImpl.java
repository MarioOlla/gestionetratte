package it.prova.gestionetratte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.repository.airbus.AirbusRepository;
import it.prova.gestionetratte.web.api.exception.AirbusNotFoundException;

@Service
@Transactional
public class AirbusServiceImpl implements AirbusService {

	@Autowired
	private AirbusRepository repository;

	@Override
	public List<Airbus> listAll() {
		return (List<Airbus>) repository.findAll();
	}

	@Override
	public Airbus findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Airbus findByIdEager(Long id) {
		return repository.findByIdEager(id);
	}

	@Override
	public List<Airbus> findByExample(Airbus example) {
		return repository.findByExample(example);
	}

	@Override
	public Airbus inserisciNuovo(Airbus input) {
		return repository.save(input);
	}

	@Override
	public Airbus update(Airbus input) {
		return repository.save(input);

	}

	@Override
	public void rimuovi(Long id) {
		if(repository.findById(id)==null)
			throw new AirbusNotFoundException("Non è stato trovato nessun Airbus con id:"+id);
		repository.deleteById(id);

	}

}
