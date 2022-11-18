package ao.co.celsodesousa.helpDesk.services;

import ao.co.celsodesousa.helpDesk.domain.Tecnico;
import ao.co.celsodesousa.helpDesk.exceptions.DataIntegrityViolationException;
import ao.co.celsodesousa.helpDesk.exceptions.ObjectNotFoundException;
import ao.co.celsodesousa.helpDesk.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private TecnicoRepository repository;

	public Tecnico findById(Integer id) {

		Optional<Tecnico> optional = repository.findById(id);

		return optional
				.orElseThrow(() -> new ObjectNotFoundException("Nao foi econtrado nenhum tecnico com esse id =" + id));

	}

	private void validate(Tecnico modelo) {

		Optional<Tecnico> optional = this.repository.findByCpf(modelo.getCpf());

		if (optional.isPresent() && modelo.getId() != optional.get().getId()) {

			throw new DataIntegrityViolationException("CPF Ja existe no sistema");

		} else {

			optional = this.repository.findByEmail(modelo.getEmail());

			if (optional.isPresent() && modelo.getId() != optional.get().getId()) {

				throw new DataIntegrityViolationException("Email Ja existe no sistema");

			}
		}

	}

	public List<Tecnico> findAll() {

		return this.repository.findAll();
	}

	@Transactional
	public Tecnico save(Tecnico model) {

		validate(model);
		return this.repository.save(model);
	}

	@Transactional

	public Tecnico update(Tecnico model) {

		Tecnico modelAntigo = findById(model.getId());

		if (modelAntigo != null) {
			validate(model);

			return this.repository.save(model);
		}
		return null;
	}
	
	@Transactional
	public Tecnico delete(Integer id) {
		
		
		Tecnico modelAntigo = findById(id);
		
		if(modelAntigo != null && modelAntigo.getChamado().size() == 0) {
			
			
			repository.delete(modelAntigo);
			
			return modelAntigo;
			
			
			
		}
		
		
		throw new DataIntegrityViolationException("Tecnico possui ordem de servico");
		
		
	}
}
