package it.prova.gestionetratte.repository.airbus;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestionetratte.model.Airbus;

public interface AirbusRepository extends CrudRepository<Airbus, Long>,CustomAirbusRepository{
	 
	@Query("select a from Airbus a left join fetch a.tratte t where a.id = ?")
	public Airbus findByIdEager(Long id);
}
