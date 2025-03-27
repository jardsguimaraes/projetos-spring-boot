package med.voll.medvoll.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.medvoll.model.PacienteModel;

public interface PacienteRepository extends JpaRepository<PacienteModel, Long> {

    Page<PacienteModel> findAllByAtivoTrue(Pageable paginacao);

}
