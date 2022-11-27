package it.prova.gestionetratte.repository.tratta;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestionetratte.model.Airbus;
import it.prova.gestionetratte.model.Tratta;

public interface TrattaRepository extends CrudRepository<Tratta, Long>, CustomTrattaRepository{
	
	@Query("select distinct t from Tratta t left join fetch t.airbus")
	public List<Airbus> listAllEager();
	
	@Query("select t from Tratta t left join fetch t.airbus a where t.id=?1")
	public Airbus findByIdEager(Long id);
}
