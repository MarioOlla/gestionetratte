package it.prova.gestionetratte.repository.airbus;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestionetratte.model.Airbus;

public interface AirbusRepository extends CrudRepository<Airbus, Long>,CustomAirbusRepository{
	 
	@Query("select distinct a from Airbus a left join fetch a.tratte")
	public List<Airbus> listAllEager();
	
	@Query("select a from Airbus a left join fetch a.tratte t where a.id=?1")
	public Airbus findByIdEager(Long id);
}
