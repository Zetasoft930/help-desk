package ao.co.celsodesousa.helpDesk.repository;

import ao.co.celsodesousa.helpDesk.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
	
	public Optional<Tecnico> findByCpf(String cpf);
	public Optional<Tecnico> findByEmail(String email);

}
